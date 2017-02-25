package it.mygeo.project.service.robot.metro.bean;

import it.wlp.android.system.bean.G30Bean;

import com.google.android.gms.maps.model.LatLng;

public class G30BeanPlus extends G30Bean
{
	private LatLng latLng_1;
	private LatLng latLng_2;
	private LatLng latLng_3;
	private LatLng last;
	
	public LatLng getLatLng_1() {
		return latLng_1;
	}
	public void setLatLng_1(LatLng latLng_1) {
		this.latLng_1 = latLng_1;
	}
	public LatLng getLatLng_2() {
		return latLng_2;
	}
	public void setLatLng_2(LatLng latLng_2) {
		this.latLng_2 = latLng_2;
	}
	public LatLng getLatLng_3() {
		return latLng_3;
	}
	public void setLatLng_3(LatLng latLng_3) {
		this.latLng_3 = latLng_3;
	}
	public LatLng getLast() {
		return last;
	}
	public void setLast(LatLng last) {
		this.last = last;
	}
	
	
}
