package it.wlp.android.proxy.event;

import it.wlp.android.proxy.domain.ProxyView;

import java.util.Observable;
import java.util.Observer;

import android.widget.TextView;

public class IntentEvent<T>
implements Observer
{
	TextView textView;
	ProxyView proxyView;
	Class<T> intentClass;
	
	public  IntentEvent(Class<T> activity) 
	{
		super();
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
	}
}
