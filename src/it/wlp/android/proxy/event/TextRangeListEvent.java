package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.activities.ActivityRangeListArray;
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

public class TextRangeListEvent implements Observer  ,  PreferenceCallBack
{
	TextView textView;
	ProxyView proxyView;
	String currentValue;
	
	
	View.OnClickListener dialogClickListener = new View.OnClickListener() 
   {
		public void onClick(View arg0) 
		{	
			Intent intent = new Intent(proxyView.getContext(), ActivityRangeListArray.class);
			CharSequence value = ((TextView)proxyView.getActivity().findViewById(R.id.idle_text_range_station)).getText();
			intent.putExtra(UTIL_GEO.DEF_SEEK, value);
			proxyView.getContext().startActivity(intent);
		}
   };

	public TextRangeListEvent(View textView) 
	{
		super();
		this.textView = (TextView)textView;
//		this.textView.setTextColor(Color.WHITE);
//		this.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//		this.textView.setTypeface(null, Typeface.BOLD_ITALIC);
//		this.textView.setBackgroundColor(Color.TRANSPARENT);
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		
		textView.setOnClickListener(dialogClickListener);
		NotifyBean.createEvent(UTIL_GEO.NB_SeekBarDistanceEvent, this);
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
                    		String value = (String)msg1.obj;
                    		textView.setText(value.subSequence(0, value.indexOf(";")));
                    		TextView idle_text = (TextView)proxyView.getActivity().findViewById(R.id.idle_text_range_station);
                    		idle_text.setText(value.subSequence(value.indexOf(";") +1 , value.length()));
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

