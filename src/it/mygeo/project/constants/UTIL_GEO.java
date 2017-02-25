package it.mygeo.project.constants;

import it.mygeo.project.R;

import java.util.UUID;

public interface UTIL_GEO {
	
	public static final String TAG = "HelpMeTro";
	
	final static int MENU_EXIT = 0;
	final static int DIALOG_CATEGORY_TEXT_STYLE_VALUE 	= 1;
	final static int STYLE_PREFERENCE_ACTIVITY_VALUE 	= 2;
	
	final static int SELECT_MENU_EXIT					= 3;
	final static int SELECT_HELPMETRO_DESC 				= 4;

	final static String HELPMETRO_DIR 				= "HelMeTro";
	final static String HELPMETRO_FILE 				= "HelMeTro_File.xml";
	final static String HELPMETRO 					= "HelMeTro";
	final static String IS_CONN_SERV 			= "true";
	final static String DEF_SEEK 				= UUID.randomUUID().toString();
	final static String RANGE 					= UUID.randomUUID().toString();
	final static String STATION 				= UUID.randomUUID().toString();
	
	final static String PRE_LABEL_DIST 			= "in ";
	final static String SPACE 					= " ";
	final static int POST_LABEL_DIST_KM 		= R.string.Km;
	final static int POST_LABEL_DIST_KMS 		= R.string.Kms;
	final static int POST_LABEL_DIST_METRS 		= R.string.mts;
	final static String VALUE_SEEK 					= "VALUE_SEEK";
	final static String VALUE_METRO					= "VALUE_METRO";
	final static String METRO_A 					= "A";
	final static String METRO_B 					= "B";
	
	final static String S_METRO_A 					= "Metro A";
	final static String S_METRO_B 					= "Metro B";
	
	
	final static int NB_ConnetionEvent 			= 1000;
	final static int NB_ListElementEvent 		= 1001;
	final static int NB_SeekBarDistanceEvent 	= 1002;
	final static int NB_VoiceStyle 				= 1009;
	final static int NB_VoiceSeek 				= 1010;
	final static int NB_Stations 				= 1011;
	final static int NB_GoogleMap 				= 1012;
	final static int NB_ActivityGoogleMap 		= 1013;
	
	final static String EXTRA_PROMPT			= "EXTRA_PROMPT";
	final static String NB_TO_NOTIFY 			= "NB_TO_NOTIFY";
	final static String NB_VoiceStyle_NOTIFY 	= "NB_VoiceStyle_NOTIFY";
	final static String NB_VoiceSeek_NOTIFY 	= "NB_VoiceSeek_NOTIFY";
	
	final static String MYGEO_ERROR = "ERROR";
	

	final static int LIST = 2001;
	final static int ARRAY = 2002;
	final static int STRING = 2003;
	final static int INT = 2004;

}
