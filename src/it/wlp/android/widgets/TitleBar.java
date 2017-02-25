package it.wlp.android.widgets;

import it.mygeo.project.R;
import it.wlp.android.system.bean.G30Bean;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBar extends LinearLayout implements OnClickListener {

//	private static final String TAG = "TitleBar";
	private TextView mTitleView;
	private Button mBackBtn;
	private Button mPowerBtn;
	private Activity activity;
	private String exchangeValue;
	private int marker;
	private G30Bean g30bean;
	
	
	public TitleBar(Context context) {
		super(context);
	}
	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this, true);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mTitleView = (TextView) findViewById(R.id.title_title);
		mBackBtn = (Button) findViewById(R.id.title_confirm);
		mPowerBtn = (Button) findViewById(R.id.title_power);
		
		mPowerBtn.setOnClickListener(this);
		mBackBtn.setOnClickListener(this);
	}
	public void onClick(View v) {
		if (v.getId() ==  R.id.title_power)
				activity.finish();
		else if (v.getId() ==  R.id.title_confirm)
		{
			activity.finish();
		}
	}
	
	public Button getBackButton(){
		return mBackBtn;
	}
	
	public Button getPowerButton(){
		return mPowerBtn;
	}
	
	public void setTitle(int resid){
		mTitleView.setText(resid);
	}
	
	public void setTitle(String text){
		mTitleView.setText(text);
	}
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public String getExchangeValue() {
		return exchangeValue;
	}
	public void setExchangeValue(String exchangeValue) {
		this.exchangeValue = exchangeValue;
	}
	public TextView getmTitleView() {
		return mTitleView;
	}
	public void setmTitleView(TextView mTitleView) {
		this.mTitleView = mTitleView;
	}
	public int getMarker() {
		return marker;
	}
	public void setMarker(int market) {
		this.marker = market;
	}
	public G30Bean getG30bean() {
		return g30bean;
	}
}
