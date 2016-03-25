import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import users.User;
import users.UserList;


public class Panel_PasswordModifier extends JPanel 
{
	private JLabel wrongCombinationLabel;
	private JTextField usernameTextField;
	private JPasswordField oldPasswordField;
	private JPasswordField newPasswordField;
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	private Window_Credential tester;
	private JOptionPane changeSuccessful = new JOptionPane();
	private ImageIcon mediumLockImage;
	
	private UserList employeeList;
	private User currentUser;

	/**
	 * Create the panel.
	 */
	public Panel_PasswordModifier(final UserList employeeList, final User currentUser) 
	{
		this.employeeList = employeeList;
		this.currentUser = currentUser;
		
		setBackground(Color.WHITE);
		setLayout(null);

		URL url1 = getClass().getResource("lockIconMedium.png");
		mediumLockImage = new ImageIcon(url1);
		JLabel lockIcon = new JLabel("");
		lockIcon.setIcon(mediumLockImage);
		lockIcon.setBounds(182, 11, 91, 93);
		add(lockIcon);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(227, 131, 115, 20);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		
		final JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		usernameLabel.setBounds(115, 134, 70, 14);
		add(usernameLabel);
		
		JLabel oldPasswordLabel = new JLabel("Old Password");
		oldPasswordLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		oldPasswordLabel.setBounds(113, 176, 104, 14);
		add(oldPasswordLabel);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(227, 173, 115, 20);
		add(oldPasswordField);
		
		JLabel newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		newPasswordLabel.setBounds(115, 214, 102, 14);
		add(newPasswordLabel);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(227, 211, 115, 20);
		add(newPasswordField);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBorder(emptyBorder);
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(117, 266, 89, 23);
		add(cancelButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Calibri", Font.BOLD, 18));
		confirmButton.setBorder(emptyBorder);
		confirmButton.setBackground(Color.decode("#98ff98"));
		confirmButton.setBounds(252, 266, 89, 23);
		add(confirmButton);
		
		wrongCombinationLabel = new JLabel("Incorrect username/password combination.");
		wrongCombinationLabel.setVisible(false);
		wrongCombinationLabel.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		wrongCombinationLabel.setForeground(Color.RED);
		wrongCombinationLabel.setBounds(102, 241, 250, 14);
		add(wrongCombinationLabel);
		
		
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					tester.swapView("controlPanel");
					tester.setSize(530, 500);
				}
			}
		});
		
		Action action = new AbstractAction()
	    {
	        
			public void actionPerformed(ActionEvent e) 
			{
				if(employeeList.isValidUser(usernameTextField.getText(), oldPasswordField.getText()))
				{
					currentUser.setPassword(newPasswordField.getText());
					
					changeSuccessful.setLayout(getLayout());
					changeSuccessful.showMessageDialog(null, "Password Change Successful");
					resetWindow();
					if(tester != null)
					{
						tester.swapView("controlPanel");
						tester.setSize(530, 500);
					}
				}
				else
				{
					wrongCombinationLabel.setVisible(true);
					resetWindow();
				}
					
				
			}
		};
		
		newPasswordField.addActionListener(action);
		confirmButton.addActionListener(action); 


	}
	
	public void resetWindow()
	{
		usernameTextField.setText("");
		oldPasswordField.setText("");
		newPasswordField.setText("");
		wrongCombinationLabel.setVisible(false);
	}
	
	//alows for switching between cards
	public void setTester(Window_Credential tester)
	{
		this.tester = tester;
	}
}
