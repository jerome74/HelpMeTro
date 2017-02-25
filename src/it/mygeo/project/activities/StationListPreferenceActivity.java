package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.wlp.android.preference.MultiSelectListPreference;
import it.wlp.android.proxy.domain.ProxyViewPreference;
import it.wlp.android.proxy.external.IProxyView;
import it.wlp.android.service.external.PreferenceCallBack;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class StationListPreferenceActivity extends PreferenceActivity implements
		OnPreferenceChangeListener, 
		PreferenceCallBack {

	IProxyView iProxyView;
	MultiSelectListPreference multiSelectListPreference;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		
		
		addPreferencesFromResource(R.xml.basepreference);
		
		multiSelectListPreference = new MultiSelectListPreference(this);
		multiSelectListPreference.setKey(getString(R.id.multi_pref));
		multiSelectListPreference.setTitle(R.id.multi_pref);
		multiSelectListPreference.setSummary(R.id.multi_pref);
		
		getPreferenceScreen().addPreference(multiSelectListPreference);
		
		
		
		iProxyView = new ProxyViewPreference(this, this);
		iProxyView.init();
		
	}
	
	@Override
	public boolean onPreferenceChange(Preference arg0, Object arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void returnServiceResponse() {
		// TODO Auto-generated method stub
	}

}
