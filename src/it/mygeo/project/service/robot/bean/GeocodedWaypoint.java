//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.12 at 02:17:48 PM CEST 
//


package it.mygeo.project.service.robot.bean;

import org.simpleframework.xml.Element;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://bean.robot.service.project.mygeo.it}geocoder_status"/>
 *         &lt;element ref="{http://bean.robot.service.project.mygeo.it}type"/>
 *         &lt;element ref="{http://bean.robot.service.project.mygeo.it}place_id"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

public class GeocodedWaypoint {

    @Element(name = "geocoder_status", required = false)
    protected String geocoderStatus;
    @Element(required = false)
    protected String type;
    @Element(name = "place_id", required = false)
    protected String placeId;

    /**
     * Gets the value of the geocoderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    /**
     * Sets the value of the geocoderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeocoderStatus(String value) {
        this.geocoderStatus = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the placeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * Sets the value of the placeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceId(String value) {
        this.placeId = value;
    }

}