package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.activities.ActivityListArray;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TextListStationEvent implements Observer  ,  PreferenceCallBack
{
	TextView textView;
	ProxyView proxyView;
	String currentValue;
	
	
	View.OnClickListener dialogClickListener = new View.OnClickListener() 
   {
		public void onClick(View arg0) 
		{
			Intent intent = new Intent(proxyView.getContext(), ActivityListArray.class);
			
			if(((ToggleButton)proxyView.getActivity().findViewById(R.id.metroAButton)).getText().equals(proxyView.getActivity().getString(R.string.metroA_on)))
			{
				intent.putExtra(UTIL_GEO.VALUE_METRO, UTIL_GEO.METRO_A);
				proxyView.getContext().startActivity(intent);
			}
			else if(((ToggleButton)proxyView.getActivity().findViewById(R.id.metroBButton)).getText().equals(proxyView.getActivity().getString(R.string.metroB_on)))
			{
				intent.putExtra(UTIL_GEO.VALUE_METRO, UTIL_GEO.METRO_B);
				proxyView.getContext().startActivity(intent);
			}		
			else
			{
				Toast.makeText(proxyView.getActivity(), R.string.no_metro,
						Toast.LENGTH_SHORT).show();
			}
		}
   };

	public TextListStationEvent(View textView) 
	{
		super();
		this.textView = (TextView)textView;
//		this.textView.setTextColor(Color.WHITE);
//		this.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//		this.textView.setTypeface(null, Typeface.BOLD_ITALIC);
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		
		textView.setOnClickListener(dialogClickListener);
		NotifyBean.createEvent(UTIL_GEO.NB_ListElementEvent, this);
	}
	

	@Override
	public void returnServiceResponse(Message msg) 
	{
		handler.sendMessage(msg);
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
			final Message msg1 = msg;
			try 
			{
				proxyView.getActivity().runOnUiThread(new Runnable() 
                {
                    public void run() 
                    {
                    		textView.setText((String)msg1.obj);
                    }
                });
			}
			catch (Exception e) 
			{
				Log.w(UTIL_GEO.HELPMETRO,  e);
			}
		}
	};
}

