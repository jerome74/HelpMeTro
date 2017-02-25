package it.mygeo.project.handler;

import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.constants.UTIL_GEO;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ServiceHandler extends Handler implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Context context;

	public ServiceHandler(Context context,  Activity activity) 
	{
		super();
		this.context = context;
	}

	public void handleMessage(Message msg, Intent intent) 
	{
		super.handleMessage(msg);

		switch (msg.arg1) {
		case SERVICES.START:
			context.startService(intent);
			break;
			
		case SERVICES.STOP:
			Log.d(UTIL_GEO.HELPMETRO, "on SERVICES.STOP");

			if(intent != null && intent.getComponent() != null)
				context.stopService(intent);
			break;
		default:
			break;
		}
	}
	
}
