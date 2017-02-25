package it.wlp.android.dialog.element.domain;

import it.wlp.android.dialog.element.IntentElement;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentActivity<E> implements IntentElement<Activity,E>
{
	
	private Context context;
	private Class<Activity> activityClass;
	private Intent intent;
	private HashMap<String,E> extra;
	
	public IntentActivity() 
	{
		intent = new Intent();
	}
	
	private void init()
	{
		intent.setClass(context, activityClass);
	
		if(extra != null)
			for (Iterator<String> keysExtra = extra.keySet().iterator(); keysExtra.hasNext();) 
			{
				String key 		= keysExtra.next();
				Object objExtra = extra.get(key);
				
				if(objExtra instanceof String) 
					intent.putExtra(key, (String)objExtra);	
				else if(objExtra instanceof Integer) 
					intent.putExtra(key, (Integer)objExtra);	
				else if(objExtra instanceof String[]) 
					intent.putExtra(key, (String[])objExtra);	
			}
	}


	public void setContext(Context context) 
	{
		this.context = context;
	}



	public void setExtra(HashMap<String, E> map) 
	{
		extra = map;
	}


	public void start() 
	{
		init();
		context.startActivity(intent);	
	}


	public void setClass(Class<Activity> clIntent) 
	{
		this.activityClass = clIntent;
	}
}
