package eu.isas.searchgui.processbuilders;

import com.compomics.util.exceptions.ExceptionHandler;
import com.compomics.util.experiment.biology.aminoacids.sequence.AminoAcidPattern;
import com.compomics.util.experiment.biology.enzymes.Enzyme;
import com.compomics.util.experiment.biology.modifications.Modification;
import com.compomics.util.experiment.biology.modifications.ModificationFactory;
import com.compomics.util.experiment.biology.modifications.ModificationType;
import com.compomics.util.experiment.identification.Advocate;
import com.compomics.util.parameters.identification.search.DigestionParameters;
import com.compomics.util.waiting.WaitingHandler;
import com.compomics.util.parameters.identification.search.SearchParameters;
import com.compomics.util.parameters.identification.tool_specific.TideParameters;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ProcessBuilder for the Tide index generation.
 *
 * @author Harald Barsnes
 * @author Marc Vaudel
 */
public class TideIndexProcessBuilder extends SearchGUIProcessBuilder {

    /**
     * The name of the Tide executable.
     */
    public static final String EXECUTABLE_FILE_NAME = "crux";
    /**
     * The FASTA file.
     */
    private File fastaFile;
    /**
     * The search parameters.
     */
    private SearchParameters searchParameters;
    /**
     * The advanced Tide parameters.
     */
    private TideParameters tideParameters;
    /**
     * The compomics modification factory.
     */
    private ModificationFactory modificationFactory = ModificationFactory.getInstance();

    /**
     * Constructor.
     *
     * @param tideFolder the Tide folder
     * @param fastaFile the FASTA file
     * @param searchParameters the search parameters
     * @param waitingHandler the waiting handler
     * @param exceptionHandler the handler of exceptions
     *
     * @throws IOException thrown of there are problems creating the Tide
     * parameter file
     */
    public TideIndexProcessBuilder(File tideFolder, File fastaFile, SearchParameters searchParameters, WaitingHandler waitingHandler, ExceptionHandler exceptionHandler) throws IOException {

        this.waitingHandler = waitingHandler;
        this.exceptionHandler = exceptionHandler;
        this.searchParameters = searchParameters;
        tideParameters = (TideParameters) searchParameters.getIdentificationAlgorithmParameter(Advocate.tide.getIndex());
        this.fastaFile = fastaFile;

        // make sure that the tide file is executable
        File tide = new File(tideFolder.getAbsolutePath() + File.separator + EXECUTABLE_FILE_NAME);
        tide.setExecutable(true);

        // full path to executable
        process_name_array.add(tide.getAbsolutePath());
        process_name_array.add("tide-index");

        // add the fasta file
        process_name_array.add(fastaFile.getAbsolutePath());

        // the name of the index file
        process_name_array.add(tideParameters.getFastIndexFolderName()); // @TODO: put in the user temp folder instead?

        // overwrite existing files
        process_name_array.add("--overwrite");
        process_name_array.add("T");

        // add the modifications
        String nonTerminalMods = getNonTerminalModifications();
        if (!nonTerminalMods.isEmpty()) {
            process_name_array.add("--mods-spec");
            process_name_array.add(getNonTerminalModifications());
        }
        String nTermMods = getTerminalModifications(true);
        if (!nTermMods.isEmpty()) {
            process_name_array.add("--nterm-peptide-mods-spec");
            process_name_array.add(nTermMods);
        }
        String cTermMods = getTerminalModifications(false);
        if (!cTermMods.isEmpty()) {
            process_name_array.add("--cterm-peptide-mods-spec");
            process_name_array.add(cTermMods);
        }

        // the max number of modifications per peptide
        if (tideParameters.getMaxVariableModificationsPerPeptide() != null) {
            process_name_array.add("--max-mods");
            process_name_array.add(tideParameters.getMaxVariableModificationsPerPeptide().toString());
        }

        // the decoy format
        process_name_array.add("--decoy-format");
        process_name_array.add(tideParameters.getDecoyFormat());
        if (!tideParameters.getDecoyFormat().equalsIgnoreCase("none")) {
            process_name_array.add("--keep-terminal-aminos");
            process_name_array.add(tideParameters.getKeepTerminalAminoAcids());
            process_name_array.add("--seed");
            process_name_array.add(tideParameters.getDecoySeed().toString());
        }

        // set the output directory
        process_name_array.add("--output-dir");
        process_name_array.add(tideParameters.getOutputFolderName());

        // create peptide list
        process_name_array.add("--peptide-list");
        if (tideParameters.getPrintPeptides()) {
            process_name_array.add("T");
        } else {
            process_name_array.add("F");
        }

        // use parameter file
        //process_name_array.add("--parameter-file");
        //process_name_array.add("a file"); // @TODO: implement?
        //
        // output verbosity
        process_name_array.add("--verbosity");
        process_name_array.add(tideParameters.getVerbosity().toString());

        // peptide lengths
        process_name_array.add("--min-length");
        process_name_array.add(tideParameters.getMinPeptideLength().toString());
        process_name_array.add("--max-length");
        process_name_array.add(tideParameters.getMaxPeptideLength().toString());

        // peptide masses
        process_name_array.add("--min-mass");
        process_name_array.add(tideParameters.getMinPrecursorMass().toString());
        process_name_array.add("--max-mass");
        process_name_array.add(tideParameters.getMaxPrecursorMass().toString());

        // isotopic masses type used when calculating the peptide mass
        process_name_array.add("--isotopic-mass");
        if (tideParameters.getMonoisotopicPrecursor()) {
            process_name_array.add("mono");
        } else {
            process_name_array.add("average");
        }

        // include starting peptide both with and without the initial m
        process_name_array.add("--clip-nterm-methionine");
        if (tideParameters.getClipNtermMethionine()) {
            process_name_array.add("T");
        } else {
            process_name_array.add("F");
        }

        DigestionParameters digestionPreferences = searchParameters.getDigestionParameters();

        if (digestionPreferences.getCleavageParameter() == DigestionParameters.CleavageParameter.wholeProtein) {

            process_name_array.add("--custom-enzyme");
            process_name_array.add("{X}|{X}");

        } else if (digestionPreferences.getCleavageParameter() == DigestionParameters.CleavageParameter.unSpecific) {

            process_name_array.add("--enzyme");
            process_name_array.add("no-enzyme");

        } else if (digestionPreferences.getCleavageParameter() == DigestionParameters.CleavageParameter.enzyme) {

            if (digestionPreferences.getEnzymes().size() == 1) {

                Enzyme enzyme = digestionPreferences.getEnzymes().get(0);
                String enzymeName = enzyme.getName();
                // enzyme
                //      note: Tide enzymes not implemented in utilities: 
                //          elastase ([ALIV]|{P}), clostripain ([R]|[]), iodosobenzoate ([W]|[]), proline-endopeptidase ([P]|[]), 
                //          staph-protease ([E]|[]), elastase-trypsin-chymotrypsin ([ALIVKRWFY]|{P})
                if (enzymeName.equals("Trypsin")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("trypsin");
                } else if (enzymeName.equals("Trypsin (no P rule)")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("trypsin/p");
                } else if (enzymeName.equals("Chymotrypsin")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("chymotrypsin");
                } else if (enzymeName.equals("CNBr")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("cyanogen-bromide");
                } else if (enzymeName.equals("Asp-N")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("asp-n");
                } else if (enzymeName.equals("Lys-C")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("lys-c");
                } else if (enzymeName.equals("Lys-N")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("lys-n");
                } else if (enzymeName.equals("Arg-C")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("arg-c");
                } else if (enzymeName.equals("Glu-C")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("glu-c");
                } else if (enzymeName.equals("Pepsin A")) {
                    process_name_array.add("--enzyme");
                    process_name_array.add("pepsin-a");
                } else {
                    process_name_array.add("--custom-enzyme");
                    process_name_array.add(digestionPreferences.getXTandemFormat());
                }
                process_name_array.add("--missed-cleavages");
                Integer missedCleavages = digestionPreferences.getnMissedCleavages(enzymeName);
                process_name_array.add("" + missedCleavages);
            } else {
                process_name_array.add("--custom-enzyme");
                process_name_array.add(digestionPreferences.getXTandemFormat());

                // missed cleavages
                process_name_array.add("--missed-cleavages");
                Integer missedCleavages = null;
                for (Enzyme enzyme : digestionPreferences.getEnzymes()) {
                    int enzymeMissedCleavages = digestionPreferences.getnMissedCleavages(enzyme.getName());
                    if (missedCleavages == null || enzymeMissedCleavages > missedCleavages) {
                        missedCleavages = enzymeMissedCleavages;
                    }
                }
                process_name_array.add("" + missedCleavages);
            }
        }

        // full or partial enzyme digestion
        boolean semiSpecific = false;
        if (digestionPreferences.getCleavageParameter() == DigestionParameters.CleavageParameter.enzyme) {
            for (Enzyme enzyme : digestionPreferences.getEnzymes()) {
                if (digestionPreferences.getSpecificity(enzyme.getName()) == DigestionParameters.Specificity.semiSpecific
                        || digestionPreferences.getSpecificity(enzyme.getName()) == DigestionParameters.Specificity.specificCTermOnly
                        || digestionPreferences.getSpecificity(enzyme.getName()) == DigestionParameters.Specificity.specificNTermOnly) {
                    semiSpecific = true;
                    break;
                }
            }
        }
        process_name_array.add("--digestion");
        if (semiSpecific) {
            process_name_array.add("partial-digest");
        } else {
            process_name_array.add(tideParameters.getDigestionType());
        }

        process_name_array.trimToSize();

        // print the command to the log file
        System.out.println(System.getProperty("line.separator") + System.getProperty("line.separator") + "tide index command: ");

        for (Object currentElement : process_name_array) {
            System.out.print(currentElement + " ");
        }

        System.out.println(System.getProperty("line.separator"));

        pb = new ProcessBuilder(process_name_array);
        pb.directory(tideFolder);

        // set error out and std out to same stream
        pb.redirectErrorStream(true);
    }

    /**
     * Returns a string with the non-terminal modifications as a string in the
     * Tide format.
     *
     * @return the non-terminal modifications as a string in the Tide format.
     */
    private String getNonTerminalModifications() {

        String tempFixedNonTerminalModifications = getNonTerminalModifications(searchParameters.getModificationParameters().getFixedModifications(), true);
        String tempVariableNonTerminalModifications = getNonTerminalModifications(searchParameters.getModificationParameters().getVariableModifications(), false);

        if (!tempFixedNonTerminalModifications.isEmpty() && !tempVariableNonTerminalModifications.isEmpty()) {
            tempFixedNonTerminalModifications += "," + tempVariableNonTerminalModifications;
        } else if (!tempVariableNonTerminalModifications.isEmpty()) {
            return tempVariableNonTerminalModifications;
        }

        return tempFixedNonTerminalModifications;
    }

    /**
     * Get the non-terminal modifications as a string in the Tide format.
     *
     * @param modifications the modifications to check
     * @param fixed if the modifications are to to be added as fixed or variable
     * @return the non-terminal modifications as a string in the Tide forma
     */
    private String getNonTerminalModifications(ArrayList<String> modifications, boolean fixed) {

        // tide modification pattern: [max_per_peptide]residues[+/-]mass_change
        String nonTerminalModifications = "";

        for (String modName : modifications) {

            Modification modification = modificationFactory.getModification(modName);
            ModificationType modificationType = modification.getModificationType();

            if (modificationType == ModificationType.modaa) {

                if (!nonTerminalModifications.isEmpty()) {

                    nonTerminalModifications += ",";

                }

                // add the number of allowed modifications per peptide
                if (!fixed) {

                    nonTerminalModifications += tideParameters.getMaxVariableModificationsPerTypePerPeptide(); // @TODO: make this modification specific?

                }

                // add the residues affected
                AminoAcidPattern modificationPattern = modification.getPattern();

                if (modificationPattern != null && modificationPattern.length() > 0) {

                    for (Character aminoAcid : modificationPattern.getAminoAcidsAtTarget()) {

                        nonTerminalModifications += aminoAcid;

                    }
                }

                // add the modification mass
                if (modification.getMass() > 0) {

                    nonTerminalModifications += "+";

                }

                nonTerminalModifications += modification.getMass();

            }
        }

        return nonTerminalModifications;
    }

    /**
     * Returns a string with the terminal modifications as a string in the Tide
     * format.
     *
     * @param nTerm true if the modifications are n-terminal, false if
     * c-terminal
     * @return the terminal modifications as a string in the Tide format.
     */
    private String getTerminalModifications(boolean nTerm) {

        String tempNTermModifications = getTerminalModifications(searchParameters.getModificationParameters().getFixedModifications(), true, nTerm);
        String tempCTermModifications = getTerminalModifications(searchParameters.getModificationParameters().getVariableModifications(), false, nTerm);

        if (!tempNTermModifications.isEmpty() && !tempCTermModifications.isEmpty()) {
            tempNTermModifications += "," + tempCTermModifications;
        } else if (!tempCTermModifications.isEmpty()) {
            return tempCTermModifications;
        }

        return tempNTermModifications;
    }

    /**
     * Get the terminal modifications as a string in the Tide format.
     *
     * @param modifications the modifications to check
     * @param fixed if the modifications are to to be added as fixed or variable
     * @param nTerm true if the modifications are n-terminal, false if
     * c-terminal
     * @return the terminal modifications as a string in the Tide format
     */
    private String getTerminalModifications(ArrayList<String> modifications, boolean fixed, boolean nTerm) {

        String terminalModifications = "";

        for (String modName : modifications) {

            Modification modification = modificationFactory.getSingleAAModification(modName);
            ModificationType modificationType = modification.getModificationType();

            if ((modificationType.isNTerm() && nTerm)
                    || (modificationType.isCTerm() && !nTerm)) {

                if (!terminalModifications.isEmpty()) {
                    terminalModifications += ",";
                }

                // add the number of allowed modifications per peptide
                if (!fixed) {
                    terminalModifications += "1";
                }

                // add the residues affected 
                AminoAcidPattern modificationPattern = modification.getPattern();
                String tempPattern = "";

                if (modificationPattern != null && modificationPattern.length() > 0) {

                    for (Character aminoAcid : modificationPattern.getAminoAcidsAtTarget()) {

                        tempPattern += aminoAcid;

                    }
                }

                if (tempPattern.length() == 0) {

                    tempPattern = "X";

                }

                terminalModifications += tempPattern;

                // add the modification mass
                if (modification.getRoundedMass() > 0) {

                    terminalModifications += "+";

                }

                terminalModifications += modification.getRoundedMass();

            }
        }

        return terminalModifications;
    }

    @Override
    public String getType() {
        return "Tide Indexing";
    }

    @Override
    public String getCurrentlyProcessedFileName() {
        return fastaFile.getName();
    }
}
