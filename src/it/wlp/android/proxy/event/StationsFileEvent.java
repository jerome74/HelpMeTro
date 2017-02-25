package it.wlp.android.proxy.event;


import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.system.fwork.EnginePersistFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.os.Message;
import android.widget.LinearLayout;

public class StationsFileEvent  implements Observer {
	EnginePersistFile enginePersistFile;
	LinearLayout linearLayout;
	ProxyView proxyView;
	ContainerG30Bean containerG30Bean;
	Message message;
	
	

	public StationsFileEvent() 
	{
		super();
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;

		try 
		{	
			containerG30Bean = EnginePersistFile.execEnginePersistFile(proxyView.getActivity()).read();
			
			List<G30Bean> beansA = new ArrayList<G30Bean>(); 
			List<G30Bean> beansB = new ArrayList<G30Bean>(); 
			
			for (G30Bean g30Beans : containerG30Bean.g30Beans) 
				if(g30Beans.getType().equals(UTIL_GEO.METRO_A))
					beansA.add(g30Beans);
				else if(g30Beans.getType().equals(UTIL_GEO.METRO_B))
					beansB.add(g30Beans);
			
			NotifyBean.addElementsC_G30Bean(UTIL_GEO.NB_Stations, containerG30Bean);
			NotifyBean.addMetroA(UTIL_GEO.METRO_A, beansA);
			NotifyBean.addMetroB(UTIL_GEO.METRO_B, beansB);
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
