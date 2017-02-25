package it.wlp.android.proxy.event;



import it.mygeo.project.R;
import it.wlp.android.proxy.domain.ProxyViewPreference;

import java.util.Observable;
import java.util.Observer;

import android.content.res.Resources;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.Log;

public class MultiPreferenceEvent implements Observer
{
	private ListPreference multiListPreference;
	private ProxyViewPreference proxyView;
	private String[] value_beers;
	private String[] entry_beers; 
	

	public MultiPreferenceEvent(Preference preferences) 
	{
		super();
		this.multiListPreference = (ListPreference)preferences;
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyViewPreference)arg0;
		
		Resources r = proxyView.getContext().getResources();
		String[] types_description_beers = r.getStringArray(R.array.stations_a);
		
		manageListPreferenceBeer(types_description_beers );
		
		multiListPreference.setEntries(entry_beers);
		Log.d(proxyView.getActivity().getString(R.string.DEBUG), "class = " + multiListPreference.getClass().getName() + " put Entries " );
		
		multiListPreference.setEntryValues(value_beers);
		Log.d(proxyView.getActivity().getString(R.string.DEBUG), "class = " + multiListPreference.getClass().getName() + " put EntryValues " );
	}
	
	/**
	 * 
	 * @param types_beers
	 */
	
	private void manageListPreferenceBeer(String[] types_beers)
	{
		value_beers = new String[types_beers.length / 2];
		entry_beers = new String[types_beers.length / 2]; 
		
		int countTB = 0; 
		int countEB = 0;
		
		while(types_beers.length > countTB)
		{
			String type = types_beers[countTB];
			Log.d(proxyView.getActivity().getString(R.string.DEBUG), "type = " + type );
			
			String description = types_beers[++countTB];
			Log.d(proxyView.getActivity().getString(R.string.DEBUG), "description = " + description );
			
			entry_beers[countEB] = new String(type.concat(System.getProperty("line.separator")
					.concat(System.getProperty("line.separator")).concat(description)));
			Log.d(proxyView.getActivity().getString(R.string.DEBUG), "entry_beers[countEB] = " + entry_beers[countEB] );
			
			value_beers[countEB] = new String(String.valueOf(countEB)); 
			Log.d(proxyView.getActivity().getString(R.string.DEBUG), "value_beers[countEB] = " + value_beers[countEB] );
			
			countTB++;
			countEB++;
		}
	}
}

