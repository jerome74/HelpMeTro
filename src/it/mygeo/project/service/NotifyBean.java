package it.mygeo.project.service;

import it.mygeo.project.handler.ServiceHandler;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Message;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;


public class NotifyBean
{
	static Map<Integer,ServiceHandler>  serviceHandlers = Collections.synchronizedMap(new HashMap<Integer,ServiceHandler>());
	static Map<Integer,PreferenceCallBack>  preferenceCallBacks =  Collections.synchronizedMap(new HashMap<Integer,PreferenceCallBack>());
	static Map<Integer,ContainerG30Bean>  managedElementsC_G30Bean =  Collections.synchronizedMap(new HashMap<Integer,ContainerG30Bean>());
	static Map<String,List<G30Bean>>  G30BeanA =  Collections.synchronizedMap(new HashMap<String,List<G30Bean>>());
	static Map<String,List<G30Bean>>  G30BeanB =  Collections.synchronizedMap(new HashMap<String,List<G30Bean>>());
	static Map<Integer,GoogleMap>  managedMap =  Collections.synchronizedMap(new HashMap<Integer,GoogleMap>());
	
	/**
	 * 
	 * @param force
	 * @param preferenceCallBack
	 * @return
	 */
	
	public static void createHandle(int idNotify, ServiceHandler serviceHandler)
	{	
		
		
		if(serviceHandlers.size() > 0)
		{
			if(serviceHandlers.get(idNotify) == null )
				serviceHandlers.put(idNotify ,serviceHandler);
		}
		else
		{
			serviceHandlers.put(idNotify ,serviceHandler);
		}
	}
	
	/**
	 * 
	 * @param idNotify
	 * @param preferenceCallBack
	 */
	
	public static void createEvent(int idNotify, PreferenceCallBack preferenceCallBack)
	{
		preferenceCallBacks.put(idNotify ,preferenceCallBack);
	}
	
	/**
	 * 
	 */
	
	public static void removeEvent(int idNotify)
	{	
		preferenceCallBacks.remove(idNotify);
	}
	
	
	/**
	 * 
	 * @param idNotify
	 * @param containerG30Bean
	 */
	
	public static void addElementsC_G30Bean(int idElements, ContainerG30Bean containerG30Bean)
	{
		managedElementsC_G30Bean.put(idElements ,containerG30Bean);
	}
	
	/**
	 * 
	 * @param idElements
	 * @return
	 */
	
	public static ContainerG30Bean getElementsC_G30Bean(int idElements)
	{	
		return managedElementsC_G30Bean.get(idElements);
	}
	
	/**
	 * 
	 * @param idElements
	 */
	
	public static void removeElementsC_G30Bean(int idElements)
	{	
		managedElementsC_G30Bean.remove(idElements);
	}
	
	
	/**
	 * 
	 * @param idNotify
	 * @param GoogleMap
	 */
	
	public static void addMap(int idElements, GoogleMap map)
	{
		managedMap.put(idElements ,map);
	}
	
	/**
	 * 
	 * @param idElements
	 * @return
	 */
	
	public static GoogleMap getMap(int idElements)
	{
		return managedMap.get(idElements);
	}
	
	/**
	 * 
	 * @param idNotify
	 * @param GoogleMap
	 */
	
	public static void addMetroA(String idElements, List<G30Bean> bean)
	{
		G30BeanA.put(idElements ,bean);
	}
	
	/**
	 * 
	 * @param idElements
	 * @return
	 */
	
	public static List<G30Bean> getMetroA(String idElements)
	{
		return G30BeanA.get(idElements);
	}
	
	/**
	 * 
	 * @param idElements
	 * @param bean
	 */
	
	public static void addMetroB(String idElements, List<G30Bean> bean)
	{
		G30BeanB.put(idElements ,bean);
	}
	
	/**
	 * 
	 * @param idElements
	 * @return
	 */
	
	public static List<G30Bean> getMetroB(String idElements)
	{
		return G30BeanB.get(idElements);
	}
	
	public static void clean()
	{
		serviceHandlers.clear();
		preferenceCallBacks.clear();
		managedElementsC_G30Bean.clear();
		managedMap.clear();
	}
	
	/**
	 * 
	 * @param message
	 */
	
	public static void  notifyHandler(int idHandler, Message message)
	{
		serviceHandlers.get(idHandler).sendMessage(message);
	}
	
	/**
	 * 
	 * @param idEvent
	 * @param message
	 */
	
	public static void  notifyEvent(int idEvent)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		preferenceCallBacks.get(idEvent).returnServiceResponse();
	}
	
	public static PreferenceCallBack getEvent(int idEvent)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		return preferenceCallBacks.get(idEvent);
	}
	
	public static void  notifyEvent(int idEvent, Message message)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		preferenceCallBacks.get(idEvent).returnServiceResponse(message);
	}
	
}
