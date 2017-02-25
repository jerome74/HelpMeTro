package it.wlp.android.dialog.element.domain;

import it.mygeo.project.R;
import it.mygeo.project.activities.StationListPreferenceActivity;
import it.mygeo.project.constants.UTIL_GEO;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.handler.manage.ManageIntentHandler;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class DialogTextView implements DialogElement { 

	Builder builder;
	Context context;
	Activity activity;
	Dialog dialog;
	
	public Dialog getDialog() 
	{
		this.dialog = builder.create();
		return dialog;
	}

	public void createDialog() 
	{
        
        View view = View.inflate(this.context, R.layout.adv, null);
        
        final Button b_simple = (Button)view.findViewById(R.id.b_simple);

        
        b_simple.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	IToastHelper iToastHelper = new ToastHelper(context);
            	ToastHelperDomain toastHelperDomain = new ToastHelperDomain(iToastHelper);
            	//toastHelperDomain.createToastMessage(R.string.no_function, R.drawable.stop);
            }
        });
        
        final Button b_avd = (Button)view.findViewById(R.id.b_adv);
        
        b_avd.setOnClickListener(new View.OnClickListener() 
        {
            @SuppressWarnings({ "unchecked", "rawtypes" })
			public void onClick(View v) 
            {
            	dialog.dismiss();
            	ManageIntentHandler<StationListPreferenceActivity, Object> 
            	manageIntentHandler = new ManageIntentHandler<StationListPreferenceActivity, Object>(); 
            	
				IntentActivity activity = (IntentActivity) manageIntentHandler.createDialogElement(context
            											, UTIL_GEO.STYLE_PREFERENCE_ACTIVITY_VALUE
            											, StationListPreferenceActivity.class
            											, null);
            	
            	activity.start();
            	
            	
            	
            	
            }
        });
        
        
        
        builder.setView(view);
        builder.setCancelable(false);
	}

	public void setBuilder(Builder builder) 
	{
		this.builder = builder;
	}

	@Override
	public void setContext(Context context) 
	{
		this.context = context;
		
	}

	@Override
	public void setActivity(Activity activity) 
	{
		this.activity = activity;
	}

	@Override
	public void createDialog(View textView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExtraObj(Object object) {
		// TODO Auto-generated method stub
		
	}

}
