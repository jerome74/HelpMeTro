package it.mygeo.project.service.alert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;


public class ServiceAlarm 
{
	
	public ServiceAlarm( Context context , Long elapse, Intent intent  ) 
	{
		
		AlarmManager alarmManager 	=  (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		PendingIntent sender 		= 	PendingIntent.getBroadcast(context, 0, intent, 0);
		
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += elapse;

		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,firstTime,elapse, sender);
	}
	
	private static void init(Context context , Long elapse, Intent intent )
	{
		new ServiceAlarm(context, elapse, intent);
	}
	
	public static void  start(Context context , Long elapse , Intent intent)
	{
		init(context, elapse, intent);
	}
	
	/**
	 * 
	 * @param context
	 * @param intent
	 */
	
	public static void stop(Context context ,Intent intent)
	{
		 PendingIntent sender = PendingIntent.getBroadcast(context , 
                 0, intent, 0);
         
         AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
         am.cancel(sender);
	}
	
}
