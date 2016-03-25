package data;

import java.util.Date;
import java.util.LinkedList;

public class DataList 
{
	LinkedList<Data> list = new LinkedList<Data>();
	private int shutOffCount = 0;
	private int powerOnCount = 0; 	
	
	public void addInstance(String name, String action, Date date)
	{
		list.add(new Data(name, action, date));
	}
	
	public void incrementShutOffCount()
	{
		shutOffCount++;
	}
	
	public int getShutOffCount()
	{
		return this.shutOffCount;
	}
	
	public void incrementPowerOnCount()
	{
		powerOnCount++;
	}
	
	public int getPowerOnCount()
	{
		return this.powerOnCount;
	}
	

}
