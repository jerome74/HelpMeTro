package it.mygeo.project.handler;

import it.mygeo.project.activities.HelpMeTroActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class SplashHandler extends Handler 
{
	
	private Context context;
	private Activity activity;
	
	
	
	public SplashHandler(Context context , Activity activity) 
	{
		super();
		this.context = context;
		this.activity = activity;
	}



	public void handleMessage(Message msg) 
 {
		super.handleMessage(msg);

		Intent intent = new Intent();

		switch (msg.what) {
		case 0:
			intent.setClass(this.context, HelpMeTroActivity.class);
			break;

		default:
			break;
		}

		this.context.startActivity(intent);
		this.activity.finish();

	}	
}
