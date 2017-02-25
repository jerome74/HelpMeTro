package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.widgets.ArrayListFragment;
import it.wlp.android.widgets.ArrayRangeListFragment;
import it.wlp.android.widgets.TitleBar;
import it.wlp.android.widgets.TitleBarSimple;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

public class ActivityRangeListArray  extends Activity {

	 TitleBar bar;
	
   @Override
   protected void onCreate(Bundle savedInstanceState) 
   {
       super.onCreate(savedInstanceState);
       
       setContentView(R.layout.favourite_properties_range_list);
       TitleBarSimple bar = (TitleBarSimple)findViewById(R.id.titleBarSimple);
       bar.setActivity(this);
       bar.setTitle(getIntent().getStringExtra(UTIL_GEO.RANGE));
       
   }

	@Override
	public void finish() 
	{
		try 
		{
			ArrayRangeListFragment arrayListFragment = (ArrayRangeListFragment)getFragmentManager().findFragmentById(R.id.id_range_fragment);
			
			if (arrayListFragment.getSelectedValue() != null) 
			{
				Message msg = Message.obtain();
				msg.obj = arrayListFragment.getStrTextViewCurrentValue() + ";" + arrayListFragment.getSelectedValue();
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