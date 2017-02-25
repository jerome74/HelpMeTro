package it.wlp.android.dialog.element.domain;

import it.wlp.android.dialog.element.IntentElement;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class IntentService<E> implements IntentElement<Service,E>
{
	
	private Context context;
	private Class<Service> serviceClass;
	private Intent intent;
	private HashMap<String,E> extra;
	
	public IntentService() 
	{
		intent = new Intent();
	}
	
	private void init()
	{
		intent.setClass(context, serviceClass);
	
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

	@Override
	public void setContext(Context context) 
	{
		this.context = context;
	}

	@Override
	public void setClass(Class<Service> t) 
	{
		this.serviceClass = t;
	}

	@Override
	public void setExtra(HashMap<String, E> map) 
	{
		extra = map;
	}

	@Override
	public void start() 
	{
		init();
		context.startService(intent);	
	}

}