package it.wlp.android.dialog.handler;

import it.wlp.android.dialog.element.IntentElement;

import java.util.HashMap;

import android.content.Context;

public abstract class IntentHandler<T,E>
{

	public IntentElement<T, E> createDialogElement(Context context, int id, Class<T> clIntent, HashMap<String,E> extra) 
	{	
			IntentElement<T, E> element = newElement( id );
		   
			element.setClass(clIntent);
			element.setContext(context);
			element.setExtra(extra);
		   
			return element;
		}
		public abstract IntentElement<T, E> newElement(int id);
}
