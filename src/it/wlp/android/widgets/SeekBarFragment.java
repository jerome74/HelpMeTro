package it.wlp.android.widgets;

import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_KM;
import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_KMS;
import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_METRS;
import static it.mygeo.project.constants.UTIL_GEO.PRE_LABEL_DIST;
import static it.mygeo.project.constants.UTIL_GEO.SPACE;
import it.mygeo.project.R;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {

	private TitleBar bar;
    private SeekBar mSeekBar;
    private TextView textViewCurrentValue;
    private String strTextViewCurrentValue;
	private String selectedValue;
	private int progress;
	
    
    private Activity activity;

    public SeekBarFragment() 
    {
	}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content, Bundle savedInstanceState) 
    {
        View view 						= inflater.inflate(R.layout.distance, content, false);
        mSeekBar 						= (SeekBar) view.findViewById(R.id.seekBar);
        textViewCurrentValue 			= (TextView)view.findViewById(R.id.curentValue);
        bar 							= (TitleBar)view.findViewById(R.id.titleBarS);
        
		bar.setTitle(R.string.title_range_station_label_distance);
	    bar.setActivity(activity);
	    mSeekBar.setOnSeekBarChangeListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        mSeekBar.setProgress(progress);
        return view;
    }

    /** {@inheritDoc} */
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
    {
		selectedValue = String.valueOf(progress);
		
    	if(progress <= 25)
    	{
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("100").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_METRS)));
    	}
    	else if(progress > 25 &&  progress <= 50){
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("250").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_METRS)));
    	}
    	else if(progress > 51 &&  progress <= 60){
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("500").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_METRS)));
    	}
    	else if(progress > 61 &&  progress <= 70){
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("1").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_KM)));
    	}
    	else if(progress > 71 &&  progress <= 80){
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("3").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_KMS)));
    	}
    	else if(progress > 81 &&  progress <= 90){
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("7").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_KMS)));
    	}
    	else if(progress > 90){
    		strTextViewCurrentValue = new String(PRE_LABEL_DIST.concat("15").concat(SPACE).concat(activity.getString(POST_LABEL_DIST_KMS)));
    	}
    		
    	textViewCurrentValue.setText(strTextViewCurrentValue);
    }

    /** {@inheritDoc} */
    public void onStartTrackingTouch(SeekBar seekBar) 
    {
    }

    /** {@inheritDoc} */
    public void onStopTrackingTouch(SeekBar seekBar) 
    {
    }

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
    
	public String getSelectedValue() {
		return selectedValue;
	}
	
	public String getStrTextViewCurrentValue() {
		return strTextViewCurrentValue;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
}
