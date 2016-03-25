import java.awt.Color;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import users.User;
import users.UserList;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Panel_RemoveUser extends JPanel 
{
	
	
	private Window_Credential tester;
	private JTable table;
	private DefaultTableModel defaultTable;
	private JOptionPane userRemovedDialog = new JOptionPane();
	private ImageIcon removeUserImage;

	public Panel_RemoveUser(final UserList employeeList, final User currentUser) 
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		URL url1 = getClass().getResource("remove_user_medium.png");
		removeUserImage = new ImageIcon(url1);
		
		JLabel removeUserLabel = new JLabel("");
		removeUserLabel.setIcon(removeUserImage);
		removeUserLabel.setBounds(176, 11, 98, 101);
		add(removeUserLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 136, 331, 245);
		add(scrollPane);
		
		defaultTable = new DefaultTableModel();
		
		defaultTable.addColumn("Name");
		defaultTable.addColumn("Badge Number");
		defaultTable.addColumn("User Type");
		
		table = new JTable();
		table.setModel(defaultTable);
//		table.setModel(new DefaultTableModel
//		(
//			new Object[][] {},
//			new String[] {"Name", "Employee ID", "User Type"}
//		) 
//		{
//			boolean[] columnEditables = new boolean[] {
//				false, true, true
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
		
		buildTable(employeeList, currentUser);
		
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		table.setSelectionMode(selectionModel.SINGLE_SELECTION);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(87, 407, 89, 31);
		add(cancelButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setFont(new Font("Calibri", Font.BOLD, 18));
		removeButton.setBackground(Color.decode("#98ff98"));
		removeButton.setBounds(263, 407, 98, 31);
		add(removeButton);
		
		//return back to "User Modify" panel
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
		
		//remove a user from the "Employee List"
		removeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(employeeList.getUserList().size() - 1 > 0)
				{
					removeUserFromList(employeeList);
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "User successfully removed");
					buildTable(employeeList, currentUser);
				}
				else
					userRemovedDialog.showMessageDialog(null, "No users to remove.");
			}
		});
		
	}
	private void removeUserFromList(UserList employeeList)
	{
		LinkedList<User> userList = employeeList.getUserList();
		User result;
		for(int i = 0; i < userList.size(); i++)
		{
			result = userList.get(i);
			if(result.getName().equals(defaultTable.getValueAt(table.getSelectedRow(), 0)))
				employeeList.getUserList().remove(i);
		}
	}
	
	private void buildTable(UserList employeeList, User currentUser)
	{
		defaultTable.setRowCount(0);
		LinkedList<User> userList = employeeList.getUserList();
		for(int i = 0; i < userList.size(); i++)
		{
			User result = userList.get(i);
			if(!result.getName().equals(currentUser.getName()))
				defaultTable.addRow(new Object[]{result.getName(), result.getBadgeNumber(), result.getUserType()});
		}
	}
	

	//alows for switching between cards
	public void setTester(Window_Credential tester)
	{
		this.tester = tester;
	}
}
