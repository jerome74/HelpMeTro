package it.mygeo.project.service.domain;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.AccessibilityServiceManager;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.enu.ServiceState;
import android.content.Intent;
import android.util.Log;

public class CatchToastService extends AccessibilityServiceManager
{
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{		
		Log.d(UTIL_GEO.HELPMETRO, "CatchToastService : onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public ServiceState execute() 
	{		
		return ServiceState.STARTED;
	}
	
	
	
	
	/**
	 * @method onDestroy
	 * @args
	 * @return void
	 */
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}
	

/**
 * @method: updateActivity
 * @args:
 * @return:void
 */

	@Override
	protected void updateActivity() 
	{
		if(intent.getBooleanExtra(UTIL_GEO.IS_CONN_SERV, false))
		{
			Log.d(UTIL_GEO.HELPMETRO, "CatchToastService : updateActivity()");

			try 
			{
				NotifyBean.notifyEvent(UTIL_GEO.NB_ConnetionEvent);
			}
			catch (Exception e) 
			{
				Log.w(UTIL_GEO.HELPMETRO,  e);
			}
		}
	}
}
