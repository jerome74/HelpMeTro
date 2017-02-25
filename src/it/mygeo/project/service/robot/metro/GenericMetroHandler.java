package it.mygeo.project.service.robot.metro;

import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.constants.UTIL_GEO;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GenericMetroHandler extends Handler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Context context;

	public GenericMetroHandler(Context context) {
		super();
		this.context = context;
	}

	public void handleMessage(Message msg, Intent intent) 
	{
		
	}
}