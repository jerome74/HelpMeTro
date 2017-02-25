package it.mygeo.project.service;

import it.mygeo.project.R;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.service.enu.ServiceState;
import it.mygeo.project.service.external.PreferenceCallBack;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public  abstract class  ServiceManager  extends Service 
{
	private WakeLock sWakeLock;
	protected boolean acquire = true;

	/**
	 * @method onCreate
	 * @args
	 * @return void
	 */
	
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	
	/**
	 * @method acquireWakeLock
	 * @args ctx, lock
	 * @return void
	 */
	
	private void acquireWakeLock(Context ctx, String lock) 
    {
        if (sWakeLock == null) 
        {
            PowerManager pMgr = (PowerManager) ctx.getSystemService(POWER_SERVICE);
            sWakeLock = pMgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, lock);
        }
        
        sWakeLock.acquire();
    }
    
	/**
	 * @method releaseWakeLock
	 * @args ctx
	 * @return void
	 */
	
    protected void releaseWakeLock(Context ctx) 
    {
        sWakeLock.release();
    }

	/**
	 * @method onStart
	 * @args intent, startId
	 * @return void
	 */
	
	@Override
	public int onStartCommand(Intent intent, int flags , int startId)
	{
		super.onStartCommand(intent, flags, startId);
		
		/*************/
		doExecute()
		/*************/;
		
		return START_STICKY ;
	}
	
	
	/**
	 * @method doExecute
	 * @return void
	 * @param void
	 * @throws none
	 */
	
	public  ServiceState  doExecute() 
	{		
			if(acquire)
				acquireWakeLock(this, this.getString(R.string.LOCK));
				
			return execute();
	}
	
	/**
	 * @method doExecute
	 * @return void
	 * @param void
	 * @throws none
	 */
	
	public abstract ServiceState execute();
	public abstract ServiceState terminate();
}
