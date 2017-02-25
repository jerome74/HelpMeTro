package it.mygeo.project.service.robot.util;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.robot.bean.DirectionsResponse;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.util.Log;

public class Transit {

	/**
	 * @param args
	 */
	public static DirectionsResponse getTransit(String startLat
					, String startLng
					, String endLat
					, String endtLng) 
	{    
		
		HttpPost httppost 						= null;
		HttpClient httpclient					= null;
		HttpResponse response 					= null;
		HttpEntity entity 						= null;
		InputStream is							= null;
		BufferedReader reader 					= null;
		StringBuilder sb 						= null;
		String line 							= "0";
		String result 							= null;
		ByteArrayInputStream arg1  				= null;
		Serializer serial 						= null;
		DirectionsResponse directionsResponse 	= null;
		
	   	try
	   	{
			String stringUrl = "http://maps.googleapis.com/maps/api/directions/xml?" +
					"origin=" + startLat + "," + startLng
					+ "&destination="  + endLat + "," + endtLng
					+ "&departure_time=" + System.currentTimeMillis() / 1000 + "&mode=transit";
			
			
			httppost 	= new HttpPost(stringUrl);
			httpclient 	= new DefaultHttpClient();
			response 	= httpclient.execute(httppost);
			entity 		= response.getEntity();
			is 			= entity.getContent();
			reader 		= new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			sb 			= new StringBuilder();
			
			sb.append(reader.readLine() + "\n");
			
			
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			is.close();
			reader.close();
			
			result = sb.toString();
			
			Log.d(UTIL_GEO.HELPMETRO, "-->START LOG RESP XML<--");
			Log.d(UTIL_GEO.HELPMETRO, result);
			Log.d(UTIL_GEO.HELPMETRO, "-->END LOG RESP XML<--");
			
			arg1  				= new ByteArrayInputStream(result.getBytes());
			serial 				= new Persister();
			directionsResponse 	= serial.read(DirectionsResponse.class, arg1);

		} 
	   	catch (Exception e) 
		{
		  Log.e(UTIL_GEO.TAG, "DirectionsResponse ERROR :", e);
		  return null;
		}
	   	
	    return directionsResponse;
	}

}
