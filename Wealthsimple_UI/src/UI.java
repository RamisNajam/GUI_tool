import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * This is the UI class that displays the data to the user. I recognize that each frame and the
 * button listeners should be in separate classes, but for simplicity I have put them all
 * in one class.
 *
 */
class UI {

  private PendingTransfers pt;

  UI(PendingTransfers pt) {
    this.pt = pt;
    prepFrames();
  }

  /**
   * This method prepares the UI frame and panels, and also sets up the button listeners
   *
   */
  private void prepFrames() {
    // Set the text fonts that will be used
    final Font wsFontLarge = new Font("Gadugi", Font.BOLD, 27);
    final Font wsFontMed = new Font("Gadugi", Font.PLAIN, 16);
    final Font wsFontMedBold = new Font("Gadugi", Font.BOLD, 16);
    final Font wsFontSmall = new Font("Gadugi", Font.PLAIN, 12);

    // Set the colours that will be used
    final Color backgroundColor = new java.awt.Color(249, 248, 247);
    final Color boldColour =  new java.awt.Color(86, 86, 86);
    final Color textColor = new java.awt.Color(141, 141, 141);

    // Get the paths for the files
    //final String arrowPath = ImportTransfers.class.getClassLoader().getResource("arrowButton.jpg").getPath();
    //final String logoPath = ImportTransfers.class.getClassLoader().getResource("wsLogo.png").getPath();
    //final String leftButton = ImportTransfers.class.getClassLoader().getResource("leftArrowButton.jpg").getPath();


    // Create the arrowButton and the Wealthsimple logo
    ImageIcon arrowButton = new ImageIcon("C:\\Users\\Ramis\\IdeaProjects\\Wealthsimple_UI\\arrowButton.jpg");

    ImageIcon wsIcon = new ImageIcon("C:\\Users\\Ramis\\IdeaProjects\\Wealthsimple_UI\\wsLogo.png");
    Image logo = wsIcon.getImage();

    // Set up the main frame
    final JFrame mainFrame = new JFrame();
    mainFrame.setSize(500, 320);
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setIconImage(logo);
    mainFrame.getContentPane().setBackground(backgroundColor);

    // Setup the panel
    final JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setBackground(backgroundColor);
    final GridBagConstraints gc = new GridBagConstraints();
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.insets = new Insets(9, 9, 9, 9);

    // Create the title element and add it to the panel
    final JLabel elementTitle = new JLabel("Check Transfer Status");
    elementTitle.setFont(wsFontLarge);
    elementTitle.setForeground(boldColour);
    gc.gridx = 1;
    gc.gridy = 0;
    panel.add(elementTitle, gc);

    // Create and add the instructions prompt to the panel
    final JTextArea instructionsPrompt = new JTextArea("We'll need to know your transfer "
        + "code so "
        + "that we can" + System.lineSeparator() + "update you on the status of your transfer. "
        + "If you're unclear," + System.lineSeparator() + "you can find "
        + "these details in the email confirmation we sent.",3, 20);
    instructionsPrompt.setEditable(false);
    instructionsPrompt.setBackground(backgroundColor);
    instructionsPrompt.setForeground(textColor);
    instructionsPrompt.setFont(wsFontMed);
    gc.gridx = 1;
    gc.gridy = 1;
    panel.add(instructionsPrompt, gc);

    // Create and add the TextField to the Panel
    final JTextField enter_id = new JTextField("Transfer ID");
    enter_id.setFont(wsFontMed);
    enter_id.setForeground(textColor);
    final Border border = BorderFactory.createLineBorder(textColor);
    enter_id.setBorder(border);
    gc.gridx = 1;
    gc.gridy = 2;
    panel.add(enter_id, gc);

    // Create and add the arrow button to the panel
    final JButton arrow = new JButton(arrowButton);
    arrow.setBorderPainted(false);
    arrow.setContentAreaFilled(false);
    arrow.setFocusPainted(false);
    arrow.setOpaque(false);
    gc.gridx = 1;
    gc.gridy = 4;
    panel.add(arrow, gc);
    // Add the action listener
    arrow.addActionListener(new ActionListener() {
      final JLabel errorMsg = new JLabel("Invalid transfer code.");
      public void actionPerformed(ActionEvent e) {
        String entered_id = enter_id.getText();
        Transfer transfer = pt.getTransfer(entered_id);
        //final JLabel errorMsg = new JLabel("Invalid transfer code.");
        if(transfer == null) {
          Border redBorder = BorderFactory.createLineBorder(Color.RED);
          enter_id.setBorder(redBorder);
          errorMsg.setForeground(Color.RED);
          errorMsg.setBackground(backgroundColor);
          errorMsg.setFont(wsFontSmall);
          gc.gridx = 1;
          gc.gridy = 3;
          panel.add(errorMsg, gc);
          mainFrame.setVisible(true);
        } else {
          mainFrame.setSize(850, 320);
          errorMsg.setForeground(backgroundColor);
          panel.remove(errorMsg);
          panel.setVisible(false);
          // Change border back to proper colour
          enter_id.setBorder(border);
          // Create an information panel
          final Panel infoPanel = new Panel();
          infoPanel.setLayout(new GridBagLayout());
          infoPanel.setBackground(backgroundColor);
          final GridBagConstraints gbc = new GridBagConstraints();
          gbc.fill = GridBagConstraints.HORIZONTAL;
          gbc.insets = new Insets(9, 9, 9, 9);

          // 1
          // Create the title element and add it to the panel
          JLabel infoElementTitle = new JLabel("Transfer Status");
          infoElementTitle.setFont(wsFontLarge);
          infoElementTitle.setForeground(boldColour);
          gbc.gridx = 1;
          gbc.gridy = 0;
          infoPanel.add(infoElementTitle, gbc);

          // 2
          // Create the title element and add it to the panel
          JLabel transferState = new JLabel("Transfer State");
          transferState.setFont(wsFontMedBold);
          transferState.setForeground(textColor);
          gbc.gridx = 1;
          gbc.gridy = 1;
          infoPanel.add(transferState, gbc);

          // 3
          // Create the title element and add it to the panel
          String stateValue = transfer.getTransferState();
          JLabel transferStateValue = new JLabel(stateValue);
          transferStateValue.setFont(wsFontMed);
          transferStateValue.setForeground(textColor);
          gbc.gridx = 2;
          gbc.gridy = 1;
          infoPanel.add(transferStateValue, gbc);

          // 4
          // Create the title element and add it to the panel
          JLabel accountType = new JLabel("Account type");
          accountType.setFont(wsFontMedBold);
          accountType.setForeground(textColor);
          gbc.gridx = 1;
          gbc.gridy = 2;
          infoPanel.add(accountType, gbc);

          // 5
          // Create the title element and add it to the panel
          String accountValue = transfer.account_type;
          JLabel accountStateValue = new JLabel(accountValue);
          accountStateValue.setFont(wsFontMed);
          accountStateValue.setForeground(textColor);
          gbc.gridx = 2;
          gbc.gridy = 2;
          infoPanel.add(accountStateValue, gbc);

          // 6
          // Create the title element and add it to the panel
          JLabel transferType = new JLabel("Transfer type");
          transferType.setFont(wsFontMedBold);
          transferType.setForeground(textColor);
          gbc.gridx = 1;
          gbc.gridy = 3;
          infoPanel.add(transferType, gbc);

          // 7
          // Create the title element and add it to the panel
          String transferValue = transfer.transfer_type;
          JLabel transferTypeValue = new JLabel(transferValue);
          transferTypeValue.setFont(wsFontMed);
          transferTypeValue.setForeground(textColor);
          gbc.gridx = 2;
          gbc.gridy = 3;
          infoPanel.add(transferTypeValue, gbc);

          // 8
          // Add the back button
          ImageIcon leftArrowButton = new ImageIcon("C:\\Users\\Ramis\\IdeaProjects\\Wealthsimple_UI\\leftArrowButton.jpg");
          JButton leftArrow = new JButton(leftArrowButton);
          leftArrow.setBorderPainted(false);
          leftArrow.setContentAreaFilled(false);
          leftArrow.setFocusPainted(false);
          leftArrow.setOpaque(false);
          gbc.gridx = 0;
          gbc.gridy = 2;
          infoPanel.add(leftArrow, gbc);
          leftArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              // Set infoPanel false
              infoPanel.setVisible(false);
              mainFrame.setSize(500, 320);
              panel.setVisible(true);
              mainFrame.setVisible(true);
            }
          });
          mainFrame.add(infoPanel);
          mainFrame.setVisible(true);
        }
      }
    });

    // Set the panel and mainFrame visible
    panel.setVisible(true);
    mainFrame.add(panel);
    mainFrame.setVisible(true);
  }
}