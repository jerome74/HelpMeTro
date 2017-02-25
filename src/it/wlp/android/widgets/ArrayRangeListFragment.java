package it.wlp.android.widgets;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayRangeListFragment extends ListFragment 
{

	Drawable drawable;
	View view;
	String selectedValue;
	String startSelectedValue;
	String strTextViewCurrentValue;
	
	public ArrayRangeListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Resources r = getResources();
        
 
        	String[] distance_metro = r.getStringArray(R.array.distance_metro);
   
        	
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, distance_metro));
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
		strTextViewCurrentValue = (String) getListAdapter().getItem(position);
    	
    	if(strTextViewCurrentValue != null && strTextViewCurrentValue.indexOf(" ") != -1)
    		selectedValue = strTextViewCurrentValue.substring(0, strTextViewCurrentValue.indexOf(" ")).trim();
    		
    	 super.getActivity().finish();
    }

    /**
     * 
     * @return
     */
	public String getSelectedValue() {
		return selectedValue;
	}
	
	public String getStrTextViewCurrentValue() {
		return strTextViewCurrentValue;
	}
}
