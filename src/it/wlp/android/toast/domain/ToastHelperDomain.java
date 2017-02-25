package it.wlp.android.toast.domain;

import android.widget.Toast;
import it.wlp.android.toast.external.IToastHelper;

public class ToastHelperDomain {
	IToastHelper helper;

	public ToastHelperDomain(IToastHelper helper) 
	{
		super();
		this.helper = helper;
	}

	public void createToastMessage(int iText) 
	{
		this.helper.createToastMessage(iText);
	}

	public void createToastMessage(int iText, int iImage) 
	{
		this.helper.createToastMessage(iText, iImage);
	}
	
	public void createToastMessage(String tText, int iImage) 
	{
		this.helper.createToastMessage(tText, iImage);
	}
	
	public Toast getToastMessage(int iText)
	{
		return this.helper.getToastMessage(iText);
	}
	
	public void stopToastMessage(Toast toast)
	{
		this.helper.stopToastMessage(toast);
	}
}
