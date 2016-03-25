import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.User;//--------------FOR DEBUGGING-----------
import users.UserList;
import data.DataList;
public class Window_Credential extends JFrame
{
	//-----------------------------------------------------------------
	//-----------------------VARIABLE DECLARATIONS---------------------
	//-----------------------------------------------------------------
	//Textfields
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	//Labels
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel wrongCredentialsLabel;
	
	//Graphic Icon
	ImageIcon logoImage;
	
	//Buttons
	private JButton submitButton;
	
	//Insets
	private Insets insets;
	
	//GUI setup for contents and cardLayout implementation
	private Container contents;
	private CardLayout cardLayout = new CardLayout();
	private JPanel cards = new JPanel(cardLayout);
	private JPanel credentialPanel;
	
	
	//Allows transition between windows
	private Window_Credential tester;
	
	//For row clarification
	private static final int ROW1 = 0;
	private static final int ROW2 = 1;
	private static final int ROW3 = 2;
	private static final int ROW4 = 3;
	private static final int ROW5 = 4;
	
	//include the userlist that will be used to verify the incoming user
	private UserList employeeList = new UserList();
	private DataList dataList = new DataList();
	private User currentUser;
	
	
	//--------------FOR DEBUGGING-----------
	//--------------------------------------
	boolean clickedTwice = false;
	private User employee1 = new User("Ricky Martinez", "Administrator", "100", "ricky", "apple");
	private User employee2 = new User("Justin Kelley", "Standard", "101", "justin", "pear");
	private User employee3 = new User("Marshall Bauguss", "Standard", "102", "marshall", "berry");
	private User employee4 = new User("Julio Crespo", "Standard", "103", "julio", "grape");
	
	private boolean poweredOn = true;
	
	
	//-----------------------------------------------------------------
	//---------------------------GUI SETUP-----------------------------
	//-----------------------------------------------------------------
	public Window_Credential()
	{	
		super("Load Shed Controller 1.0");
		contents = getContentPane();
		tester = this;
		
		
		credentialPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		insets = new Insets(0,0,0,0);
		
		//declare the image as type imageIcon
		
		URL url =getClass().getResource("SACSTATE.png");
		logoImage = new ImageIcon(url);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		//created this to center image and button to the credential prompt
		int imageButtonToLeft = 20;
		
		//add the image to the top
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0;
		constraint.gridwidth = 2;
		constraint.ipadx = 0;  
		constraint.ipady = 0;
		insets.top = 0;
		insets.left = imageButtonToLeft;
		constraint.insets = insets;
		constraint.gridx = 0;
		constraint.gridy = ROW1;
		credentialPanel.add(new JLabel(logoImage), constraint);
		
		//Add label for user name
		usernameLabel = new JLabel("Username: ");
		constraint.fill = GridBagConstraints.NONE;
		constraint.weightx = 0.5;
		constraint.gridwidth = 1;
		constraint.ipadx = 10;  
		constraint.ipady = 10;
		insets.top = 40;
		insets.left = 50;
	    constraint.gridx = 0;
	    constraint.gridy = ROW2;
	    credentialPanel.add(usernameLabel, constraint);
	    
	    //Add text field for user name
	    usernameField = new JTextField("");
	    constraint.fill = GridBagConstraints.NONE;
	    constraint.weightx = 0.5;
		constraint.gridwidth = 1;
	    constraint.ipadx = 150;
	    constraint.ipady = 10;
	    insets.top = 40;
		insets.left = 0;
  	    constraint.gridx = 1;
  	    constraint.gridy = ROW2;
  	    credentialPanel.add(usernameField, constraint);
  	 
  	    
	  	//Add label for password
  	    passwordLabel = new JLabel("Password: ");
  		constraint.fill = GridBagConstraints.NONE;
  		constraint.weightx = 0.5;
		constraint.gridwidth = 1;
		constraint.ipadx = 10;
	    constraint.ipady = 10;
  		insets.top = 15;
  		insets.left = 50;
	    constraint.gridx = 0;
	    constraint.gridy = ROW3;
	    credentialPanel.add(passwordLabel, constraint);
	    
	    //Add text field for password
	    passwordField = new JPasswordField("");
	    constraint.fill = GridBagConstraints.NONE;
	    constraint.weightx = 0.5;
		constraint.gridwidth = 1;
	    constraint.ipadx = 150;
	    constraint.ipady = 10;
	    insets.top = 15;
  		insets.left = 0;
	    constraint.gridx = 1;
	    constraint.gridy = ROW3;
	    credentialPanel.add(passwordField, constraint);
	    
	    //Add button for 
	    submitButton = new JButton("Enter");
	    submitButton.setForeground(Color.white);
	    submitButton.setBackground(Color.decode("#7ec0ee"));
	    constraint.fill = GridBagConstraints.NONE;
	    constraint.weightx = 0;
		constraint.gridwidth = 2;
		constraint.ipadx = 10;
	    constraint.ipady = 10;
	    insets.top = 20;
	    insets.left = imageButtonToLeft;
	    constraint.gridx = 0;
	    constraint.gridy = ROW4;
	    credentialPanel.add(submitButton, constraint);
	    
	    wrongCredentialsLabel = new JLabel("Incorrect username/password combination.");
	    wrongCredentialsLabel.setVisible(false);
	    wrongCredentialsLabel.setForeground(Color.red);
  		constraint.fill = GridBagConstraints.NONE;
  		constraint.weightx = 0;
		constraint.gridwidth = 2;
		constraint.ipadx = 10;
	    constraint.ipady = 10;
  		insets.top = 15;
	    insets.left = 10;
	    constraint.gridx = 0;
	    constraint.gridy = ROW5;
	    credentialPanel.add(wrongCredentialsLabel, constraint);
	    
	    //add all of the items to the card view
	    credentialPanel.setBackground(Color.white);
  	    cards.add(credentialPanel, "credentialPanel");
	    contents.add(cards);
		setSize(350, 400);
		setVisible(true);
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//center the GUI on the screen
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = toolkit.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    setLocation(screenWidth / 4, screenHeight / 4);
	
		//-----------------------------------------------------------------
		//---------------------------BUTTON ACTION-------------------------
		//-----------------------------------------------------------------
	    
	    
	    employeeList.addUser(employee1);//--------------FOR DEBUGGING-----------
	    employeeList.addUser(employee2);//--------------FOR DEBUGGING-----------
	    employeeList.addUser(employee3);//--------------FOR DEBUGGING-----------
	    employeeList.addUser(employee4);//--------------FOR DEBUGGING-----------
	    
	    Action action = new AbstractAction()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	//click twice for debug//--------------FOR DEBUGGING-----------
				//if(clickedTwice || employeeList.isValidUser(usernameField.getText(), passwordField.getText()))
				if(employeeList.isValidUser(usernameField.getText(), passwordField.getText()))
				{
					currentUser = employeeList.getUser();
					Panel_Control panel = new Panel_Control(employeeList, dataList, currentUser, poweredOn);
					cards.add(panel, "controlPanel");
					panel.setTester(tester);
					swapView("controlPanel");
					setSize(480, 480);
					setVisible(true);
					setResizable(false);
					resetWindow();
				}
				else
				{
					resetWindow();
					wrongCredentialsLabel.setVisible(true);
				}
				
				clickedTwice = true;//--------------FOR DEBUGGING-----------
	        }
	    };
	    
	    passwordField.addActionListener(action);
		submitButton.addActionListener(action); 
		
}
	
	
	public void setPoweredOnValue(boolean isPoweredOn)
	{
		poweredOn = isPoweredOn;
	}
	
	private void resetWindow()
	{
		usernameField.setText("");
		usernameField.requestFocus();
		passwordField.setText("");
		wrongCredentialsLabel.setVisible(false);
	}
	
	public void swapView(String key) 
	{
	      cardLayout.show(cards, key);
	}
	
	public JPanel getCards()
	{
		return cards;
	}
	
	
	
}
