package it.wlp.android.dialog.element.domain;

import it.mygeo.project.R;
import it.wlp.android.dialog.element.DialogElement;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class M30MenuDialog implements DialogElement { 

	Builder builder;
	Context context;
	Activity activity;
	Dialog dialog;
	EditText et;
	Integer returValue = 0;
	
	public Dialog getDialog() 
	{
		this.dialog = builder.create();
		return dialog;
	}

	public void createDialog(final View textView)
 {

		View view = View.inflate(this.context, R.layout.adv_desc_g30, null);

		final Button b_ok = (Button) view.findViewById(R.id.b_ok);

		b_ok.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			{
				et.getPaint().setUnderlineText(false);
				String testo = et.getText().toString();
				((TextView) textView).setText(testo);				
				dialog.dismiss();

			}});
		
		final Button b_no = (Button)view.findViewById(R.id.b_insert_desc);
		et = (EditText)view.findViewById(R.id.et_desc_g30);
		
		if(!((TextView) textView).getText().equals(activity.getString(R.string.title_mygeo_label_g30)))
			et.setText(((TextView) textView).getText());
		
		

		b_no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) 
			{
				dialog.dismiss();
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
	public void createDialog() {
		// TODO Auto-generated method stub
		
	}
	
	private class SpanNoUnderline extends URLSpan {
        public SpanNoUnderline(String url) {
            super(url);
        }
        @Override public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

	@Override
	public void setExtraObj(Object object) {
		// TODO Auto-generated method stub
		
	}
}
