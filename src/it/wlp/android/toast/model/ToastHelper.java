package it.wlp.android.toast.model;

import it.mygeo.project.R;
import it.wlp.android.toast.external.IToastHelper;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastHelper  implements IToastHelper
{

	Context context;
	Activity activity;
	
	
	
	
	public ToastHelper(Context context) {
		super();
		this.context = context;
	}

	public void createToastMessage(int iText)
	{
		
		View view = View.inflate(this.context, R.layout.alert_toast, null);

		TextView text = (TextView) view.findViewById(R.id.t_text);
		text.setText(iText);

		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(view);
		toast.show();
	}
	
	/**
	 * @method createToastMessage
	 * @args int iText, int iImage
	 * @return void
	 */
	
	public void createToastMessage(int iText, int iImage)
	{
		try {
			View view = View.inflate(this.context, R.layout.alert_toast, null);

			TextView text = (TextView) view.findViewById(R.id.t_text);
			text.setText(iText);
			
			ImageView image = (ImageView) view.findViewById(R.id.t_img);
			image.setImageResource(iImage);

			Toast toast = new Toast(context);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(view);
			toast.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createToastMessage(String Text, int iImage) 
	{
		View view = View.inflate(this.context, R.layout.alert_toast, null);

		TextView text = (TextView) view.findViewById(R.id.t_text);
		text.setText(Text);
		
		ImageView image = (ImageView) view.findViewById(R.id.t_img);
		image.setImageResource(iImage);

		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(view);
		toast.show();
	}
	
	@Override
	public Toast getToastMessage(int iText)
	{
		
		View view = View.inflate(this.context, R.layout.alert_toast, null);

		TextView text = (TextView) view.findViewById(R.id.t_text);
		text.setText(iText);

		Toast toast = new Toast(context);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(view);
		toast.show();
		
		return toast;
	}
	
	@Override
	public void stopToastMessage(Toast toast)
	{
		toast.cancel();
	}
	
}
