package com.example.appearanceactivity;

import com.example.singleTextInputFragment.SingleTextViewInputFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class forget_two_page_Activity extends Activity {
	
	SingleTextViewInputFragment forget_emial;
	
	SingleTextViewInputFragment forget_password;
	SingleTextViewInputFragment forget_repeat_password;
	
	private Button commit_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_page_forget);
		initabb();
	}
	public void initabb() {
		forget_emial=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_forget_email);
		forget_password=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_forget_password);
		forget_repeat_password=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_forget_repeat_password);
		commit_btn=(Button) findViewById(R.id.forget_repeat_comit_btn);
		
		commit_btn.setOnClickListener(new commitOnClickListener());
		
	}
	
	class commitOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Intent intent=new Intent(forget_two_page_Activity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		forget_emial.setinputSingleTextLabel("验证码：");
		forget_password.setinputSingleTextLabel("输入密码：");
		forget_password.setinputSingleTextHintLabel("请输入您新设置的密码：");
		forget_repeat_password.setinputSingleTextLabel("重新输入密码：");
		forget_repeat_password.setinputSingleTextHintLabel("请重新输入您新设置的密码：");
	}
}
