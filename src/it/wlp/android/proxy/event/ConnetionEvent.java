package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.handler.ServiceHandler;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.domain.ConnService;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;

import java.util.Observable;
import java.util.Observer;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class ConnetionEvent implements Observer ,  PreferenceCallBack
{
	private ProxyView proxyView;
	private Intent connection;
	private ServiceHandler serviceHandler;
	private Intent accessibility;
	private Message startMessage;
	
	
	public ConnetionEvent() 
	{
		super();
	}

	public void update(Observable arg0, Object arg1)
	{
		try 
		{
			proxyView = (ProxyView)arg0;
			
			connection = new Intent(proxyView.getContext() , ConnService.class);
			
			connection.putExtra(UTIL_GEO.IS_CONN_SERV, true);
			
			serviceHandler = new ServiceHandler(proxyView.getContext(), proxyView.getActivity());
			
			startMessage = new Message();
			
			startMessage.arg1 = SERVICES.START;
			
			serviceHandler.handleMessage(startMessage,connection);
			
			NotifyBean.createEvent(UTIL_GEO.NB_ConnetionEvent, this);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void returnServiceResponse() 
	{
		handler.sendEmptyMessage(0);
	}
	
	/**
	 * 
	 */ 
	
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			try 
			{
				proxyView.getActivity().runOnUiThread(new Runnable() 
                {
                    public void run() 
                    {
                    	Log.d(UTIL_GEO.HELPMETRO,  "on handleMessage");
                    	
                    	final ProgressBar progressBar 	= (ProgressBar)proxyView.getActivity().findViewById(R.id.prog_conn);
        				final TextView textViewButton 	= (TextView)proxyView.getActivity().findViewById(R.id.searchPropertiesButton);
        				
        				progressBar.setVisibility(View.GONE);
        				textViewButton.setVisibility(View.VISIBLE);	
        				
        				
        				startMessage.arg1 = SERVICES.STOP;
        				
        				serviceHandler.handleMessage(startMessage,connection);
        				serviceHandler.handleMessage(startMessage, accessibility);
                    }
                });
			}
			catch (Exception e) 
			{
				Log.w(UTIL_GEO.HELPMETRO,  e);
			}
			
		}
	};
	
	
	
	public boolean isMyServiceRunning() {
	    ActivityManager manager = (ActivityManager) proxyView.getActivity()
	    																	.getSystemService(proxyView.getContext().ACTIVITY_SERVICE);
	    
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) 
	    {
	        if (ConnService.class.getName().equals(service.service.getClassName())) 
	        {
	        	  return false;
	        }
	    }
	  return true;
	}

	@Override
	public void returnServiceResponse(Message msg) {
		// TODO Auto-generated method stub
		
	}
	
}