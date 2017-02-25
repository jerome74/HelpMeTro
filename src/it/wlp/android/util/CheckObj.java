package it.wlp.android.util;

import it.mygeo.project.constants.UTIL_GEO;

import java.util.List;

public class CheckObj {
	public static boolean check(Object object, int TYPE) 
	{
		boolean result = false;
		
		switch (TYPE) {
		case UTIL_GEO.LIST:
			result =  (((List)object) != null && ((List)object).size() > 0);
			break;

		case UTIL_GEO.ARRAY:
			result =  (((Object[])object) != null && ((Object[])object).length > 0);
			break;

		case UTIL_GEO.STRING:
			result =  (((String)object) != null && ((String)object).length() > 0);
			break;

		case UTIL_GEO.INT:
			result =  (((Integer)object) != null && ((Integer)object) > 0);
			break;

		default:
			break;
		}
		return result;
	}
}
