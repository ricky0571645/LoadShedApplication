package users;

import java.util.LinkedList;

public class UserList 
{
	private LinkedList<User> list = new LinkedList<User>();
	public int userIndex = 0;
	private boolean userFound = false;
	
	public void addUser(User newEmployee)
	{
		list.add(newEmployee);
	}
	
	public boolean isValidUser(String usernameInput, String passwordInput)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).isEqualToUsername(usernameInput))
			{
				if(list.get(i).isEqualToPassword(passwordInput))
				{
					this.userIndex = i;
					this.userFound = true;
					return true;
				}
			}
		}
		return false;
	}
	
	public int listSize()
	{
		return list.size();
	}
	
	public User getUser()
	{
		if(userFound)
		{
			userFound = false;
			return list.get(userIndex);
		}
		else
			return null;
	}
	
	public LinkedList<User> getUserList()
	{
		return list;
	}
	
	public boolean userExists(String nameInput, String usernameInput)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).isEqualToUsername(usernameInput))
			{
				if(list.get(i).getName().equals(nameInput))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	//Will craeate an import method for reading from a file
}
