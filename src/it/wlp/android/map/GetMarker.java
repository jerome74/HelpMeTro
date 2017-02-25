package it.wlp.android.map;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import android.app.Activity;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class GetMarker 
{
	
	
	public static BitmapDescriptor getmarkerStation(String type, Activity activity) 
	{
		if(type.equals(UTIL_GEO.METRO_A))
				return BitmapDescriptorFactory.fromResource(R.drawable.metrolina);		
		else if(type.equals(UTIL_GEO.METRO_B))
			return BitmapDescriptorFactory.fromResource(R.drawable.metrolinb);
		else
			return BitmapDescriptorFactory.fromResource(R.drawable.markermetro1);
			
	}
	
	public static int getImageManMarker() 
	{
				return R.drawable.man;	
	}
	
	public static BitmapDescriptor getIconManMarker() 
	{
				return BitmapDescriptorFactory.fromResource(R.drawable.man);		
	}
	
}
