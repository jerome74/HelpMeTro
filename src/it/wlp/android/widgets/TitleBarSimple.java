package it.wlp.android.widgets;

import it.mygeo.project.R;
import it.wlp.android.system.bean.G30Bean;

import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBarSimple extends LinearLayout implements OnClickListener {

//	private static final String TAG = "TitleBar";
	private TextView mTitleView;
	private Button mBackBtn;
	private Activity activity;
	private String exchangeValue;
	private boolean isGeo;
	private int marker;
	private String type;
	private G30Bean g30bean;
	
	
	public TitleBarSimple(Context context) {
		super(context);
	}
	public TitleBarSimple(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title_not_ok, this, true);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mTitleView = (TextView) findViewById(R.id.title_title);
		mBackBtn = (Button) findViewById(R.id.title_power);
		mBackBtn.setOnClickListener(this);
	}
	public void onClick(View v) {
		if (v.getId() ==  R.id.title_power)
				activity.finish();
	}
	
	public Button getBackButton(){
		return mBackBtn;
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
	public void isGeo() {
		isGeo = true;
	}
	public int getMarker() {
		return marker;
	}
	public void setMarker(int market) {
		this.marker = market;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public G30Bean getG30bean() {
		return g30bean;
	}
	public void setUpdateG30bean(String updateG30bean) 
	{
		TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter('|');
		 // Once per string to split
		 splitter.setString(updateG30bean);
		 Iterator<String> iterator =  splitter.iterator();
		
		g30bean = new G30Bean();
		
		g30bean.setId(Integer.parseInt(iterator.next()));
		g30bean.setMarker(Integer.parseInt(iterator.next()));
		g30bean.setTitle(iterator.next());
		g30bean.setLatitude(Double.parseDouble(iterator.next()));
		g30bean.setLongitude(Double.parseDouble(iterator.next()));
		g30bean.setType(iterator.next());
		
	}
}
