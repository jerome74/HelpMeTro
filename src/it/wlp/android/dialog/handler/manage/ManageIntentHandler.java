package it.wlp.android.dialog.handler.manage;

import static it.mygeo.project.constants.UTIL_GEO.STYLE_PREFERENCE_ACTIVITY_VALUE;
import it.wlp.android.dialog.element.IntentElement;
import it.wlp.android.dialog.element.domain.IntentActivity;
import it.wlp.android.dialog.handler.IntentHandler;

public class ManageIntentHandler<T,E> extends IntentHandler 
{
	@Override
	public  IntentElement<T, E> newElement(int id) 
	{
		switch (id) 
		{
		case STYLE_PREFERENCE_ACTIVITY_VALUE:
			return new IntentActivity();

		default:
			return null;

		}
	}
}