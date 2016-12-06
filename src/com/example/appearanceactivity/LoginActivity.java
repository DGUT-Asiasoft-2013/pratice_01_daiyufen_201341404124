package com.example.appearanceactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private Button regist_btn;
	private Button login_btn;
	
	private TextView forget_password_tv;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_mian);
		regist_btn=(Button) findViewById(R.id.button2);
		login_btn=(Button) findViewById(R.id.button1);
		
		forget_password_tv=(TextView) findViewById(R.id.forget_tv);
		forget_password_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Forget_password();
			}
		});
		
		
		regist_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
			}
		});
		
		login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1=new Intent(LoginActivity.this, AppActivity.class);
				startActivity(intent1);
			}
		});
	}

	protected void Forget_password() {
		Intent forget_intent=new Intent(LoginActivity.this,ForgetPassord_Activity.class);
		startActivity(forget_intent);
		finish();
	}

	
}
