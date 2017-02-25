package it.mygeo.project.service.robot.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UtilTimeStart 
{
	static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:nn");
	
	static List<Long> startListMetroA_Anagnina = new ArrayList<Long>(); 
	static List<Long> startListMetroA_Battistini = new ArrayList<Long>(); 
	static List<Long> startListMetroB_Laurentina = new ArrayList<Long>(); 
	static List<Long> startListMetroB_Rebibbia = new ArrayList<Long>(); 
	
	
	static
	{
		
//		05
//		30 35 40 44 48 51 55 58
//		06
//		02 05 08 11 14 18 22 25 28 30 33 36 39 42 45 47 50 53 56 59
//		07
//		02 05 08 11 14 17 20 22 25 28 31 34 37 39 42 45 48 51 54 56 59
//		08
//		02 05 08 11 13 16 19 22 25 28 30 33 36 39 42 45 48 51 54 57 59
//		09
//		02 05 08 11 14 16 19 23 26 30 33 36 39 43 46 50 53 56
//		10
//		00 03 07 10 13 16 20 23 26 29 32 36 39 42 46 49 52 56 59
//		11
//		02 05 08 11 15 18 22 25 28 32 35 39 42 46 49 53 56
//		12
//		00 03 07 10 14 17 21 24 27 31 34 37 40 43 46 50 53 57
//		13
//		00 03 07 10 14 17 21 24 28 31 35 38 42 45 49 52 56 59
//		14
//		02 06 09 12 15 18 21 25 28 32 35 38 42 45 49 52 56 59
//		15
//		03 06 10 13 16 19 22 25 28 31 34 37 41 44 47 50 53 56 59
//		16
//		02 05 08 10 13 16 19 22 25 27 30 33 36 39 42 45 48 51 54 57
//		17
//		00 03 06 09 12 15 18 22 25 28 31 34 37 40 43 46 49 52 55 58
//		18
//		01 04 07 10 13 16 19 22 25 28 31 34 37 40 43 46 49 53 56
//		19
//		00 03 07 11 14 18 22 26 31 35 39 43 49 52 55
//		20
//		00 04 09 14 19 28 33 38 43 48 53 59
//		21
//		05 11 17 22 28 33 39 44 50 56
//		22
//		03 10 17 24 31 38 46 55
//		23
//		04 12 20 30

		
		startListMetroA_Anagnina.add(getLontTimeStart("05:30")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:35")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:40")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:44")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:48")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:51")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:55")); 
		startListMetroA_Anagnina.add(getLontTimeStart("05:58"));
	}
	
	
	
	private static Long getLontTimeStart(String time)
	{
		return null;
	}
	
}
