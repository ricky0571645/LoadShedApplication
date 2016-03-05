package users;

import java.util.LinkedList;

public class UserList 
{
	LinkedList<User> list = new LinkedList<User>();
	
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
					return true;
			}
		}
		return false;
	}
	
	//Will craeate an import method for reading from a file
}
