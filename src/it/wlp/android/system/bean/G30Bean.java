package it.wlp.android.system.bean;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element(name="G30Bean")
public class G30Bean
{
	@Attribute(required=true)
	private int id;
	@Element(required=false)
	private int marker;
	@Element(required=false)
	private String type;
	@Element(required=false)
	private double longitude;
	@Element(required=false)
	private double latitude;
	@Element(required=false)
	private String title;
	
	public G30Bean() {
		// TODO Auto-generated constructor stub
	}
	
	public G30Bean(int id
								,int marker
								,double latitude
								,double longitude
								,String title
								,String type) {
		super();
		this.id = id;
		this.marker = marker;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.type = type;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMarker() {
		return marker;
	}

	public void setMarker(int marker) {
		this.marker = marker;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
