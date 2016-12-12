package com.example.appearanceactivity;

import com.example.singleTextInputFragment.ForgetPasswordFragment;

import android.app.Activity;
import android.os.Bundle;

public class ForgetPassord_Activity extends Activity {
	
	ForgetPasswordFragment for_fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_main);
		
		for_fragment=(ForgetPasswordFragment) getFragmentManager().findFragmentById(R.id.forgot_fragment);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
