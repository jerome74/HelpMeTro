package it.mygeo.project.service.alert.signal;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.domain.ConnService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class ConnAlarmSignal extends BroadcastReceiver {

	@Override
	public void onReceive(Context paramContext, Intent paramIntent) 
	{	 
		Intent connection = new Intent(paramContext , ConnService.class);
		
		final ConnectivityManager connMgr 		= (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		final android.net.NetworkInfo wifi 		= connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		final android.net.NetworkInfo mobile 	= connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			
		Log.d(UTIL_GEO.HELPMETRO, "post setup variables and message");
		 

		if (wifi.isAvailable() && wifi.isConnectedOrConnecting()) 
		{	
			Toast.makeText(paramContext, R.string.wifi,Toast.LENGTH_SHORT).show();
		    paramContext.stopService(connection);
		}
		else if (mobile.isAvailable() && mobile.isConnectedOrConnecting()) 
		{
			Toast.makeText(paramContext, R.string.tre_g,Toast.LENGTH_SHORT).show();
		    paramContext.stopService(connection);
		} 
		else 
		{
			Toast.makeText(paramContext, R.string.no_net,Toast.LENGTH_SHORT).show();
		}
	}
}
