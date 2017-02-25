package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.activities.VoiceActivity;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VoiceRangeStationEvent  implements Observer  ,  PreferenceCallBack , OnInitListener
{
	ImageView imageView;
	TextView  textView;
	ProxyView proxyView;
	private TextToSpeech tts;
	
	private int SPEECH_REQUEST_CODE = 1234;
	
	
	View.OnClickListener dialogClickListener = new View.OnClickListener() 
   {
		public void onClick(View arg0) 
		{
			Intent intent = new Intent(proxyView.getContext(), VoiceActivity.class);
			intent.putExtra(UTIL_GEO.EXTRA_PROMPT, proxyView.getActivity().getString(R.string.rec_range_station));
			intent.putExtra(UTIL_GEO.NB_TO_NOTIFY,UTIL_GEO.NB_VoiceSeek_NOTIFY);
			proxyView.getContext().startActivity(intent);
			
		}
   };

	public VoiceRangeStationEvent(View imageView , View textView) 
	{
		super();
		this.imageView 	= (ImageView)imageView;
		this.textView 	= (TextView)textView;
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		
		tts = new TextToSpeech(proxyView.getContext(), this);
		
		imageView.setOnClickListener(dialogClickListener);
		NotifyBean.createEvent(UTIL_GEO.NB_VoiceSeek, this);
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
                    		String messaggio = (String)msg1.obj;
                    		
                    		if(messaggio != null)
                    		{
                    			if(messaggio.equals(proxyView.getActivity().getString(R.string.vx_error))
                    			|| messaggio.equals(proxyView.getActivity().getString(R.string.vx_not_ok))
                    			|| messaggio.equals(proxyView.getActivity().getString(R.string.vx_not_valid)))
                    			{
                    				tts.speak(messaggio, TextToSpeech.QUEUE_FLUSH, null);
                    			}
                    			else
                    			{
                    				Resources r = proxyView.getActivity().getResources();
                    		        String[] bases = r.getStringArray(R.array.stations_a);
                    		        
                    		        boolean found = false;
                    		        
                    		        for (String obj : bases) 
                    		        {
                    		        	if(obj.equalsIgnoreCase(messaggio))
                    		        	{
                    		        		found = true;
                    		        		messaggio = obj;
                    		        		break;
                    		        	}
									}
                    		        
                    		        if (found) 
                    		        {
                    		        	tts.speak(messaggio, TextToSpeech.QUEUE_FLUSH, null);
                    		        	textView.setText(messaggio);
                    		        	
									} 
                    		        else
                    		        {
                    		        	tts.speak(proxyView.getActivity().getString(R.string.vx_no_obj), TextToSpeech.QUEUE_FLUSH, null);
									}
                    			}
                    		}
                    		else
                    		{
                    			tts.speak(proxyView.getActivity().getString(R.string.vx_no_obj), TextToSpeech.QUEUE_FLUSH, null);
                    		}
                    		
                    }
                });
			}
			catch (Exception e) 
			{
				Log.w(UTIL_GEO.HELPMETRO,  e);
			}
		}
	};

	 	@Override
	    public void onInit(int status)
	    {
	    }
}

