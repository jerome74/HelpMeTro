package it.wlp.android.proxy.event;

import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_KM;
import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_KMS;
import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_METRS;
import static it.mygeo.project.constants.UTIL_GEO.PRE_LABEL_DIST;
import it.mygeo.project.R;
import it.mygeo.project.activities.HelpMeTroActivity;
import it.mygeo.project.activities.MapHelpMeTroActivity;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ButtonSearchEvent implements Observer ,  PreferenceCallBack
{
	
	private TextView textViewSearch;
	private ProxyView proxyView;
	private IToastHelper iToastHelper;
	private ToastHelperDomain toastHelperDomain;
	
	
	public ButtonSearchEvent(View textViewSearch) 
	{
		super();
		this.textViewSearch = (TextView)textViewSearch;
	}
	
	@Override
	public void returnServiceResponse() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		textViewSearch.setOnClickListener(clickListener);
//		this.textViewSearch.setTextColor(Color.WHITE);
//		this.textViewSearch.setTypeface(null, Typeface.BOLD_ITALIC);
		this.textViewSearch.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		 
		iToastHelper 		= new ToastHelper(proxyView.getContext());
		toastHelperDomain 	= new ToastHelperDomain(iToastHelper);
	}
	
	View.OnClickListener clickListener = new View.OnClickListener() 
	   {
			public void onClick(View arg0) 
			{
				
				if(!(((ToggleButton)proxyView.getActivity().findViewById(R.id.metroAButton)).getText()).equals(proxyView.getActivity().getString(R.string.metroA_on))
					&&	!(((ToggleButton)proxyView.getActivity().findViewById(R.id.metroBButton)).getText()).equals(proxyView.getActivity().getString(R.string.metroB_on))	)
				{
					Toast.makeText(proxyView.getActivity(), R.string.check_search_button,Toast.LENGTH_SHORT).show();
				}
				else if(((TextView)proxyView.getActivity().findViewById(R.id.text_station_list)).getText().toString()
						.indexOf(proxyView.getActivity().getString(R.string.msg_text_station_list)) != -1)
				{
					Toast.makeText(proxyView.getActivity(), R.string.msg_text_station_list,Toast.LENGTH_SHORT).show();
				}
				else if(((TextView)proxyView.getActivity().findViewById(R.id.text_range_station)).getText().toString()
						.indexOf(proxyView.getActivity().getString(R.string.msg_text_range_station)) != -1)
				{
					Toast.makeText(proxyView.getActivity(), R.string.msg_text_range_station,Toast.LENGTH_SHORT).show();
				}
				else if(Arrays.asList(proxyView.getActivity().getResources().getStringArray(R.array.stations_b))
						.contains(((TextView)proxyView.getActivity().findViewById(R.id.text_station_list)).getText()) 
						&& (((ToggleButton)proxyView.getActivity().findViewById(R.id.metroAButton)).getText()).equals(proxyView.getActivity().getString(R.string.metroA_on)))
				{
					Toast.makeText(proxyView.getActivity(), R.string.no_station,Toast.LENGTH_SHORT).show();
				}
				else if(Arrays.asList(proxyView.getActivity().getResources().getStringArray(R.array.stations_a))
						.contains(((TextView)proxyView.getActivity().findViewById(R.id.text_station_list)).getText()) 
						&& (((ToggleButton)proxyView.getActivity().findViewById(R.id.metroBButton)).getText()).equals(proxyView.getActivity().getString(R.string.metroB_on)))
				{
					Toast.makeText(proxyView.getActivity(), R.string.no_station,Toast.LENGTH_SHORT).show();
				}
				else
				{
					Class class1 = MapHelpMeTroActivity.class;
					Intent intent = new Intent(proxyView.getContext(), class1);
					TextView textViewDistance = (TextView)proxyView.getActivity().findViewById(R.id.text_range_station);
					TextView textViewStation =  (TextView)proxyView.getActivity().findViewById(R.id.text_station_list);
					
					CharSequence distance = textViewDistance.getText();
					CharSequence station  = textViewStation.getText();
					
					int range = widthInPixels(distance.toString());
					
					intent.putExtra(UTIL_GEO.RANGE, range);
					intent.putExtra(UTIL_GEO.STATION, station);
					
					proxyView.getContext().startActivity(intent);
				}
				 
			}
			
			/**
			 * 
			 * @param valueWidth
			 * @return
			 */
			
			private int widthInPixels(String valueWidth)
			{
				try 
				{
					 if (valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_KM)) != -1)
					{
						return Integer.valueOf(1000);
					}
					else if (valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_KMS)) != -1)
					{
						String value = valueWidth.substring(0, valueWidth.indexOf(" ")).trim();
						return ((Integer.valueOf(value)) * 1000);
					}
					else if (valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_METRS)) != -1)
					{
						String value = valueWidth.substring(0, valueWidth.indexOf(" ")).trim();
						return Integer.valueOf(value);
					}
					
				return 0;	
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					return 100;
				}
			}
	   };


	@Override
	public void returnServiceResponse(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
