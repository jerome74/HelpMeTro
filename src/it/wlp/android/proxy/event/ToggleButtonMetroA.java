package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.util.Observable;
import java.util.Observer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.ToggleButton;

public class ToggleButtonMetroA implements Observer ,  PreferenceCallBack
{
	
	private ToggleButton toggleButton;
	private ProxyView proxyView;
	private IToastHelper iToastHelper;
	private ToastHelperDomain toastHelperDomain;
	
	
	public ToggleButtonMetroA(View toggleButton) 
	{
		super();
		this.toggleButton = (ToggleButton)toggleButton;
//		this.toggleButton.setTextColor(Color.WHITE);
//		this.toggleButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//		this.toggleButton.setTypeface(null, Typeface.BOLD_ITALIC);
	}
	
	@Override
	public void returnServiceResponse() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		toggleButton.setOnClickListener(clickListener);
		 
		iToastHelper 		= new ToastHelper(proxyView.getContext());
		toastHelperDomain 	= new ToastHelperDomain(iToastHelper);
		
	}
	
	View.OnClickListener clickListener = new View.OnClickListener() 
	   {
			public void onClick(View arg0) 
			{
				
					 ToggleButton tb = (ToggleButton)proxyView.getActivity().findViewById(R.id.metroBButton);
					 
					 switch (tb.getVisibility()) 
					 {
					 	case View.GONE:
					 		tb.setVisibility(View.VISIBLE);
					 	break;
					 	
					 	case View.VISIBLE:
					 		tb.setVisibility(View.GONE);
						break;

					 	default:
						break;
//					}
				 }
			}
	   };


	@Override
	public void returnServiceResponse(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
