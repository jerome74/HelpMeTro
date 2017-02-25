package it.wlp.android.system.fwork;

import it.mygeo.project.R;
import it.mygeo.project.constants.HashMapStaticCoodinatesStations;
import it.mygeo.project.constants.UTIL_GEO;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.app.Activity;
import android.os.Environment;

public class EnginePersistFile {

	private static IToastHelper iToastHelper;
	private static ToastHelperDomain toastHelperDomain;
	private static Serializer serial;
	private static File helmetro_File;
	private static EnginePersistFile engine;
	private Activity activity;

	public static EnginePersistFile execEnginePersistFile(Activity activity)
	{
		if( engine != null )
			return engine;
		else
			return new EnginePersistFile(activity);
	}
	
	private  EnginePersistFile(Activity activity) {
		iToastHelper = new ToastHelper(activity);
		toastHelperDomain = new ToastHelperDomain(iToastHelper);
		serial = new Persister();
		this.activity = activity;
	}

	
/**
 * 
 * @param containerG30Bean
 * @return
 * @throws Exception
 */
	
		public void persist(ContainerG30Bean containerG30Bean) 
		{
			try 
			{ 	
				serial.write(containerG30Bean, new FileWriter(helmetro_File,false));
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
	}

	/**
	 * @name read
	 * @return  ContainerG30Bean
	 * @throws Exception
	 */
	
	@SuppressWarnings("resource")
	public ContainerG30Bean read() throws Exception {

		ContainerG30Bean containerG30Bean = null;
		
		if (!isExternalStorageWritable())
				toastHelperDomain.createToastMessage(R.string.m_no_starage, R.drawable.stop);
		
		File helmetroDir = new File( Environment.getExternalStorageDirectory()
													,UTIL_GEO.HELPMETRO_DIR);
		
		if (!helmetroDir.exists()) 
			helmetroDir.mkdir();
		
		helmetro_File = new File(helmetroDir.getAbsolutePath().concat(File.separator).concat(UTIL_GEO.HELPMETRO_FILE));
		
		if (!helmetro_File.exists()) 
		{
			helmetro_File.createNewFile(); 
			persist(HashMapStaticCoodinatesStations.getMapContainerG30Bean(this.activity));
		}
			
		if(new FileReader(helmetro_File).read() == -1)
		{
			containerG30Bean = HashMapStaticCoodinatesStations.getMapContainerG30Bean(this.activity);
		}
		else
		{
				containerG30Bean = serial.read(ContainerG30Bean.class, new FileReader(helmetro_File));
		}
		
		
		return containerG30Bean;
	}

	/**
	 * 
	 * @return
	 */
	
	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| !Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
}
