package it.wlp.android.proxy.domain;

import it.mygeo.project.R;
import it.wlp.android.proxy.event.MultiPreferenceEvent;
import it.wlp.android.proxy.external.IProxyView;

import java.util.Observable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class ProxyViewPreference extends Observable implements IProxyView 
{
	PreferenceActivity activity;
	Context context;
	PreferenceManager preferenceManager; 	
	SharedPreferences sharedPreferences;
	
	
	
	public ProxyViewPreference(Activity activity, Context context) 
	{
		super();
		this.activity = ((PreferenceActivity)activity);
		this.context = context;
		
		preferenceManager = this.activity.getPreferenceManager();
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
	}


	public void init() 
	{
		addObserver(new MultiPreferenceEvent(preferenceManager.findPreference(activity.getString(R.id.multi_pref))));

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