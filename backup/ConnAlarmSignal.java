package it.mygeo.project.service.alert.signal;

import it.mygeo.project.R;
import it.mygeo.project.activities.MapNowG30Activity;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ConnAlarmSignal extends BroadcastReceiver {

	@Override
	public void onReceive(Context paramContext, Intent paramIntent) 
	{	
		
		//View view = View.inflate(paramContext, R.layout.main, null);     
		
//		 final ProgressBar progressBar 	= (ProgressBar)view.findViewById(R.id.prog_conn);
//		 final Button button 					= (Button)view.findViewById(R.id.searchPropertiesButton);
		 
		 final ConnectivityManager connMgr 		= (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		 final android.net.NetworkInfo wifi 		= connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		 final android.net.NetworkInfo mobile 	= connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (wifi.isAvailable() && wifi.isConnectedOrConnecting()) 
		{	
			Toast.makeText(MapNowG30Activity.this, R.string.wifi,
					Toast.LENGTH_SHORT).show();
		    toastHelperDomain.createToastMessage(R.string.wifi, R.drawable.go);
			
//		    progressBar.setVisibility(View.GONE);
//			button.setVisibility(View.VISIBLE);
			
			/*************************************************************/
			NotifyBean.notifyEvent(SERVICES.CHECK_STATUS_CONNECTION);
			/*************************************************************/
		} 
		else if (mobile.isAvailable() && mobile.isConnectedOrConnecting()) 
		{
	
		    toastHelperDomain.createToastMessage(R.string.tre_g, R.drawable.go);
						
//			progressBar.setVisibility(View.GONE);
//			button.setVisibility(View.VISIBLE);
			
			/*************************************************************/
			NotifyBean.notifyEvent(SERVICES.CHECK_STATUS_CONNECTION);
			/*************************************************************/
		} 
		else 
		{
		    toastHelperDomain.createToastMessage(R.string.no_net, R.drawable.stop);
		}
	}
}
