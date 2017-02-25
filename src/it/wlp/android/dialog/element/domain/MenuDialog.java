package it.wlp.android.dialog.element.domain;

import it.mygeo.project.R;
import it.wlp.android.dialog.element.DialogElement;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class MenuDialog implements DialogElement { 

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

		View view = View.inflate(this.context, R.layout.menu_adv, null);

		final Button b_si = (Button) view.findViewById(R.id.ui_si);

		b_si.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				activity.finish();
			}
		});
		
		final Button b_no = (Button) view.findViewById(R.id.ui_no);

		b_no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
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
