package com.example.singleTextInputFragment;

import com.example.appearanceactivity.R;
import com.example.appearanceactivity.forget_two_page_Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ForgetPasswordFragment extends Fragment {
	
	SingleTextViewInputFragment forget_email;

	View view;
	
	private Button next_btn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			view=inflater.inflate(R.layout.forget_passwor_main, container);
			forget_email=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.forget_fragment);

			next_btn=(Button) view.findViewById(R.id.next_btn);
			next_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					GotoNext();
				}
			});
		}
		
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		forget_email.setinputSingleTextLabel(" ‰»Î” œ‰");
		forget_email.setinputSingleTextHintLabel("«Î ‰»Îƒ„◊‘º∫µƒ◊¢≤·” œ‰");
	}

	protected void GotoNext() {
		Intent i=new Intent(getActivity(), forget_two_page_Activity.class);
		startActivity(i);
		
	}
	
	
}
