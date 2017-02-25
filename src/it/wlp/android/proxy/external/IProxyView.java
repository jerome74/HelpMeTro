package it.wlp.android.proxy.external;

import android.app.Activity;
import android.content.Context;


public interface IProxyView 
{
	void init();
	Activity getActivity();
	Context getContext();
}
