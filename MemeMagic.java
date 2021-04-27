import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 * You will need to implement certain portions of this class, marked with comments starting with "TODO" to connect 
 * it with your existing code. 
 * 
 * This class provides an example layout for the GUI. You are encouraged to be creative in your design. 
 * More information about Swing is online at: 
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 * 
 * 
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme meme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    private JPanel genSave;
    
	JComboBox memeVerticalAligncomboBox;

	private JTextField backgroundImageTitleText;

	private JTextField backgroundImageDescriptionTextField;

	private JTextField memeCaptionTextField;
    
    //String title = backgroundImageTitleText.getText();
	//String desc = backgroundImageDescriptionTextField.getText();
	//String caption = memeCaptionTextField.getText();
    
    public MemeMagic() {
        this.user = new User();
        this.backgroundImageTitleText = new JTextField();
        this.backgroundImageDescriptionTextField = new JTextField();
        this.memeCaptionTextField = new JTextField();
    }
    
    public MemeMagic(User user) {
        this.user = user;
        this.user = new User();
        this.backgroundImageTitleText = new JTextField();
        this.backgroundImageDescriptionTextField = new JTextField();
        this.memeCaptionTextField = new JTextField();
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);
        
        // Panel for generate and save buttons
        genSave = new JPanel(new BorderLayout());
        
        
        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName title and description
        JPanel backgroundImageFilePanel = new JPanel();
        JPanel backgroundImageTitlePanel = new JPanel();
        JPanel backgroundImageDescriptionPanel = new JPanel();
        
        // File Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel, BorderLayout.CENTER);
        
        // Title Label
        JLabel backgroundImageTitleLabel = new JLabel("Title: ");
        backgroundImageTitleLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageTitlePanel.add(backgroundImageTitleLabel, BorderLayout.AFTER_LAST_LINE); //
        
        // Description Label
        JLabel backgroundImageDescriptionLabel = new JLabel("Description: ");
        backgroundImageDescriptionLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionLabel, BorderLayout.WEST);
        
        // Text field
        JTextField backgroundImageTitleText = new JTextField();
        backgroundImageTitlePanel.add(backgroundImageTitleText);
        backgroundImageTitleText.setPreferredSize(new Dimension(200,20));
        backgroundImagePanel.add(backgroundImageDescriptionPanel, BorderLayout.SOUTH);
        
        JTextField backgroundImageDescriptionTextField = new JTextField();
        backgroundImageDescriptionPanel.add(backgroundImageDescriptionTextField, BorderLayout.CENTER);
        backgroundImageDescriptionTextField.setPreferredSize(new Dimension(200,20));
        
        //Add DESCRIPTION Panel to BI Panel
        backgroundImagePanel.add(backgroundImageDescriptionPanel, BorderLayout.SOUTH);
        
        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton, BorderLayout.CENTER); //
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        
        // TODO The button needs a listener
        backgroundImageButton.setActionCommand("Filechange");
        backgroundImageButton.addActionListener(new OpenButtonListener());
        
        // Generate Button
        JButton generate = new JButton("Generate");
        genSave.add(generate, BorderLayout.WEST);
        generate.setPreferredSize(new Dimension(200, 20));
        
        // Generate Button Listener
        generate.setActionCommand("Generate");
        generate.addActionListener(new generateListener());
        
        // Save Button
        JButton save = new JButton("Save");
        genSave.add(save, BorderLayout.EAST);
        save.setPreferredSize(new Dimension(200, 20));
        
        // Save Button Listener
        save.setActionCommand("clickSave");
        save.addActionListener(new SaveButtonListener());
        
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);
        backgroundImagePanel.add(backgroundImageTitlePanel);
        
        //backgroundImagePanel.add(backgroundImageDescriptionLabel);

        

        
        // TODO Complete the Control Panel implementation (with Background Image and Meme panels)
        
        JPanel memePanel = new JPanel(new BorderLayout());
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));
       
        // Meme Caption Panel
        JPanel memeCaptionPanel = new JPanel(new BorderLayout());
        
        // Meme Caption Panel Label 
        JLabel memeCaptionLabel = new JLabel("Caption: ");
        memeCaptionLabel.setPreferredSize(new Dimension(80,15));
        memeCaptionPanel.add(memeCaptionLabel, BorderLayout.WEST);
        
        // Meme captionText Field
        JTextField memeCaptionTextField = new JTextField();
        memeCaptionPanel.add(memeCaptionTextField, BorderLayout.CENTER);
        
        // Add caption Panel to Meme Panel
        memePanel.add(memeCaptionPanel, BorderLayout.NORTH);
        
        // Vertical Align the Meme Panel
        // JPanel memeVerticalAlignPanel= new JPanel(new BorderLayout());
        JPanel memeVerticalAlignPanel= new JPanel();

        // Meme Vertical Align label
        JLabel memeVerticalAlignLabel = new JLabel("Vertical Align: ");
        memeVerticalAlignLabel.setPreferredSize(new Dimension (100, 20));
        
        // memeVerticalAlignPanel.add(memeVerticalAlignLabel, BorderLayout.WEST);
        memeVerticalAlignPanel.add(memeVerticalAlignLabel);

        // M Vertical Align Combo Box
        String[] verticalAlignStrings = {"Top","Middle", "Bottom"};
        JComboBox memeVerticalAligncomboBox = new JComboBox(verticalAlignStrings);
        memeVerticalAligncomboBox.setPreferredSize(new Dimension(300,20));
        
        memeVerticalAlignPanel.add(memeVerticalAligncomboBox);
        //jComboListener myListenerC= new jComboListener();
        memeVerticalAligncomboBox.addActionListener(memeVerticalAligncomboBox);
        memeVerticalAlignPanel.add(memeVerticalAligncomboBox, BorderLayout.CENTER);
        
        //Add to Meme Panel
        
        memePanel.add(memeVerticalAlignPanel, BorderLayout.CENTER);
       
        memePanel.add(memeCaptionPanel, BorderLayout.NORTH);
        memePanel.add(memeVerticalAlignPanel, BorderLayout.CENTER);
        //--------------------
        
        
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        controlPanel.add(memePanel);
        
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);
        panelPane.add(genSave, BorderLayout.SOUTH);
        
        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }
    
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    
    private class generateListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            if(evt.getActionCommand().equals("Generate")) {
            	
	        	String caption = memeCaptionTextField.getText();
	        	String title = backgroundImageTitleText.getText();
	        	String desc = backgroundImageDescriptionTextField.getText();
				String vert = "" + memeVerticalAligncomboBox.getSelectedItem();
	        	
				BackgroundImage back = new BackgroundImage(backgroundImageFilename, title, desc);        	
	            meme = new GraphicalMeme(back,caption,user);
	            meme.setCaptionVerticalAlign(vert);
	            
		            try {
		            	BufferedImage myPicture = meme.compileMeme();
		                imageDisplayLabel = new JLabel(new ImageIcon(myPicture));
		                memeViewPanel.add(imageDisplayLabel);
		                memeViewPanel.repaint();
		                
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Select a file");
					}
            }

        }
    }
    
    
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("clickSave");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                
          
                //       Catch  exceptions to provide the user with an appropriate message
                try {
                	ImageIO.write(meme.compileMeme(500), "png", new File(destinationFile));
                } catch(IOException e) {
                	System.out.println("Invalid Imput or Output!");
                } catch(NullPointerException e) {
                	System.out.println("Create a meme first to save");
                }
            }
        }

    }
}