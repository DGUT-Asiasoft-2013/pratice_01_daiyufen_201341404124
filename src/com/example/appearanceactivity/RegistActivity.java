package com.example.appearanceactivity;

import com.example.singleTextInputFragment.SingleTextViewInputFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegistActivity extends Activity {
	
	SingleTextViewInputFragment fragment_num;
	SingleTextViewInputFragment fragment_password;
	SingleTextViewInputFragment fragment_repeat_password;
	SingleTextViewInputFragment fragment_email;
	
	
	private Button commit_btn;
	//SingleImageInputFragment fragment_image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_mian);
		initregie();
	}
	
	public void initregie() {
		fragment_num=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_one);
		fragment_password=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_two);
		fragment_repeat_password=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_three);
		fragment_email=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_five);
	
		commit_btn=(Button) findViewById(R.id.commit);
		commit_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intit=new Intent(RegistActivity.this, LoginActivity.class);
				startActivity(intit);
				finish();
			}
		});
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		fragment_num.setinputSingleTextLabel("用户名：");
		fragment_num.setinputSingleTextHintLabel("请输入你的用户名");
		fragment_password.setinputSingleTextLabel("密码：");
		fragment_password.setinputSingleTextHintLabel("请输入你的密码：");
		fragment_password.setIspassword(true);
		fragment_repeat_password.setinputSingleTextLabel("重复密码：");
		fragment_repeat_password.setIspassword(true);
		
		fragment_email.setinputSingleTextHintLabel("注册邮箱：");
		fragment_email.setinputSingleTextHintLabel("请输入你的邮箱");
	}

}
