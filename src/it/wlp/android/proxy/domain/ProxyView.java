package it.wlp.android.proxy.domain;

import it.mygeo.project.R;
import it.wlp.android.proxy.event.ButtonSearchEvent;
import it.wlp.android.proxy.event.ConnetionEvent;
import it.wlp.android.proxy.event.StationsFileEvent;
import it.wlp.android.proxy.event.TextListStationEvent;
import it.wlp.android.proxy.event.TextRangeListEvent;
import it.wlp.android.proxy.event.ToggleButtonMetroA;
import it.wlp.android.proxy.event.ToggleButtonMetroB;
import it.wlp.android.proxy.event.VoiceListStationEvent;
import it.wlp.android.proxy.event.VoiceRangeStationEvent;
import it.wlp.android.proxy.external.IProxyView;

import java.util.Observable;

import android.app.Activity;
import android.content.Context;

public class ProxyView extends Observable implements IProxyView 
{
	Activity activity;
	Context context;
	
	
	
	public ProxyView(Activity activity, Context context) {
		super();
		this.activity = activity;
		this.context = context;
	}


	public void init() 
	{
		addObserver(new TextListStationEvent(activity.findViewById(R.id.text_station_list)));
		//addObserver(new TextRangeStationEvent(activity.findViewById(R.id.text_range_station)));
		addObserver(new TextRangeListEvent(activity.findViewById(R.id.text_range_station)));
		addObserver(new ToggleButtonMetroA(activity.findViewById(R.id.metroAButton)));
		addObserver(new ToggleButtonMetroB(activity.findViewById(R.id.metroBButton)));
		addObserver(new ButtonSearchEvent(activity.findViewById(R.id.searchPropertiesButton)));
		addObserver(new VoiceListStationEvent(activity.findViewById(R.id.voice_station_list),activity.findViewById(R.id.text_station_list)));
		addObserver(new VoiceRangeStationEvent(activity.findViewById(R.id.voice_range_station),activity.findViewById(R.id.text_range_station)));
		addObserver(new ConnetionEvent());
		addObserver(new StationsFileEvent());
		
		setChanged();
		notifyObservers();
	}


	public Activity getActivity() {
		return activity;
	}


	public Context getContext() {
		return context;
	}
}
