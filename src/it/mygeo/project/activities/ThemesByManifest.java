package it.mygeo.project.activities;


import it.mygeo.project.R;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.handler.manage.ManageDialogHandler;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.proxy.external.IProxyView;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

/**
 * This activity's theme is set in the manifest.  It does not need to set the
 * theme in the onCreate() method.
 *
 * @author Matt Quigley
 *
 */
public class ThemesByManifest extends Activity
{
	IProxyView iProxyView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		iProxyView = new ProxyView(this, this);
		iProxyView.init();
	}
	
	@Override
	protected Dialog onCreateDialog(int id) 
	{
		ManageDialogHandler dialogHandler = new ManageDialogHandler();
		DialogElement dialogElement =  dialogHandler.createDialogElement(this, this , id);
		
		return dialogElement.getDialog();
	}	
}