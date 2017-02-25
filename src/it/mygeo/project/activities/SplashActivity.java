/**
 * 
 */
package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.mygeo.project.handler.SplashHandler;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;

/**
 * @author jerome
 *
 */
public class SplashActivity extends Activity 
{
	
	private SplashHandler objSplashHandler;
	private Message objSplashMessage;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        objSplashHandler = new SplashHandler(SplashActivity.this, SplashActivity.this);
        
        objSplashMessage = new Message();
        
        objSplashMessage.what = 0;
        
        objSplashHandler.sendMessageDelayed(objSplashMessage, 1000);
        
        
    }
}
