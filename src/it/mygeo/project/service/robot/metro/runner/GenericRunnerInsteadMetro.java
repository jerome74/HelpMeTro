package it.mygeo.project.service.robot.metro.runner;

import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.mygeo.project.service.robot.metro.bean.TubeBean;
import it.wlp.android.system.bean.ContainerG30Bean;

import java.util.ArrayList;
import java.util.List;

import android.os.Message;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

public class GenericRunnerInsteadMetro implements Runnable
{
	private GoogleMap googleMap;
	private ContainerG30Bean g30Beans; 
	private PreferenceCallBack preferenceCallBack; 
	private List<TubeBean> tubeBeans; 
	
	
	
	
	




	public GenericRunnerInsteadMetro(GoogleMap googleMap
								, ContainerG30Bean g30Beans
								, PreferenceCallBack preferenceCallBack) 
	{
		super();
		
		this.googleMap 			= googleMap;
		this.g30Beans 			= g30Beans;
		this.preferenceCallBack = preferenceCallBack;
		
		this.tubeBeans = new ArrayList<TubeBean>();
	}

	@Override
	public void run() 
	{		
		try 
		{
			preferenceCallBack.returnServiceResponse(new Message());
		} 
		catch (Exception e) 
		{
			Log.e(UTIL_GEO.HELPMETRO, "GenericRunnerMetro ERROR :" ,  e);
		}
		
	}

}
