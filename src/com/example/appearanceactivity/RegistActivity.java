package com.example.appearanceactivity;

import com.example.singleTextInputFragment.SingleImageInputFragment;
import com.example.singleTextInputFragment.SingleTextViewInputFragment;

import android.app.Activity;
import android.os.Bundle;

public class RegistActivity extends Activity {
	
	SingleTextViewInputFragment fragment_num;
	SingleTextViewInputFragment fragment_password;
	SingleTextViewInputFragment fragment_repeat_password;
	
	SingleImageInputFragment fragment_image;
	
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
	
		fragment_image=(SingleImageInputFragment)getFragmentManager().findFragmentById(R.id.fragment_four);
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		fragment_num.setinputSingleTextLabel("用户名：");
		fragment_num.setinputSingleTextHintLabel("请输入你的用户名");
		fragment_password.setinputSingleTextLabel("密码：");
		fragment_password.setinputSingleTextHintLabel("请输入你的密码：");
		fragment_repeat_password.setinputSingleTextLabel("重复密码：");
		fragment_repeat_password.setIspassword(true);
	}

}
