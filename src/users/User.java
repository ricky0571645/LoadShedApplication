package users;
public class User 
{
	//instance variables
	private String name;
	private String userType;
	private String badgeNumber;
	private String password;
	private String username;
	
	public User(String name, String title, String badgeNumber, String username, String password)
	{
		setName(name);
		setTitle(title);
		setBadgeNumber(badgeNumber);
		setUsername(username);
		setPassword(password);
	}

	//-----------------------------------------------------------------
	//---------------------------GETTERS-------------------------------
	//-----------------------------------------------------------------
	public String getName() {
		return name;
	}

	public String getUserType() {
		return userType;
	}

	public String getBadgeNumber() {
		return badgeNumber;
	}
	
	//omitted the getPassword method
	
	//-----------------------------------------------------------------
	//---------------------------SETTERS-------------------------------
	//-----------------------------------------------------------------

	private void setName(String name) {
		this.name = name;
	}

	private void setTitle(String userType) {
		this.userType = userType;
	}

	private void setBadgeNumber(String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	
	//will utilize decryption methods
	private void setUsername(String username) {
		this.username = username;
	}
		
	//will utilize decryption methods
	public void setPassword(String password) {
		this.password = password;
	}
	
	//-----------------------------------------------------------------
	//---------------------------FUNCTIONS-----------------------------
	//-----------------------------------------------------------------
	
	boolean isEqualToPassword(String passwordInput)
	{
		if(this.password.equals(passwordInput))
		{
			return true;
		}
		else
			return false;
	}
	
	boolean isEqualToUsername(String usernameInput)
	{
		if(this.username.equals(usernameInput))
		{
			return true;
		}
		else
			return false;
	}
	
	
	

}
