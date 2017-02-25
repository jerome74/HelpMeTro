package it.mygeo.project.service.robot.metro.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Minute 
{
	private SimpleDateFormat simpleDateFormat = null;
	private Date date = null;
	private int minute;
	private int frazione;
	private long time;
	private final static String DATE = "01/01/2000 00:00:00";
	
	public Minute(int frazione) 
	{
		simpleDateFormat = new SimpleDateFormat("mm:ss");
		this.frazione = frazione;
	}
	


	public String getMinute() 
	{
		if(minute > 0)
		{
			String min = simpleDateFormat.format(new Date(minute * this.frazione * 1000));
			minute--;
			return min;
		}
		
		return "00:00";
		
	}
	
	public void setMinute(int minute) 
	{	
		this.minute = minute;
	}
	
	public String minuteIs() 
	{
		return simpleDateFormat.format(new Date(minute * this.frazione * 1000));
	}
	
	

}
