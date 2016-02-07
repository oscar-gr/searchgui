package eu.isas.searchgui.gui;

import com.compomics.util.experiment.identification.protein_sequences.SequenceFactory;
import com.compomics.util.gui.GuiUtilities;
import com.compomics.util.gui.error_handlers.HelpDialog;
import eu.isas.searchgui.SearchHandler;
import eu.isas.searchgui.preferences.OutputOption;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * SearchGUI advanced settings dialog.
 *
 * @author Harald Barsnes
 * @author Marc Vaudel
 */
public class AdvancedSettingsDialog extends javax.swing.JDialog {

    /**
     * The SearchGUI search panel.
     */
    private SearchGUI searchGUI;

    /**
     * Creates a new AdvancedSettingsDialog.
     *
     * @param searchGUI the SearchGUI main frame
     * @param modal if the dialog is to be modal
     */
    public AdvancedSettingsDialog(SearchGUI searchGUI, boolean modal) {
        super(searchGUI, modal);
        initComponents();
        this.searchGUI = searchGUI;

        SearchHandler currentSearchHandler = searchGUI.getSearchHandler();

        fastaSuffixTxt.setText(SequenceFactory.getTargetDecoyFileNameTag());
        mgfMaxSizeTxt.setText(searchGUI.getMgfMaxSize() + "");
        mgfReducedSizeTxt.setText(searchGUI.getMgfNSpectra() + "");
        if (currentSearchHandler.renameXTandemFile()) {
            renameCmb.setSelectedIndex(0);
        } else {
            renameCmb.setSelectedIndex(1);
        }
        if (searchGUI.checkPeakPicking()) {
            peakPickingComboBox.setSelectedIndex(0);
        } else {
            peakPickingComboBox.setSelectedIndex(1);
        }
        if (searchGUI.checkDuplicateTitles()) {
            duplicateTitlesComboBox.setSelectedIndex(0);
        } else {
            duplicateTitlesComboBox.setSelectedIndex(1);
        }
        refMassTxt.setText(currentSearchHandler.getRefMass() + "");
        if (currentSearchHandler.generateProteinTree()) {
            proteinTreeComboBox.setSelectedIndex(0);
        } else {
            proteinTreeComboBox.setSelectedIndex(1);
        }
        groupResultFilesCmb.setSelectedIndex(currentSearchHandler.getOutputOption().id);
        if (currentSearchHandler.outputData()) {
            includeDataCmb.setSelectedIndex(0);
        } else {
            includeDataCmb.setSelectedIndex(1);
        }
        if (currentSearchHandler.isIncludeDateInOutputName()) {
            includeDateCmb.setSelectedIndex(0);
        } else {
            includeDateCmb.setSelectedIndex(1);
        }

        renameCmb.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));
        groupResultFilesCmb.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));
        includeDataCmb.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));
        includeDateCmb.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));
        peakPickingComboBox.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));
        duplicateTitlesComboBox.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));
        proteinTreeComboBox.setRenderer(new com.compomics.util.gui.renderers.AlignedListCellRenderer(SwingConstants.CENTER));

        setLocationRelativeTo(searchGUI);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        advancedParamatersPanel = new javax.swing.JPanel();
        fileProcessingPanel = new javax.swing.JPanel();
        fastaFileSuffixLabel = new javax.swing.JLabel();
        fastaSuffixTxt = new javax.swing.JTextField();
        renameCmb = new javax.swing.JComboBox();
        renameXTandemFileLabel = new javax.swing.JLabel();
        spectrumProcessingPanel = new javax.swing.JPanel();
        peakPickingLabel = new javax.swing.JLabel();
        peakPickingComboBox = new javax.swing.JComboBox();
        duplicateTitlesLabel = new javax.swing.JLabel();
        duplicateTitlesComboBox = new javax.swing.JComboBox();
        maxMgfFileSizeLabel = new javax.swing.JLabel();
        maxSpectraPerFileLabel = new javax.swing.JLabel();
        mgfMaxSizeTxt = new javax.swing.JTextField();
        mgfReducedSizeTxt = new javax.swing.JTextField();
        openDialogHelpJButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        outputPanel = new javax.swing.JPanel();
        groupResultFilesTxt = new javax.swing.JLabel();
        groupResultFilesCmb = new javax.swing.JComboBox();
        includeDataTxt = new javax.swing.JLabel();
        includeDataCmb = new javax.swing.JComboBox();
        includeDateLbl = new javax.swing.JLabel();
        includeDateCmb = new javax.swing.JComboBox();
        proteinTreePanel = new javax.swing.JPanel();
        proteinTreeLabel = new javax.swing.JLabel();
        proteinTreeComboBox = new javax.swing.JComboBox();
        identificationParametersPanel = new javax.swing.JPanel();
        refMassLbl = new javax.swing.JLabel();
        refMassTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Advanced Settings");
        setMinimumSize(new java.awt.Dimension(500, 300));
        setResizable(false);

        advancedParamatersPanel.setBackground(new java.awt.Color(230, 230, 230));

        fileProcessingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("File Processing"));
        fileProcessingPanel.setOpaque(false);

        fastaFileSuffixLabel.setText("FASTA File Suffix");

        fastaSuffixTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fastaSuffixTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fastaSuffixTxtActionPerformed(evt);
            }
        });
        fastaSuffixTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fastaSuffixTxtKeyReleased(evt);
            }
        });

        renameCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        renameXTandemFileLabel.setText("Rename X!Tandem File");

        javax.swing.GroupLayout fileProcessingPanelLayout = new javax.swing.GroupLayout(fileProcessingPanel);
        fileProcessingPanel.setLayout(fileProcessingPanelLayout);
        fileProcessingPanelLayout.setHorizontalGroup(
            fileProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileProcessingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fileProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fastaFileSuffixLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(renameXTandemFileLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fileProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(renameCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fastaSuffixTxt))
                .addContainerGap())
        );
        fileProcessingPanelLayout.setVerticalGroup(
            fileProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileProcessingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fileProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fastaSuffixTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fastaFileSuffixLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fileProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renameCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(renameXTandemFileLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        spectrumProcessingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Spectrum Processing"));
        spectrumProcessingPanel.setOpaque(false);

        peakPickingLabel.setText("Check Peak Picking");

        peakPickingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        duplicateTitlesLabel.setText("Check Duplicate Titles");

        duplicateTitlesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));

        maxMgfFileSizeLabel.setText("Maximum MGF File Size (MB)");

        maxSpectraPerFileLabel.setText("Maximum Spectra in MGF File");

        mgfMaxSizeTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mgfMaxSizeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mgfMaxSizeTxtActionPerformed(evt);
            }
        });
        mgfMaxSizeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mgfMaxSizeTxtKeyReleased(evt);
            }
        });

        mgfReducedSizeTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        mgfReducedSizeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mgfReducedSizeTxtActionPerformed(evt);
            }
        });
        mgfReducedSizeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mgfReducedSizeTxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout spectrumProcessingPanelLayout = new javax.swing.GroupLayout(spectrumProcessingPanel);
        spectrumProcessingPanel.setLayout(spectrumProcessingPanelLayout);
        spectrumProcessingPanelLayout.setHorizontalGroup(
            spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spectrumProcessingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(spectrumProcessingPanelLayout.createSequentialGroup()
                        .addComponent(peakPickingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(peakPickingComboBox, 0, 180, Short.MAX_VALUE))
                    .addGroup(spectrumProcessingPanelLayout.createSequentialGroup()
                        .addComponent(duplicateTitlesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(duplicateTitlesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(spectrumProcessingPanelLayout.createSequentialGroup()
                        .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(maxMgfFileSizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(maxSpectraPerFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mgfReducedSizeTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mgfMaxSizeTxt, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        spectrumProcessingPanelLayout.setVerticalGroup(
            spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spectrumProcessingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peakPickingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peakPickingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duplicateTitlesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duplicateTitlesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxMgfFileSizeLabel)
                    .addComponent(mgfMaxSizeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spectrumProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxSpectraPerFileLabel)
                    .addComponent(mgfReducedSizeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        openDialogHelpJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.GIF"))); // NOI18N
        openDialogHelpJButton.setToolTipText("Help");
        openDialogHelpJButton.setBorder(null);
        openDialogHelpJButton.setBorderPainted(false);
        openDialogHelpJButton.setContentAreaFilled(false);
        openDialogHelpJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openDialogHelpJButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                openDialogHelpJButtonMouseExited(evt);
            }
        });
        openDialogHelpJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openDialogHelpJButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        outputPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Output"));
        outputPanel.setOpaque(false);

        groupResultFilesTxt.setText("Group Identification Files");

        groupResultFilesCmb.setModel(new DefaultComboBoxModel(OutputOption.getOutputOptionsNames()));
        groupResultFilesCmb.setToolTipText("Output files grouping options");

        includeDataTxt.setText("Include Spectra and Database");
        includeDataTxt.setToolTipText("");

        includeDataCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        includeDataCmb.setSelectedIndex(1);
        includeDataCmb.setToolTipText("Copy the spectra and fasta files along with the identification results");

        includeDateLbl.setText("Include Date in File Name");

        includeDateCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        includeDateCmb.setSelectedIndex(1);

        javax.swing.GroupLayout outputPanelLayout = new javax.swing.GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(includeDataTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addComponent(groupResultFilesTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(includeDateLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(includeDateCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(includeDataCmb, 0, 180, Short.MAX_VALUE)
                    .addComponent(groupResultFilesCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupResultFilesCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupResultFilesTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(includeDataTxt)
                    .addComponent(includeDataCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(includeDateLbl)
                    .addComponent(includeDateCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        proteinTreePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Protein Inference"));
        proteinTreePanel.setOpaque(false);

        proteinTreeLabel.setText("Generate Protein Index");

        proteinTreeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        proteinTreeComboBox.setSelectedIndex(1);

        javax.swing.GroupLayout proteinTreePanelLayout = new javax.swing.GroupLayout(proteinTreePanel);
        proteinTreePanel.setLayout(proteinTreePanelLayout);
        proteinTreePanelLayout.setHorizontalGroup(
            proteinTreePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proteinTreePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proteinTreeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proteinTreeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        proteinTreePanelLayout.setVerticalGroup(
            proteinTreePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proteinTreePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proteinTreePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proteinTreeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proteinTreeLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        identificationParametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        identificationParametersPanel.setOpaque(false);

        refMassLbl.setText("Reference Mass (Da)");
        refMassLbl.setToolTipText("Reference mass used to convert tolerances from ppm to Da");

        refMassTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        refMassTxt.setToolTipText("Reference mass used to convert tolerances from ppm to Da");
        refMassTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refMassTxtActionPerformed(evt);
            }
        });
        refMassTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                refMassTxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout identificationParametersPanelLayout = new javax.swing.GroupLayout(identificationParametersPanel);
        identificationParametersPanel.setLayout(identificationParametersPanelLayout);
        identificationParametersPanelLayout.setHorizontalGroup(
            identificationParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(identificationParametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refMassLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refMassTxt)
                .addContainerGap())
        );
        identificationParametersPanelLayout.setVerticalGroup(
            identificationParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(identificationParametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(identificationParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refMassLbl)
                    .addComponent(refMassTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout advancedParamatersPanelLayout = new javax.swing.GroupLayout(advancedParamatersPanel);
        advancedParamatersPanel.setLayout(advancedParamatersPanelLayout);
        advancedParamatersPanelLayout.setHorizontalGroup(
            advancedParamatersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedParamatersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(advancedParamatersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spectrumProcessingPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, advancedParamatersPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(openDialogHelpJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addComponent(fileProcessingPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(proteinTreePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(identificationParametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        advancedParamatersPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {closeButton, okButton});

        advancedParamatersPanelLayout.setVerticalGroup(
            advancedParamatersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(advancedParamatersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spectrumProcessingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileProcessingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(identificationParametersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proteinTreePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(advancedParamatersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(openDialogHelpJButton)
                    .addComponent(okButton)
                    .addComponent(closeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advancedParamatersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(advancedParamatersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Validate the input and, if OK, save and close the dialog.
     *
     * @param evt
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (validateAdvancedParametersInput(true)) {
            SearchHandler currentSearchHandler = searchGUI.getSearchHandler();
            currentSearchHandler.setRenameXTandemFile(renameCmb.getSelectedIndex() == 0);
            searchGUI.setCheckPeakPicking(peakPickingComboBox.getSelectedIndex() == 0);
            searchGUI.setCheckDuplicateTitles(duplicateTitlesComboBox.getSelectedIndex() == 0);
            currentSearchHandler.setGenerateProteinTree(proteinTreeComboBox.getSelectedIndex() == 0);
            OutputOption outputOption = OutputOption.getOutputOption(groupResultFilesCmb.getSelectedIndex());
            currentSearchHandler.setOutputOption(outputOption);
            currentSearchHandler.setOutputData(includeDataCmb.getSelectedIndex() == 0);
            currentSearchHandler.setIncludeDateInOutputName(includeDateCmb.getSelectedIndex() == 0);
            currentSearchHandler.setRefMass(new Double(refMassTxt.getText()));
            dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Close the dialog without saving.
     *
     * @param evt
     */
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * Open the help dialog.
     *
     * @param evt
     */
    private void openDialogHelpJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openDialogHelpJButtonActionPerformed
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        new HelpDialog(this, getClass().getResource("/helpFiles/AdvancedSettingsDialog.html"),
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/help.GIF")),
                Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/searchgui.gif")),
                "SearchGUI - Help", 500, 50);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_openDialogHelpJButtonActionPerformed

    /**
     * Change the cursor back to the default cursor.
     *
     * @param evt
     */
    private void openDialogHelpJButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openDialogHelpJButtonMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_openDialogHelpJButtonMouseExited

    /**
     * Change the cursor to a hand cursor.
     *
     * @param evt
     */
    private void openDialogHelpJButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openDialogHelpJButtonMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_openDialogHelpJButtonMouseEntered

    /**
     * Verify the input for the max spectra in mgf file.
     *
     * @param evt
     */
    private void mgfReducedSizeTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mgfReducedSizeTxtKeyReleased
        mgfReducedSizeTxtActionPerformed(null);
    }//GEN-LAST:event_mgfReducedSizeTxtKeyReleased

    /**
     * Verify the input for the max spectra in mgf file.
     *
     * @param evt
     */
    private void mgfReducedSizeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mgfReducedSizeTxtActionPerformed
        try {
            searchGUI.setMgfNSpectra(new Integer(mgfReducedSizeTxt.getText().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Maximum amount of spectra could not be parsed.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            mgfReducedSizeTxt.setText(mgfReducedSizeTxt.getText());
        }
    }//GEN-LAST:event_mgfReducedSizeTxtActionPerformed

    /**
     * Verify the max mgf file size input.
     *
     * @param evt
     */
    private void mgfMaxSizeTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mgfMaxSizeTxtKeyReleased
        mgfMaxSizeTxtActionPerformed(null);
    }//GEN-LAST:event_mgfMaxSizeTxtKeyReleased

    /**
     * Verify the max mgf file size input.
     *
     * @param evt
     */
    private void mgfMaxSizeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mgfMaxSizeTxtActionPerformed
        try {
            searchGUI.setMgfMaxSize(new Double(mgfMaxSizeTxt.getText().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Maximum MGF file size could not be parsed.",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            mgfMaxSizeTxt.setText(mgfMaxSizeTxt.getText());
        }
    }//GEN-LAST:event_mgfMaxSizeTxtActionPerformed

    /**
     * Update the target decoy file name tag.
     *
     * @param evt
     */
    private void fastaSuffixTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fastaSuffixTxtKeyReleased
        fastaSuffixTxtActionPerformed(null);
    }//GEN-LAST:event_fastaSuffixTxtKeyReleased

    /**
     * Update the target decoy file name tag.
     *
     * @param evt
     */
    private void fastaSuffixTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fastaSuffixTxtActionPerformed
        SequenceFactory.setTargetDecoyFileNameTag(fastaSuffixTxt.getText().trim());
    }//GEN-LAST:event_fastaSuffixTxtActionPerformed

    /**
     * Validate the input parameters.
     * 
     * @param evt 
     */
    private void refMassTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refMassTxtActionPerformed
        validateAdvancedParametersInput(false);
    }//GEN-LAST:event_refMassTxtActionPerformed

    /**
     * Validate the input parameters.
     * 
     * @param evt 
     */
    private void refMassTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_refMassTxtKeyReleased
        validateAdvancedParametersInput(false);
    }//GEN-LAST:event_refMassTxtKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel advancedParamatersPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox duplicateTitlesComboBox;
    private javax.swing.JLabel duplicateTitlesLabel;
    private javax.swing.JLabel fastaFileSuffixLabel;
    private javax.swing.JTextField fastaSuffixTxt;
    private javax.swing.JPanel fileProcessingPanel;
    private javax.swing.JComboBox groupResultFilesCmb;
    private javax.swing.JLabel groupResultFilesTxt;
    private javax.swing.JPanel identificationParametersPanel;
    private javax.swing.JComboBox includeDataCmb;
    private javax.swing.JLabel includeDataTxt;
    private javax.swing.JComboBox includeDateCmb;
    private javax.swing.JLabel includeDateLbl;
    private javax.swing.JLabel maxMgfFileSizeLabel;
    private javax.swing.JLabel maxSpectraPerFileLabel;
    private javax.swing.JTextField mgfMaxSizeTxt;
    private javax.swing.JTextField mgfReducedSizeTxt;
    private javax.swing.JButton okButton;
    private javax.swing.JButton openDialogHelpJButton;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JComboBox peakPickingComboBox;
    private javax.swing.JLabel peakPickingLabel;
    private javax.swing.JComboBox proteinTreeComboBox;
    private javax.swing.JLabel proteinTreeLabel;
    private javax.swing.JPanel proteinTreePanel;
    private javax.swing.JLabel refMassLbl;
    private javax.swing.JTextField refMassTxt;
    private javax.swing.JComboBox renameCmb;
    private javax.swing.JLabel renameXTandemFileLabel;
    private javax.swing.JPanel spectrumProcessingPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Inspects the parameters validity.
     *
     * @param showMessage if true an error messages are shown to the users
     * @return a boolean indicating if the parameters are valid
     */
    public boolean validateAdvancedParametersInput(boolean showMessage) {

        boolean valid = true;
        
        valid = GuiUtilities.validateDoubleInput(this, maxMgfFileSizeLabel, mgfMaxSizeTxt, "mgf max size", "Mgf Max Size Error", true, showMessage, valid);
        valid = GuiUtilities.validateDoubleInput(this, maxSpectraPerFileLabel, mgfReducedSizeTxt, "max spectra in mgf file", "Max Spectra Error", true, showMessage, valid);
        valid = GuiUtilities.validateDoubleInput(this, refMassLbl, refMassTxt, "reference mass", "Reference Mass Error", true, showMessage, valid);

        return valid;
    }
}
