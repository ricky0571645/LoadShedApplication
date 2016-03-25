import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import users.User;
import users.UserList;


public class Panel_UserSelection extends JPanel 
{

	private Window_Credential tester;
	private ImageIcon mediumPeopleImage;
	private ImageIcon addUserImage;
	private ImageIcon removeUserImage;
	
	public Panel_UserSelection(final UserList employeeList, final User currentUser) 
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		//IMAGES
		URL url1 = getClass().getResource("peopleicon_medium.png");
		URL url2 = getClass().getResource("add_user_small.png");
		URL url3 = getClass().getResource("remove_user_small.png");
		
		mediumPeopleImage = new ImageIcon(url1);
		addUserImage = new ImageIcon(url2);
		removeUserImage = new ImageIcon(url3);
		
		JButton addUserButton = new JButton("Add User");
		addUserButton.setBackground(Color.WHITE);
		addUserButton.setForeground(new Color(0, 128, 0));
		addUserButton.setIcon(addUserImage);
		addUserButton.setFont(new Font("Calibri", Font.BOLD, 18));
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addUserButton.setBounds(36, 123, 167, 79);
		add(addUserButton);
		
		JButton removeUserButton = new JButton("Remove User");
		removeUserButton.setBackground(Color.WHITE);
		removeUserButton.setForeground(new Color(165, 42, 42));
		removeUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		removeUserButton.setIcon(removeUserImage);
		removeUserButton.setFont(new Font("Calibri", Font.BOLD, 18));
		removeUserButton.setBounds(234, 123, 186, 79);
		add(removeUserButton);
		
		JLabel userIcon = new JLabel("");
		userIcon.setIcon(mediumPeopleImage);
		userIcon.setBounds(178, 11, 94, 101);
		add(userIcon);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(180, 229, 89, 29);
		add(cancelButton);
		
		
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tester != null)
				{
					tester.swapView("controlPanel");
					tester.setSize(510, 480);
				}
			}
		});
		
		addUserButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Panel_AddUser addUserPanel = new Panel_AddUser(employeeList);
				if(tester != null)
				{
					addUserPanel.setTester(tester);
					tester.getCards().add(addUserPanel, "addUserPanel");
					tester.swapView("addUserPanel");
					tester.setSize(450, 500);
				}
			}
		});
		
		removeUserButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Panel_RemoveUser removeUserPanel = new Panel_RemoveUser(employeeList, currentUser);
				if(tester != null)
				{
					removeUserPanel.setTester(tester);
					tester.getCards().add(removeUserPanel, "removeUserPanel");
					tester.swapView("removeUserPanel");
					tester.setSize(450, 500);
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
