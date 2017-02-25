package it.wlp.android.proxy.util.domain;

import it.mygeo.project.R;
import it.wlp.android.proxy.util.external.IUtilArrayAdapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class UtilArrayAdapter<T> implements IUtilArrayAdapter<T>
{
	
	ArrayAdapter<T> arrayAdapter;
	
	public UtilArrayAdapter(final Context _context, int textViewResourceId, T[] objects) 
	{
		arrayAdapter = new ArrayAdapter( _context, textViewResourceId, objects)
		{

			final Context context = _context;
			
			public View getView(int position, View convertView, ViewGroup parent) 
			{
				View view = View.inflate(this.context, R.layout.alert_toast, null);
				return view;
			}
		};
		
	}

	public UtilArrayAdapter(final Context _context, int textViewResourceId,
			List<T> objects) 
	{
		arrayAdapter = new ArrayAdapter( _context, textViewResourceId, objects)
		{

			final Context context = _context;
			
			public View getView(int position, View convertView, ViewGroup parent) 
			{
				View view = View.inflate(this.context, R.layout.lt_stile, null);
				return view;
			}
		};
	}

	public ArrayAdapter<T> getArrayAdapter() {
		return arrayAdapter;
	}
		
}
