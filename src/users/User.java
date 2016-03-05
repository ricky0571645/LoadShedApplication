package users;
public class User 
{
	//instance variables
	private String name;
	private String title;
	private int badgeNumber;
	private String password;
	private String username;
	
	public User(String name, String title, int badgeNumber, String username, String password)
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

	public String getTitle() {
		return title;
	}

	public int getBadgeNumber() {
		return badgeNumber;
	}
	
	//omitted the getPassword method
	
	//-----------------------------------------------------------------
	//---------------------------SETTERS-------------------------------
	//-----------------------------------------------------------------

	private void setName(String name) {
		this.name = name;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}
	
	//will utilize decryption methods
	private void setUsername(String username) {
		this.username = username;
	}
		
	//will utilize decryption methods
	private void setPassword(String password) {
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
