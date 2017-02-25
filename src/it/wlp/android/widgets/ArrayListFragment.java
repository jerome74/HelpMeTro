package it.wlp.android.widgets;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayListFragment extends ListFragment 
{

	Drawable drawable;
	View view;
	String selectedValue;
	String startSelectedValue;
	
	public ArrayListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Resources r = getResources();
        
        String lineTube = super.getActivity().getIntent().getStringExtra(UTIL_GEO.VALUE_METRO);
        
        if (lineTube.equals(UTIL_GEO.METRO_A)) 
        {
        	 String[] stations_a = r.getStringArray(R.array.stations_a);
        	 String[] bases = new String[stations_a.length];
        	 
        	 for (int i = 0; i < stations_a.length; i++)
        		 bases[i] = stations_a[i].split(",")[1];
        	
             setListAdapter(new ArrayAdapter<String>(getActivity(),
                     android.R.layout.simple_list_item_1, bases));
		} 
        else if (lineTube.equals(UTIL_GEO.METRO_B)) 
        {
        	String[] stations_b = r.getStringArray(R.array.stations_b);
       	 	String[] bases = new String[stations_b.length];
       	 
       	 	for (int i = 0; i < stations_b.length; i++)
       	 		bases[i] = stations_b[i].split(",")[1];
        	
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, bases));
		}
    }

    @SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	selectedValue = (String) getListAdapter().getItem(position);
    	 super.getActivity().finish();
    }

    /**
     * 
     * @return
     */
	public String getSelectedValue() {
		return selectedValue;
	}
}
