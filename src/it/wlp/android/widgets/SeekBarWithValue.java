package it.wlp.android.widgets;

import it.mygeo.project.R;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


  public class SeekBarWithValue extends RelativeLayout {

	    private int mMax = 100;
	    private TextView mMinText;
	    private TextView mMaxText;
	    private TextView mCurrentText;
	    private SeekBar mSeek;
	    
	    public SeekBarWithValue(Context paramContext)
	    {
	      super(paramContext);
	    }

	    public SeekBarWithValue(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        LayoutInflater.from(getContext()).inflate(R.layout.distance, this);
	        // the minimum value is always 0
	        mMinText = new TextView(null);//(TextView) findViewById(R.id.curentValue);
	        mMinText.setText("0");
	        mMaxText = new TextView(null);//(TextView) findViewById(R.id.xValue);
	        mCurrentText = new TextView(null);//(TextView) findViewById(R.id.curentValue);
	        mSeek = (SeekBar) findViewById(R.id.seekBar);
	        mSeek.setMax(100);
	        mMaxText.setText(String.valueOf(mSeek.getMax()));
	    }

	    /**
	     * This needs additional work to make the current progress text stay
	     * right under the thumb drawable.
	     * 
	     * @param newProgress
	     *            the new progress for which to place the text
	     */
	    public void updateCurrentText(int newProgress) {
	        mCurrentText.setText(String.valueOf(newProgress));
	        final int padding = mMinText.getWidth() + mSeek.getPaddingLeft();
	        final int totalSeekWidth = mSeek.getWidth();
	        final RelativeLayout.LayoutParams lp = (LayoutParams) mCurrentText
	                .getLayoutParams();
	        final int seekLocation = (mSeek.getProgress() * totalSeekWidth)
	                / mMax - mCurrentText.getWidth() / 2;
	        lp.leftMargin = seekLocation + padding;
	        mCurrentText.setLayoutParams(lp);
	    }

	    public SeekBar getSeekBar() {
	        return mSeek;
	    }

	    public void updateSeekMaxValue(int newValue) {
	        mMax = newValue;
	        mMaxText.setText(mMax);
	        mSeek.setMax(mMax);
	    }

	}