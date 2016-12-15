package com.example.appearanceactivity;

import java.io.IOException;

import com.example.servelet.Servelet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

public class Add_Click_Activity extends Activity {

	private Button add_commit_btn;                //�ύ��ť
	private EditText add_top_edittext;              //���������
	private EditText add_bottom_edittext;            //���������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_edittext_activity);
		init();
		add_commit_btn.setOnClickListener(new AddCommitClickListener());
		
	}
	public void init() {
		add_commit_btn=(Button) findViewById(R.id.add_commit_btn);
		add_top_edittext=(EditText) findViewById(R.id.add_top_EditText);
		add_bottom_edittext=(EditText) findViewById(R.id.add_bottom_editext);
		
	}
	
	class AddCommitClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			AddArticle();
		}
		
	}

	public void AddArticle() {
		String title=add_top_edittext.getText().toString();
		String text=add_bottom_edittext.getText().toString();
		//���û������´����������б�Ǻõ�String������
		MultipartBody body=new MultipartBody.Builder()
				.addFormDataPart("title", title)
				.addFormDataPart("text", text)
				.build();
		
		//����request
		Request request=Servelet.requestuildApi("article")
				.method("post", null)
				.post(body)
				.build();
		//�����ͻ�������
		Servelet.getOkHttpClient().newCall(request)
		.enqueue(new Callback() {
			
			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				//arg1.body().string()���������߳�������
				final String string=arg1.body().string();
				Add_Click_Activity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Add_Click_Activity.this.onResponse(arg0, string);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
			}
			
			@Override
			public void onFailure(final Call arg0, final IOException arg1) {
				Add_Click_Activity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {

						Add_Click_Activity.this.onFailure(arg0, arg1);
					}
				});
				
			}
		});
	}
	
	public void onResponse(Call arg0, String arg1)
	{
		new AlertDialog.Builder(this).setTitle("�ύ�ɹ�")
		.setMessage(arg1.toString())
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				add_top_edittext.setText("");
				add_bottom_edittext.setText("");
			}
		}).show();
	}
	
	public void  onFailure(Call arg0, Exception arg1) {
		new AlertDialog.Builder(this).setTitle("�ύʧ��")
		.setMessage(arg1.getMessage())
		.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				add_top_edittext.setText("");
				add_bottom_edittext.setText("");
			}
		}).show();
	}
}
