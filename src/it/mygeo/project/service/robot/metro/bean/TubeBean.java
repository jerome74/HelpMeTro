package it.mygeo.project.service.robot.metro.bean;

import com.google.android.gms.maps.model.Marker;

public class TubeBean 
{	
	private Marker marker;
	private boolean activated;
	
	public TubeBean(Marker marker, boolean activated) {
		super();
		this.marker = marker;
		this.activated = activated;
	}

	
	public Marker getMarker() {
		return marker;
	}

	public boolean isActivated() {
		return activated;
	}
	
	public void off() {
		activated = false;
	}
}
