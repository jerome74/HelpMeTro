package it.mygeo.project.constants;

import it.mygeo.project.R;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;

import java.util.HashMap;

import android.app.Activity;

public class HashMapStaticCoodinatesStations 
{
static ContainerG30Bean containerG30Bean = new ContainerG30Bean();

//containerG30Bean.g30Beans.add(new G30Bean(0, 0 ,41.906268,12.414909,   "Battistini", UTIL_GEO.METRO_B));



public static ContainerG30Bean getMapContainerG30Bean(Activity activity)
{
	String[] stations_a = activity.getResources().getStringArray(R.array.stations_a);
	
	for (String  station_a : stations_a) 
	{
		String[] info_station = station_a.split(",");
		
		String lat = info_station[2];
		String lon = info_station[3];
		
		containerG30Bean.g30Beans.add(new G30Bean(0, 0,Double.parseDouble(lat),Double.parseDouble(lon), info_station[1], UTIL_GEO.METRO_A));
	}
	
	
	String[] stations_b = activity.getResources().getStringArray(R.array.stations_b);
	
	for (String  station_b : stations_b) 
	{
		String[] info_station = station_b.split(",");
		
		String lat = info_station[2];
		String lon = info_station[3];
		
		containerG30Bean.g30Beans.add(new G30Bean(0, 0,Double.parseDouble(lat),Double.parseDouble(lon), info_station[1], UTIL_GEO.METRO_B));
	}
	
	return containerG30Bean;
}}
