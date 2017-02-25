package it.mygeo.project.service.domain;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.ServiceManager;
import it.mygeo.project.service.alert.ServiceAlarm;
import it.mygeo.project.service.alert.signal.ConnAlarmSignal;
import it.mygeo.project.service.enu.ServiceState;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ConnService extends ServiceManager 
{
	
	private static Context context;
	private Intent intent;

	
	
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{		
		Log.d(UTIL_GEO.HELPMETRO, "ConnService : onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}




	@Override
	public ServiceState execute() 
	{	
		intent = new Intent(this, ConnAlarmSignal.class);
		
		ServiceAlarm.start(this, (long)5000, intent);	
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
		
		Log.d(UTIL_GEO.HELPMETRO,  this.getClass().getName() + " : onDestroy()");
		ServiceAlarm.stop(this, new Intent(this, ConnAlarmSignal.class));
		
		try 
		{
			NotifyBean.notifyEvent(UTIL_GEO.NB_ConnetionEvent);
		}
		catch (Exception e) 
		{
			Log.w(UTIL_GEO.HELPMETRO,  e);
		}
		
		super.onDestroy();
	}




	public static Context getContext() {
		return context;
	}




	public static void setContext(Context context) {
		ConnService.context = context;
	}




	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public ServiceState terminate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
