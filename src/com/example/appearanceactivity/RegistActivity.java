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
		
		fragment_num.setinputSingleTextLabel("�û�����");
		fragment_num.setinputSingleTextHintLabel("����������û���");
		fragment_password.setinputSingleTextLabel("���룺");
		fragment_password.setinputSingleTextHintLabel("������������룺");
		fragment_password.setIspassword(true);
		fragment_repeat_password.setinputSingleTextLabel("�ظ����룺");
		fragment_repeat_password.setIspassword(true);
		
		fragment_email.setinputSingleTextHintLabel("ע�����䣺");
		fragment_email.setinputSingleTextHintLabel("�������������");
	}

}
