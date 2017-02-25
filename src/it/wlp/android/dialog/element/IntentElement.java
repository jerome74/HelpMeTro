package it.wlp.android.dialog.element;

import java.util.HashMap;

import android.content.Context;


public interface IntentElement<T,E> 
{
	void setContext(Context context);
	void setClass(Class<T> clIntent);
	void setExtra(HashMap<String, E> map);
	void start();
}
