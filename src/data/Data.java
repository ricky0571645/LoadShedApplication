package data;

import java.util.Date;


	
public class Data 
{
	//instance variables
	private String name;
	private String action;
	private Date date;
	
	//assign date to constructor
	public Data(String name, String action, Date date)
	{
		this.name = name;
		this.action = action;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}

	public String getAction() {
		return action;
	}

	public Date getDate() {
		return date;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
}
