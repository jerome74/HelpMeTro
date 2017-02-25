package it.wlp.android.toast.external;

import android.widget.Toast;

public interface IToastHelper 
{
	 void createToastMessage(int iText);
	 void createToastMessage(int iText, int iImage);
	 void createToastMessage(String Text, int iImage);
	Toast getToastMessage(int iText);
	void stopToastMessage(Toast toast);
}
