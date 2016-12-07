package com.example.appearanceactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FeedListViewActivity extends Activity {

	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedlistviewactivity);
		tv=(TextView) findViewById(R.id.feed_liatview_textView);
		String text=getIntent().getStringExtra("cont");
		tv.setText(text);
	}
}
