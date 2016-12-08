package com.example.appearanceactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Add_Click_Activity extends Activity {

	private Button add_commit_btn;
	private EditText add_edittext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_edittext_activity);
//		add_commit_btn=(Button) findViewById(R.id.add_commit_btn);
//		add_edittext=(EditText) findViewById(R.id.add_editext);
		init();
		add_commit_btn.setOnClickListener(new AddCommitClickListener());
		
	}
	public void init() {
		add_commit_btn=(Button) findViewById(R.id.add_commit_btn);
		add_edittext=(EditText) findViewById(R.id.add_editext);
		
	}
	
	class AddCommitClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			overridePendingTransition(0, R.anim.slide_out_bottom);
			finish();
		}

		
		
	}
}
