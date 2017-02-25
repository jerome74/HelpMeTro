package it.mygeo.project.activities;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.widgets.SeekBarFragment;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class RangeStationActivity extends FragmentActivity {

	SeekBarFragment seekBarFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
        showEditDialog();
	}

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        seekBarFragment = new SeekBarFragment();
        seekBarFragment.setActivity(this);
        
        String def_seek = getIntent().getStringExtra(UTIL_GEO.DEF_SEEK);
        seekBarFragment.setProgress(Integer.parseInt(def_seek));
        seekBarFragment.show(fm, "");
    }
    
    @Override
	public void finish() 
	{
		try 
		{
			if(seekBarFragment.getStrTextViewCurrentValue() != null && seekBarFragment.getSelectedValue() != null)
			{
				Message msg = Message.obtain();
				
				msg.obj = seekBarFragment.getStrTextViewCurrentValue() + ";" + seekBarFragment.getSelectedValue();
				NotifyBean.notifyEvent(UTIL_GEO.NB_SeekBarDistanceEvent, msg);
			}
		}
		catch (Exception e) 
		{
			Log.w(UTIL_GEO.HELPMETRO,  e);
		}
		
		super.finish();
	}
    
}