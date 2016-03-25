import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import users.User;
import users.UserList;


public class Panel_AddUser extends JPanel 
{
	private JTextField usernameTextfield;
	private JTextField passwordTextfield;
	private JTextField badgeNumberTextfield;
	
	private Window_Credential tester;
	
	private UserList employeeList;
	private JTextField nameTextfield;
	
	private User newEmployee;
	
	private ImageIcon addUserImage;

	private JOptionPane userRemovedDialog = new JOptionPane();
	/**
	 * Create the panel.
	 */
	public Panel_AddUser(final UserList employeeList) 
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		URL url1 = getClass().getResource("add_user_medium.png");
		addUserImage = new ImageIcon(url1);
		
		
		JPanel userFieldPanel = new JPanel();
		userFieldPanel.setBackground(new Color(135, 206, 235));
		userFieldPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		userFieldPanel.setBounds(45, 117, 358, 250);
		add(userFieldPanel);
		userFieldPanel.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		usernameLabel.setBounds(35, 23, 87, 14);
		userFieldPanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		passwordLabel.setBounds(35, 66, 87, 14);
		userFieldPanel.add(passwordLabel);
		
		JLabel fullNameLabel = new JLabel("Full Name");
		fullNameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		fullNameLabel.setBounds(35, 107, 87, 14);
		userFieldPanel.add(fullNameLabel);
		
		JLabel userTypeLabel = new JLabel("User Type:");
		userTypeLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		userTypeLabel.setBounds(35, 147, 87, 14);
		userFieldPanel.add(userTypeLabel);
		
		JLabel badgeNumberLabel = new JLabel("Badge Number:");
		badgeNumberLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		badgeNumberLabel.setBounds(22, 197, 113, 14);
		userFieldPanel.add(badgeNumberLabel);
		
		usernameTextfield = new JTextField();
		usernameTextfield.setBounds(144, 20, 161, 20);
		userFieldPanel.add(usernameTextfield);
		usernameTextfield.setColumns(10);
		
		passwordTextfield = new JTextField();
		passwordTextfield.setColumns(10);
		passwordTextfield.setBounds(144, 63, 161, 20);
		userFieldPanel.add(passwordTextfield);
		
		nameTextfield = new JTextField();
		nameTextfield.setColumns(10);
		nameTextfield.setBounds(144, 104, 161, 20);
		userFieldPanel.add(nameTextfield);
		
		final JList userTypeList = new JList();
		userTypeList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Administrator", "Standard"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		userTypeList.setBounds(144, 141, 161, 42);
		userFieldPanel.add(userTypeList);
		
		badgeNumberTextfield = new JTextField();
		badgeNumberTextfield.setColumns(10);
		badgeNumberTextfield.setBounds(145, 194, 161, 20);
		userFieldPanel.add(badgeNumberTextfield);
		
		JLabel addUserImageLabel = new JLabel(addUserImage);
		addUserImageLabel.setIcon(addUserImage);
		addUserImageLabel.setBounds(176, 11, 95, 95);
		add(addUserImageLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(90, 390, 89, 23);
		add(cancelButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		confirmButton.setFont(new Font("Calibri", Font.BOLD, 16));
		confirmButton.setBackground(Color.decode("#98ff98"));
		confirmButton.setBounds(269, 390, 89, 23);
		add(confirmButton);
		
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					tester.swapView("userModPanel");
					tester.setSize(450, 320);
				}
			}
		});
		
		
		
		confirmButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//if any of the fields are empty then 
				if(usernameTextfield.getText().equals("") || passwordTextfield.getText().equals("") || badgeNumberTextfield.getText().equals("")
						|| nameTextfield.getText().equals(""))
				{
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "Please fill out all fields.");
				}
				else
				{
					if(!employeeList.userExists(nameTextfield.getText(), usernameTextfield.getText()))
					{
					newEmployee = new User(nameTextfield.getText(), userTypeList.getSelectedValue().toString(), badgeNumberTextfield.getText(), 
							usernameTextfield.getText(), passwordTextfield.getText());
					
					employeeList.addUser(newEmployee);
					
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "User added!");
					}
					
					if(tester != null)
					{
						tester.swapView("userModPanel");
						tester.setSize(450, 320);
					}
				}
			}
		});

	}
	
	
	//alows for switching between cards
	public void setTester(Window_Credential tester)
	{
		this.tester = tester;
	}
}
