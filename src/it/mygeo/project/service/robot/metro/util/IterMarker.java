package it.mygeo.project.service.robot.metro.util;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.robot.bean.DirectionsResponse;
import it.mygeo.project.service.robot.metro.bean.G30BeanPlus;
import it.mygeo.project.service.robot.metro.bean.Geo2Bean;
import it.mygeo.project.service.robot.metro.bean.NumPreMarker;
import it.wlp.android.system.bean.G30Bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class IterMarker {

	/**
	 * @param args
	 */
	//1
	public static NumPreMarker getNumPreMarkerMetro(DirectionsResponse directionsResponse)
	{
		int block30sec 				= 0;
		int numStop	   				= 0;
		int sigleStop  				= 0;
		String str_arrival_time 	= null;
		String str_departure_time 	= null;
		String str_now_time 		= null;
		NumPreMarker numPreMarker 	= new NumPreMarker();
		long arrival_time 			= 0;
		long departure_time 		= 0;
		long now_time 				= 0;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		
		
		
		try 
		{
			if(directionsResponse.getRoute().getLeg().getStep().getTransitDetails() != null)
			{
				str_departure_time 	= directionsResponse.getRoute().getLeg().getStep().getTransitDetails().getDepartureTime().getText();
			}
			else
			{
				numPreMarker.setPreMarkerMain(1);
				numPreMarker.setPreMarkerMiddle(0);
				
				return numPreMarker;
			}
			if(directionsResponse.getRoute().getLeg().getStep().getTransitDetails() != null)
			{
				str_arrival_time 	= directionsResponse.getRoute().getLeg().getStep().getTransitDetails().getArrivalTime().getText();
			}
			else
			{
				numPreMarker.setPreMarkerMain(1);
				numPreMarker.setPreMarkerMiddle(0);
				
				return numPreMarker;
			}
			
			str_now_time	= dateFormat.format(new Date(System.currentTimeMillis()));
			
				
			arrival_time = dateFormat.parse(getPM(str_arrival_time)).getTime() / 1000;
			departure_time = dateFormat.parse(getPM(str_departure_time)).getTime() / 1000;
			now_time = dateFormat.parse(str_now_time).getTime() / 1000;
			
			//numero blocchi
			block30sec = ((int)arrival_time - (int)now_time) / 30;
			
			sigleStop = block30sec % 4;
				
			switch (sigleStop) 
			{
				case 0:
					numPreMarker.setPreMarkerMain(((int)block30sec / 4));
					numPreMarker.setPreMarkerMiddle((int)0);
					break;

				default:
					numPreMarker.setPreMarkerMain(((int)block30sec / 4));
					numPreMarker.setPreMarkerMiddle(sigleStop);
					break;
			}
		} 
		catch (ParseException e) 
		{
			Log.e(UTIL_GEO.TAG, "getNumPreMarkerMetro ERROR: " , e);
		}
		
		return numPreMarker;
	}
	
	//3
	public static void getFinalMarkerLineMetro(NumPreMarker marker , Geo2Bean geo2Bean, List<G30Bean> g30Beans)
	{	
		int newIterG30Beans = 0;
		int destination		= 0;
		
		
		for (int i = 0; i < g30Beans.size(); i++)
		{
			if(g30Beans.get(i) != null)
			{
				if(g30Beans.get(i).getTitle().equals(geo2Bean.getDestination().getTitle()))
				{
					destination = i;
					
					if(marker.getPreMarkerMiddle() != 0) 	newIterG30Beans = (i - (marker.getPreMarkerMain() + 1));
					else 									newIterG30Beans = (i - marker.getPreMarkerMain());
					
					geo2Bean.setOrigin(g30Beans.get(newIterG30Beans));
					break;
				}
			}
		}
		
		while(newIterG30Beans <= destination)
		{
			geo2Bean.addToAllMiddleG30Bean(g30Beans.get(newIterG30Beans));
			newIterG30Beans++;
		}
				
	}
	
	
	
	public static List<G30BeanPlus> getListLatLng(Geo2Bean geo2Bean , NumPreMarker marker)
	{
		List<G30Bean> g30Beans = geo2Bean.getAllMiddleG30Bean();
		List<G30BeanPlus> beanPlus = new ArrayList<G30BeanPlus>();
		
		G30Bean spotG30Bean = null;
				
		for (int i = 0; i < g30Beans.size(); i++)
		{
			if((i + 1) < g30Beans.size())
			{
				spotG30Bean = g30Beans.get((i + 1));
			
				beanPlus.add(getBeanPlus(g30Beans.get(i), spotG30Bean));
			}
			else
			{
				beanPlus.get((i - 1)).setLast(new LatLng(g30Beans.get(i).getLatitude(), g30Beans.get(i).getLongitude()));
			}
		}
		
		
		return beanPlus;
	}
	
	
	
	
	private static G30BeanPlus getBeanPlus(G30Bean start, G30Bean end) 
	{
		G30BeanPlus beanPlus = new G30BeanPlus();
		
		beanPlus.setLatitude(start.getLatitude());
		beanPlus.setLongitude(start.getLongitude());
		
		beanPlus.setLatLng_2(midPoint(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude()));
		beanPlus.setLatLng_1(midPoint(start.getLatitude(), start.getLongitude(), beanPlus.getLatLng_2().latitude,  beanPlus.getLatLng_2().longitude));
		beanPlus.setLatLng_3(midPoint(beanPlus.getLatLng_2().latitude,  beanPlus.getLatLng_2().longitude,end.getLatitude(), end.getLongitude()));
		beanPlus.setTitle(start.getTitle());
		beanPlus.setType(start.getType());
		
		return beanPlus;
	}

	public static LatLng midPoint(double lat1,double lon1,double lat2,double lon2){

	    double dLon = Math.toRadians(lon2 - lon1);

	    //convert to radians
	    lat1 = Math.toRadians(lat1);
	    lat2 = Math.toRadians(lat2);
	    lon1 = Math.toRadians(lon1);

	    double Bx = Math.cos(lat2) * Math.cos(dLon);
	    double By = Math.cos(lat2) * Math.sin(dLon);
	    double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
	    double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

	    
	    LatLng latLng = new LatLng(Math.toDegrees(lat3),Math.toDegrees(lon3));

	    return latLng;
	}
	
	public static List<LatLng> getListLatLng(List<G30BeanPlus> g30BeanPlus, int singleMarker)
	{
		List<LatLng> latLngs = new ArrayList<LatLng>();
		boolean first = true;
		for (G30BeanPlus beanPlus : g30BeanPlus) 
		{
			if(first)
			{
				switch (singleMarker) {
				case 1:
					LatLng latLng = beanPlus.getLatLng_3();
					latLngs.add(latLng);
					first = false;
					break;
				case 2:
					LatLng latLng2 = beanPlus.getLatLng_2();
					latLngs.add(latLng2);
					LatLng latLng3 = beanPlus.getLatLng_3();
					latLngs.add(latLng3);
					first = false;
					break;
					
				case 3:
					LatLng latLng4 = beanPlus.getLatLng_1();
					latLngs.add(latLng4);
					LatLng latLng5 = beanPlus.getLatLng_2();
					latLngs.add(latLng5);
					LatLng latLng6 = beanPlus.getLatLng_3();
					latLngs.add(latLng6);
					first = false;
					break;

				default:
					LatLng latLng7 = new LatLng(beanPlus.getLatitude(),beanPlus.getLongitude() );
					latLngs.add(latLng7);
					LatLng latLng8 = beanPlus.getLatLng_1();
					latLngs.add(latLng8);
					LatLng latLng9 = beanPlus.getLatLng_2();
					latLngs.add(latLng9);
					LatLng latLng10 = beanPlus.getLatLng_3();
					latLngs.add(latLng10);
					first = true;
					break;
				}
			}
			else
			{
				LatLng latLng = new LatLng(beanPlus.getLatitude(),beanPlus.getLongitude() );
				latLngs.add(latLng);
				LatLng latLng1 = beanPlus.getLatLng_1();
				latLngs.add(latLng1);
				LatLng latLng2 = beanPlus.getLatLng_2();
				latLngs.add(latLng2);
				LatLng latLng3 = beanPlus.getLatLng_3();
				latLngs.add(latLng3);
				LatLng last = beanPlus.getLast();
				if(last != null)
					latLngs.add(last);
			}
		}
		
		return latLngs;
	}
	
	public static String getPM(String pm)
	{
		String ret = pm;
		
		if (pm.indexOf("pm") == -1 && pm.indexOf("am") == -1 ) 
		{
			if(Integer.parseInt(pm.split(":")[0]) < 12) pm = pm.concat("am");
			else 										pm = pm.concat("pm");
		}
		
		String[] pm3 = pm.substring(0, pm.length() - 2).split(":");

		if (String.valueOf((Integer.parseInt(pm3[0]))).equals("12")) 
		{
			ret = pm3[0] + ":" + pm3[1];
		} 
		else 
		{
			String Pm4 = null;
			
			if(pm.indexOf("pm") != -1)
			{
				Pm4 = String.valueOf((Integer.parseInt(pm3[0]) + 12))
					.equals("24") ? "00" : String.valueOf((Integer
					.parseInt(pm3[0]) + 12));
			}
			else
			{
				Pm4 = String.valueOf((Integer.parseInt(pm3[0])));
			}
			
			String Pm5 = Pm4 + ":" + pm3[1];

			ret = Pm5;
		}
	
	return ret;
}

}
