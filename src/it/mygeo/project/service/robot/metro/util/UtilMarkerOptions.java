package it.mygeo.project.service.robot.metro.util;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.robot.metro.bean.Geo2Bean;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UtilMarkerOptions {

	/**
	 * @param args
	 */
	public static Marker addMarker(Context context, GoogleMap map, LatLng latLng, int resourceId, String minutes) 
	{
		MarkerOptions markerOptions = new MarkerOptions();

		markerOptions.draggable(false);		
		markerOptions.position(latLng);
		markerOptions.icon(BitmapDescriptorFactory.fromResource(resourceId));
		
		String snippet = null;
		
		if(minutes.startsWith("00")) 	snippet = minutes + " " + context.getString(R.string.seconds);
		else 							snippet = minutes + " " + context.getString(R.string.minute);
		
		markerOptions.title(context.getString(R.string.metro_in));
		markerOptions.snippet(snippet);

		return map.addMarker(markerOptions);

	}
	
	//2
	public static Geo2Bean getPreviousMarker(String Title, List<G30Bean> g30Beans)
	{
		Geo2Bean geo2Bean = new Geo2Bean();

		for (int i = 0; i < g30Beans.size(); i++) {
			if (g30Beans.get(i).getTitle().equalsIgnoreCase(Title)) {
				// DESTINATION
				geo2Bean.setDestination(g30Beans.get(i));

				// DEPARTURE
				if (i == 0)
					geo2Bean.setDeparture(geo2Bean.getDestination());
				else
					geo2Bean.setDeparture(g30Beans.get((i - 1)));

				return geo2Bean;
			}
		}

		return geo2Bean;
	}
	
	public static G30Bean getG30Beans(String title)
	{
		ContainerG30Bean containerG30Bean = NotifyBean.getElementsC_G30Bean(UTIL_GEO.NB_Stations);

		for (G30Bean g30Bean : containerG30Bean.g30Beans)
			if (g30Bean.getTitle().equals(title))
				return g30Bean;
			
		return null;
	}

}
