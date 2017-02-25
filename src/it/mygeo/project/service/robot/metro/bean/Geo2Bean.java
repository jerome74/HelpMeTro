package it.mygeo.project.service.robot.metro.bean;

import java.util.ArrayList;
import java.util.List;

import it.wlp.android.system.bean.G30Bean;

public class Geo2Bean 
{
	G30Bean destination;
	G30Bean departure;
	G30Bean origin;
	List<G30Bean> allMiddleG30Bean = new ArrayList<G30Bean>();
	
	
	public G30Bean getDestination() {
		return destination;
	}
	public void setDestination(G30Bean destination) {
		this.destination = destination;
	}
	public G30Bean getDeparture() {
		return departure;
	}
	public void setDeparture(G30Bean departure) {
		this.departure = departure;
	}
	public G30Bean getOrigin() {
		return origin;
	}
	public void setOrigin(G30Bean origin) {
		this.origin = origin;
	}
	public List<G30Bean> getAllMiddleG30Bean() {
		return allMiddleG30Bean;
	}
	public void addToAllMiddleG30Bean(G30Bean g30Bean) {
		this.allMiddleG30Bean.add(g30Bean);
	}
	
	
	
	
	

	
}
