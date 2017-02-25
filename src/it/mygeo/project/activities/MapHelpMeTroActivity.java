package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.handler.ServiceHandler;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.domain.ConnService;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.mygeo.project.service.robot.GenericServiceMetro;
import it.mygeo.project.service.robot.bean.DirectionsResponse;
import it.mygeo.project.service.robot.metro.bean.G30BeanPlus;
import it.mygeo.project.service.robot.metro.bean.Geo2Bean;
import it.mygeo.project.service.robot.metro.bean.Minute;
import it.mygeo.project.service.robot.metro.bean.NumPreMarker;
import it.mygeo.project.service.robot.metro.bean.TubeBean;
import it.mygeo.project.service.robot.metro.util.IterMarker;
import it.mygeo.project.service.robot.metro.util.UtilMarkerOptions;
import it.mygeo.project.service.robot.util.Transit;
import it.wlp.android.map.GetMarker;
import it.wlp.android.map.HelpMeTroMarkerDragListener;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import it.wlp.android.widgets.TitleBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapHelpMeTroActivity extends FragmentActivity implements
GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, PreferenceCallBack  , OnMapReadyCallback {
	

	private GoogleMap mMap;
	private GoogleApiClient mGoogleApiClient;
	private Location myLocation;
	private LatLng stationLocation;
	private ToastHelper iToastHelper;
	private ToastHelperDomain toastHelperDomain;
	private TextView mTitleView;
	private TitleBar titleBar;
	private double latitude;
	private double longitude;
	private List<LatLng> polyz;
	private Activity activity = null;
	private ServiceHandler serviceHandler;
	private Intent A_To_B_Service = null;
	private List<TubeBean> tubeBeans = new ArrayList<TubeBean>();
	
	private List<LatLng> latLngs = null;
	private List<LatLng> latLngsInstead = null;
	
	private Iterator<LatLng> iterlatLngs = null;
	private Iterator<LatLng> iterlatLngsInstead = null;
	
	private Minute minutes = new Minute(30);
	private Minute minutesInstead = new Minute(30);
	
	private List<TubeBean> tubeBeansInstead = new ArrayList<TubeBean>();
	
	
	
	Toast toast = null;
	

	// These settings are the same as the settings for the map. They will in
	// fact give you updates at
	// the maximal rates currently possible.
	private static final LocationRequest REQUEST = LocationRequest.create()
			.setInterval(5000) // 5 seconds
			.setFastestInterval(16) // 16ms = 60fps
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	/**
	 * protected void onCreate(Bundle savedInstanceState) 
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.g30);
		TitleBar bar = (TitleBar) findViewById(R.id.titleBar_g30);
		bar.getBackButton().setVisibility(View.GONE);
		bar.setActivity(this);
		bar.setTitle("STAZIONE : " + getIntent().getStringExtra(UTIL_GEO.STATION));
		activity = this;

		 mGoogleApiClient = new GoogleApiClient.Builder(this)
         .addApi(LocationServices.API)
         .addConnectionCallbacks(this)
         .addOnConnectionFailedListener(this)
         .build();
		
		setUpMapIfNeeded();
		setUpMap();

		iToastHelper = new ToastHelper(this);
		toastHelperDomain = new ToastHelperDomain(iToastHelper);
		mTitleView = bar.getmTitleView();
		titleBar = bar;
	}

	/**
	 * private void setUpMapIfNeeded()
	 */
	
	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMapAsync(this);
		}
	}

	/**
	 * This is where we can add markers or lines, add listeners or move the
	 * camera. In this case, we just add a marker near Africa.
	 * <p>
	 * This should only be called once and when we are sure that {@link #mMap}
	 * is not null.
	 */
	private void setUpMap() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub

	}

	/**
	 * public void onConnected(Bundle connectionHint)
	 */
	
	@Override
	public void onConnected(Bundle connectionHint) 
	{
		LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, REQUEST, this);

		if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) 
		{
			myLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

			if (myLocation == null) {
				IToastHelper iToastHelper = new ToastHelper(this);
				ToastHelperDomain toastHelperDomain = new ToastHelperDomain(
						iToastHelper);
				toastHelperDomain.createToastMessage(R.string.position_on,
						R.drawable.stop);
				startActivity(new Intent(
						android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
				return;
			}

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(myLocation.getLatitude(), myLocation
							.getLongitude())) // Sets the center of the map to
												// Mountain View
					.zoom(zoomLevel(getIntent().getIntExtra(UTIL_GEO.RANGE, 0))) // Sets
																					// the
																					// zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			createGeofence(myLocation.getLatitude(), myLocation.getLongitude(),
					false);

			NotifyBean.createEvent(UTIL_GEO.NB_ActivityGoogleMap, this);
			NotifyBean.addMap(UTIL_GEO.NB_GoogleMap, mMap);
			
			new TransitDirection().execute();

			startService();

		}
	}

	/**
	 * private void startAnagninaToBattistini() 
	 */
	
	private void startService() 
	{
		A_To_B_Service = new Intent(this,GenericServiceMetro.class);
		serviceHandler = new ServiceHandler(this, this);
		Message startMessage = new Message();
		startMessage.arg1 = SERVICES.START;
		serviceHandler.handleMessage(startMessage, A_To_B_Service);
	}

	/**
	 * createGeofence(double latitude, double longitude,boolean openKeyborad)
			
	 * @param latitude
	 * @param longitude
	 * @param openKeyborad
	 */
	
	public void createGeofence(double latitude, double longitude,
			boolean openKeyborad) {

		Marker stopMarker = mMap.addMarker(new MarkerOptions().draggable(true)
				.position(new LatLng(latitude, longitude))
				.title(this.getString(R.string.app_name))
				.snippet(this.getString(R.string.snipped_my_pos)));

		myLocation.setLatitude(latitude);
		myLocation.setLongitude(longitude);

		stopMarker.setIcon(GetMarker.getIconManMarker());
		titleBar.setMarker(GetMarker.getImageManMarker());

		manageOtherMarkers(latitude, longitude);

		mMap.setOnMarkerDragListener(new HelpMeTroMarkerDragListener(this, mMap));
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
		if(mGoogleApiClient!= null && !mGoogleApiClient.isConnected())
		{
					mGoogleApiClient.connect();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	/**
	 * 
	 * @param screenWidth
	 * @return
	 */
	public static byte zoomLevel(double distance) {
		byte zoom = 1;
		double E = 40075004;
		zoom = (byte) Math.round(Math.log(E / distance) / Math.log(2) + 1);
		// to avoid exeptions
		if (zoom > 21)
			zoom = 21;
		if (zoom < 1)
			zoom = 1;

		return zoom;
	}

	@Override
	protected void onDestroy() 
	{
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<RunningServiceInfo> serviceInfos = activityManager.getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo runningServiceInfo : serviceInfos) 
		{
			if(runningServiceInfo.service.getClassName().equals("it.mygeo.project.service.robot.GenericServiceMetro"))
			{
				 final Intent 				service 	= new Intent(this , GenericServiceMetro.class);
				 final ServiceHandler 	serviceHandler 	= new ServiceHandler(this,  this);
				 final	Message message 				= new Message();
				 
				 message.arg1 = SERVICES.STOP;
				 
				 serviceHandler.handleMessage(message, service);
				 
			}
		}
		super.onDestroy();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}



	/* Method to decode polyline points */
	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	private void manageOtherMarkers(double latitude, double longitude) 
	{
		ContainerG30Bean containerG30Bean = NotifyBean.getElementsC_G30Bean(UTIL_GEO.NB_Stations);

		for (G30Bean g30Bean : containerG30Bean.g30Beans)
		{
			if (g30Bean.getTitle().equals(getIntent().getStringExtra(UTIL_GEO.STATION)))
			{
				stationLocation = new LatLng(g30Bean.getLatitude(),g30Bean.getLongitude());
			}

			MarkerOptions markerOptions = new MarkerOptions();

			markerOptions.draggable(false);
			markerOptions.position(new LatLng(g30Bean.getLatitude(), g30Bean.getLongitude()));

			if (g30Bean.getType().equals(UTIL_GEO.METRO_A))	markerOptions.title(UTIL_GEO.S_METRO_A);
			else 											markerOptions.title(UTIL_GEO.S_METRO_B);

			markerOptions.snippet(g30Bean.getTitle());

			Marker marker = mMap.addMarker(markerOptions);

			marker.setIcon(GetMarker.getmarkerStation(g30Bean.getType(), this));
		}

		new GetDirection(String.valueOf(stationLocation.latitude),
				String.valueOf(stationLocation.longitude),
				String.valueOf(myLocation.getLatitude()),
				String.valueOf(myLocation.getLongitude())).execute();
	}

	@Override
	public void returnServiceResponse(Message msg)
	{
		handlerInstead.sendEmptyMessage(0);
	}
	
	/***************************************************************
	 * 
	 * @variable handlerInstead
	 * 
	 /***************************************************************/
	
	@SuppressLint("HandlerLeak")
	Handler handlerInstead = new Handler()
	{
		public void handleMessage(Message msg)
		{
				boolean toClose = true;
				boolean toExit = false;
	
     			try 
        		{	
     				if(iterlatLngsInstead != null)
     				{
	        			while (iterlatLngsInstead.hasNext() && !toExit) 
	        			{
	        				LatLng latLng = iterlatLngsInstead.next();
	        				
	
	        				if(tubeBeansInstead.size() == 0)
	        				{	
	        					minutesInstead.setMinute(latLngsInstead.size());
	        					
	        					TubeBean firstTubeBean  = new TubeBean(UtilMarkerOptions.addMarker(MapHelpMeTroActivity.this,mMap
	        														, latLng, R.drawable.subway1,minutesInstead.getMinute())
	        														, true); 
	        						
	        					runOnUiThread(new Runnable() {
	        						public void run() {
	        							Toast.makeText(activity, R.string.tracing,
	        									Toast.LENGTH_SHORT).show();
	        						}
	        					});
	        					
	        					tubeBeansInstead.add(firstTubeBean);
	        					toClose = false;
	        					toExit = true;
	        				}
	        				else
	        				{
	        					for (TubeBean tubeBean : tubeBeansInstead)
	        					{
	        						if(tubeBean.isActivated())
	        						{
	        							tubeBean.getMarker().remove();
	        							tubeBean.off();
	        									
	        							TubeBean firstTubeBean  = new TubeBean(UtilMarkerOptions.addMarker(MapHelpMeTroActivity.this,mMap
												, latLng, R.drawable.subway1,minutesInstead.getMinute())
												, true); 
	        									
	        							tubeBeansInstead.add(firstTubeBean);
	        							toClose = false;
	        							toExit = true;
	        						}
	        					}
	        				}
	        			}
	        			if(toClose)
	                	{
	                		Message startMessage = new Message();
	                		startMessage.arg1 = SERVICES.STOP;
	                		serviceHandler.handleMessage(startMessage,A_To_B_Service);
	                	}
     				}
     				else
     				{
     					runOnUiThread(new Runnable() {
    						public void run() {
    							Toast.makeText(activity, R.string.elab,
    									Toast.LENGTH_SHORT).show();
    						}
    					});
     				}
        		} 
        		catch (Exception e) 
        		{
        			Log.e(UTIL_GEO.HELPMETRO, "GenericRunnerMetro ERROR :" ,  e);
        		}	
          }
	};
	
	
	@Override
	public void returnServiceResponse() 
	{	
		handler.sendEmptyMessage(0);
	}
	
	/***************************************************************
	 * 
	 * @variable handler
	 * 
	 /***************************************************************/
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
				boolean toClose = true;
				boolean toExit = false;
	
     			try 
        		{	
     				if(iterlatLngs != null)
     				{
	
	        			while (iterlatLngs.hasNext() && !toExit) 
	        			{
	        				LatLng latLng = iterlatLngs.next();
	        				
	
	        				if(tubeBeans.size() == 0)
	        				{	
	        					minutes.setMinute(latLngs.size());
	        					
	        					TubeBean firstTubeBean  = new TubeBean(UtilMarkerOptions.addMarker(MapHelpMeTroActivity.this,mMap
	        														, latLng, R.drawable.subway1, minutes.getMinute())
	        														, true); 
	        						
	        					runOnUiThread(new Runnable() {
	        						public void run() {
	        							Toast.makeText(activity, R.string.tracing,
	        									Toast.LENGTH_SHORT).show();
	        						}
	        					});
	        					
	        					tubeBeans.add(firstTubeBean);
	        					toClose = false;
	        					toExit = true;
	        				}
	        				else
	        				{
	        					for (TubeBean tubeBean : tubeBeans)
	        					{
	        						if(tubeBean.isActivated())
	        						{
	        							tubeBean.getMarker().remove();
	        							tubeBean.off();
	        									
	        							TubeBean firstTubeBean  = new TubeBean(UtilMarkerOptions.addMarker(MapHelpMeTroActivity.this,mMap
												, latLng, R.drawable.subway1,minutes.getMinute())
												, true); 
	        									
	        							tubeBeans.add(firstTubeBean);
	        							toClose = false;
	        							toExit = true;
	        						}
	        					}
	        				}
	        			}
	        			if(toClose)
	                	{
	                		Message startMessage = new Message();
	                		startMessage.arg1 = SERVICES.STOP;
	                		serviceHandler.handleMessage(startMessage, A_To_B_Service);
	                	}
     				}
     				else
     				{
     					runOnUiThread(new Runnable() {
    						public void run() {
    							Toast.makeText(activity, R.string.elab,
    									Toast.LENGTH_SHORT).show();
    						}
    					});
     				}
        		} 
        		catch (Exception e) 
        		{
        			Log.e(UTIL_GEO.HELPMETRO, "GenericRunnerMetro ERROR :" ,  e);
        		}	
          }
	};
	
	/***************************************************************
	 * 
	 * @class TransitDirection
	 * 
	 /***************************************************************/
	
	class TransitDirection extends AsyncTask<String, String, String>
	{	 
        @Override
        protected String doInBackground(String... arg0) 
        {
        	G30Bean destination 					= null;
			List<G30Bean> lineMetro 				= null;
			
			destination = UtilMarkerOptions.getG30Beans(getIntent().getStringExtra(UTIL_GEO.STATION));
        	
        	if (destination.getType().equals(UTIL_GEO.METRO_A)) lineMetro = NotifyBean.getMetroA(UTIL_GEO.METRO_A);
				else 											lineMetro = NotifyBean.getMetroB(UTIL_GEO.METRO_B);
        	
        
        	 latLngs = doTransitSertvice(destination, lineMetro, minutes);
        	
        	 Collections.reverse(lineMetro);
        	 
        	 latLngsInstead = doTransitSertvice(destination, lineMetro, minutesInstead);
        	 
        	 iterlatLngsInstead = latLngsInstead.iterator();
        	 iterlatLngs 		= latLngs.iterator();
        	 
        	 tubeBeans 			= new ArrayList<TubeBean>(latLngs.size());
        	 tubeBeansInstead 	= new ArrayList<TubeBean>(latLngsInstead.size());
        	 
        	return null;
        }
        
        
        private List<LatLng> doTransitSertvice(G30Bean destination, List<G30Bean> lineMetro, Minute numMinuti)
        {
			
			Geo2Bean journey 						= null;
			DirectionsResponse directionsResponse 	= null;
			NumPreMarker numPreMarker				= null;
			List<G30BeanPlus> g30BeanPlus			= null;

 			try 
    		{	
 				journey = UtilMarkerOptions.getPreviousMarker(destination.getTitle(), lineMetro);
 				
 				directionsResponse = Transit.getTransit(String.valueOf(journey.getDeparture().getLatitude())
 									, String.valueOf(journey.getDeparture().getLongitude())
 									, String.valueOf(journey.getDestination().getLatitude())
 									, String.valueOf(journey.getDestination().getLongitude()));
 				
 				if(directionsResponse == null)
 				{
 					runOnUiThread(new Runnable() {
 						public void run() {
 							Toast.makeText(activity, R.string.no_service_transit,
 									Toast.LENGTH_SHORT).show();
 						}
 					});
 					
 					return null;
 				}
 				else
 				{
	 				numPreMarker = IterMarker.getNumPreMarkerMetro(directionsResponse);
	 				
	 				IterMarker.getFinalMarkerLineMetro(numPreMarker, journey, lineMetro);
	 				
	 				g30BeanPlus = IterMarker.getListLatLng(journey, numPreMarker);
	 				
	 				List<LatLng> latLngs = IterMarker.getListLatLng(g30BeanPlus, numPreMarker.getPreMarkerMiddle());
	 				
	 				runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(activity, R.string.end_elab,
									Toast.LENGTH_SHORT).show();
						}
					});
	 				
	 				return latLngs;
 				}
    		} 
    		catch (Exception e) 
    		{
    			Log.e(UTIL_GEO.HELPMETRO, "GenericRunnerMetro ERROR :" ,  e);
    			runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(activity, R.string.no_service_transit,
								Toast.LENGTH_SHORT).show();
					}
				});
    			
    			return null;
    		}	
      }
    }
			
	
	/***************************************************************
	 * 
	 * @class GetDirection
	 * 
	 /***************************************************************/

	class GetDirection extends AsyncTask<String, String, String> {

		private String startLat;
		private String startLng;
		private String endLat;
		private String endtLng;

		public GetDirection(String startLat, String startLng, String endLat,
				String endtLng) {
			super();
			this.startLat = startLat;
			this.startLng = startLng;
			this.endLat = endLat;
			this.endtLng = endtLng;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		protected String doInBackground(String... args) {
			try {
				String stringUrl = "http://maps.googleapis.com/maps/api/directions/json?origin="
						+ this.startLat
						+ ","
						+ this.startLng
						+ "&destination="
						+ this.endLat + "," + this.endtLng + "&sensor=false";

				// String stringUrl =
				// "http://maps.googleapis.com/maps/api/directions/xml?" +
				// "origin=" + "41.9062254709679,12.4148554927194"
				// + "&destination=" + "41.9006073004294,12.4262978241803"
				// + "&departure_time=" + System.currentTimeMillis() / 1000 +
				// "&mode=transit";

				HttpPost httppost = new HttpPost(stringUrl);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				InputStream is = null;
				is = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				sb.append(reader.readLine() + "\n");
				String line = "0";
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				reader.close();
				String result = sb.toString();
				Log.d(UTIL_GEO.HELPMETRO, "-->START LOG RESP JSON<--");
				Log.d(UTIL_GEO.HELPMETRO, result);
				Log.d(UTIL_GEO.HELPMETRO, "-->END LOG RESP JSON<--");
				
				// ************/
				// XML
				// /************/
				
//				ByteArrayInputStream arg1 = new ByteArrayInputStream(
//						result.getBytes());
//				Serializer serial = new Persister();
//				DirectionsResponse directionsResponse = serial.read(
//						DirectionsResponse.class, arg1);
//
//				String polyline = directionsResponse.getRoute().getLeg()
//						.getStep().getPolyline().getPoints();

				// ************/
				// JSON
				// /************/

				 JSONObject jsonObject = new JSONObject(result);
				
				 // routesArray contains ALL routes
				 JSONArray routesArray = jsonObject.getJSONArray("routes");
				 // Grab the first route
				 JSONObject route = routesArray.getJSONObject(0);
				
				 JSONObject poly = route.getJSONObject("overview_polyline");
				 String polyline = poly.getString("points");
				polyz = decodePoly(polyline);

			} catch (Exception e) {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(activity, R.string.no_service,
								Toast.LENGTH_SHORT).show();
						finish();
					}
				});

			}

			return null;

		}

		protected void onPostExecute(String file_url) {

			if (polyz != null)
				for (int i = 0; i < polyz.size() - 1; i++) {
					LatLng src = polyz.get(i);
					LatLng dest = polyz.get(i + 1);
					Polyline line = mMap.addPolyline(new PolylineOptions()
							.add(new LatLng(src.latitude, src.longitude),
									new LatLng(dest.latitude, dest.longitude))
							.width(20).color(Color.BLUE).geodesic(true));

				}
		}
	}

	
	
	

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	
	
	
	@Override
	public void finish() 
	{
		mMap.clear();
		mGoogleApiClient.disconnect();
		
		mMap 				= null;
		mGoogleApiClient 	= null;
		myLocation 			= null;
		stationLocation  	= null;
		iToastHelper 		= null;
		toastHelperDomain 	= null;
		mTitleView 			= null;
		titleBar 			= null;
		latitude 			= 0;
		longitude 			= 0;
		polyz 				= null;
		activity 			= null;
		serviceHandler 		= null;
		A_To_B_Service 		= null;
		tubeBeans 			= null;
		latLngs 			= null;
		latLngsInstead 		= null;
		iterlatLngs 		= null;
		iterlatLngsInstead 	= null;
		minutes  			= null;
		minutesInstead  	= null;
		tubeBeansInstead  	= null;
		
		// TODO Auto-generated method stub
		super.finish();
	}


	
	@Override
	public void onConnectionSuspended(int cause) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		// Enabling MyLocation Layer of Google Map
		googleMap.setMyLocationEnabled(true);
	}

}
