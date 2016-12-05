package com.example.appearanceactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		//������ʱ��Ȼ��ת����½����
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}
}
