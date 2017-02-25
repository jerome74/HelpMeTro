package it.mygeo.project.service.robot;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.ServiceManager;
import it.mygeo.project.service.enu.ServiceState;
import it.mygeo.project.service.robot.metro.runner.GenericRunnerInsteadMetro;
import it.mygeo.project.service.robot.metro.runner.GenericRunnerMetro;
import it.wlp.android.system.bean.ContainerG30Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

public class GenericServiceMetro extends ServiceManager{

	private static Context context;
	private Intent intent;
	private IBinder mBinder = new LocalBinder();
	ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
	private GenericRunnerMetro genericRunnerMetro;
	private GenericRunnerInsteadMetro genericRunnerInsteadMetro;





	@Override
	public int onStartCommand(Intent intent, int flags, int startId) 
	{		
		acquire = false;
		
		Log.d(UTIL_GEO.HELPMETRO, "MetroServiceBotAnagninaToBattistini : onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
	
	@Override
	public ServiceState execute() 
	{		
		try 
		{
			genericRunnerMetro = new GenericRunnerMetro(NotifyBean.getMap(UTIL_GEO.NB_GoogleMap)
														, NotifyBean.getElementsC_G30Bean(UTIL_GEO.NB_Stations)
														, NotifyBean.getEvent(UTIL_GEO.NB_ActivityGoogleMap));	
			
			genericRunnerInsteadMetro = new GenericRunnerInsteadMetro(NotifyBean.getMap(UTIL_GEO.NB_GoogleMap)
														, NotifyBean.getElementsC_G30Bean(UTIL_GEO.NB_Stations)
														, NotifyBean.getEvent(UTIL_GEO.NB_ActivityGoogleMap));	
			
			scheduler.scheduleWithFixedDelay(genericRunnerMetro , 10,  30 , TimeUnit.SECONDS);
			scheduler.scheduleWithFixedDelay(genericRunnerInsteadMetro , 10,  30 , TimeUnit.SECONDS);
		}
		catch (Exception e) 
		{
			Log.w(UTIL_GEO.HELPMETRO,  e);
		}
		
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
		if(scheduler != null && !scheduler.isShutdown())
			scheduler.shutdown();

		
		super.onDestroy();
	}
	
	
	public static Context getContext() {
		return context;
	}
	
	
	public static void setContext(Context context) 
	{
		GenericServiceMetro.context = context;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public GoogleMap getMap()
	{
		return NotifyBean.getMap(UTIL_GEO.NB_GoogleMap);
	}
	
	/**
	 * 
	 * @return
	 */
	
	public ContainerG30Bean getG30Bean()
	{
		return NotifyBean.getElementsC_G30Bean(UTIL_GEO.NB_Stations);
	}


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * 
     * @author jerome
     *
     */
    
    public class LocalBinder extends Binder 
    {
        public GenericServiceMetro getServerInstance() 
        {
            return GenericServiceMetro.this;
        }
    }

	@Override
	public ServiceState terminate() 
	{
		if(scheduler != null && !scheduler.isShutdown())
			scheduler.shutdown();
		
		return ServiceState.CANCELED;
	}
}