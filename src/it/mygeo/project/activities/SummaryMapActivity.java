package it.mygeo.project.activities;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SummaryMapActivity extends FragmentActivity implements LocationListener , OnMapReadyCallback {

	 private GoogleMap mMap;
	 private LocationManager locationManager;
	 private static final long MIN_TIME = 400;
	 private static final float MIN_DISTANCE = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.summary_mapview);

	    if (mMap == null) {
	        // Try to obtain the map from the SupportMapFragment.
	       ((SupportMapFragment) getSupportFragmentManager().findFragmentById(it.mygeo.project.R.id.map)).getMapAsync(this);
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
	        }
	    }
	    mMap.setMyLocationEnabled(true);

	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); 


	}

	@Override
	public void onLocationChanged(Location location) {
	    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
	    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
	    mMap.animateCamera(cameraUpdate);
	    locationManager.removeUpdates(this);

	}

	@Override
	public void onProviderDisabled(String provider) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		mMap = googleMap;
		// Enabling MyLocation Layer of Google Map
		googleMap.setMyLocationEnabled(true);
	}
}
