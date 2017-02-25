package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.util.Log;

public class VoiceActivity extends Activity
{

	private String messaggio;
	private int SPEECH_REQUEST_CODE = 1234;
	private String language;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        language = getString(R.string.extra_language);
        
        sendRecognizeIntent();
    }
	
	
	@Override
    protected void
            onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SPEECH_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                ArrayList<String> parole = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                
                if (parole.size() == 0)
                {
                   messaggio = getString(R.string.vx_not_valid);
                }
                else
                {
                    messaggio = parole.get(0);
                    finish();
                   
                }
            }
            else
            {
            	messaggio = getString(R.string.vx_not_ok);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
	
	
	private void sendRecognizeIntent()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,language);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, language); 
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, language);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getIntent().getStringExtra(UTIL_GEO.EXTRA_PROMPT));
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 2);   
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }
	
	@Override
	public void finish() 
	{
		try 
		{
				Message msg = Message.obtain();
				msg.obj = messaggio;

				if (getIntent().getExtras().getString(UTIL_GEO.NB_TO_NOTIFY).equals(UTIL_GEO.NB_VoiceSeek_NOTIFY)) 
						NotifyBean.notifyEvent(UTIL_GEO.NB_VoiceSeek, msg);
				else
						NotifyBean.notifyEvent(UTIL_GEO.NB_VoiceStyle, msg);
				
		}
		catch (Exception e) 
		{
			Log.w(UTIL_GEO.HELPMETRO,  e);
		}
		
		super.finish();
	}
	
}
