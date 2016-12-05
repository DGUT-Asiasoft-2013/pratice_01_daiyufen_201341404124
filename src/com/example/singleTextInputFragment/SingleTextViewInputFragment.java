package com.example.singleTextInputFragment;

import com.example.appearanceactivity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class SingleTextViewInputFragment extends SingleAbstractSourse {

	//调用弱引用，会使得fragment里垃圾回收的时候，不会被回收
	private EditText ed;
	private TextView tv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.singleinput_fragment, null);
		ed=(EditText) rootView.findViewById(R.id.single_input_number);
		tv=(TextView) rootView.findViewById(R.id.sing_input_textView);
		return rootView;
	}
	public void setinputSingleTextLabel(String label) {
		tv.setText(label);
		
	}
	
	public void setinputSingleTextHintLabel(String hintlabel) {
		tv.setHint(hintlabel);
		
	}
	
	public void setIspassword(boolean password) {
		if (password) {
			ed.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
		}
		else {
			ed.setInputType(EditorInfo.TYPE_CLASS_TEXT);
		}
	}
}
