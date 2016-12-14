package com.example.appearanceactivity;

import java.io.IOException;

import com.example.servelet.Servelet;
import com.example.tabFragment.Article;

import android.R.string;
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

/*
 * ���۱༭�ύ����
 */
public class CommentTextActivity extends Activity {

	private Button comment_send;
	private EditText Comment_edit;
	
	Article artical;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comment_content);
		init();
		comment_send.setOnClickListener(new CommentSendOnClickListener());
		artical=(Article) getIntent().getSerializableExtra("article");
	}
	private void init() {
		comment_send=(Button) findViewById(R.id.comment_send);
		Comment_edit=(EditText) findViewById(R.id.comment_ed);
	}
	
	class CommentSendOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Add_Comment();
		}
		
	}

	/*
	 * �ϴ�����
	 */
	public void Add_Comment() {
		//�����������
		String content=Comment_edit.getText().toString();
		//���û������۴����������б�Ǻõ�String�����У�content�������������ǵ�һ��
				MultipartBody body=new MultipartBody.Builder()
						.addFormDataPart("content", content)
						.build();
				
				//����request
				Request request=Servelet.requestuildApi("/article/"+artical.getId()+"/comment")
						.method("post", null)
						.post(body)
						.build();
				
				//�ͻ��˷�������
				Servelet.getOkHttpClient().newCall(request).enqueue(new Callback() {
					
					@Override
					public void onResponse(final Call arg0, final Response arg1) throws IOException {
						
						try {
							//���Ǻ�ִ̨�еģ���ǰִ̨��ʱ�����Ȱ��������ַ�����
							String ar=arg1.body().string();
							CommentTextActivity.this.onResponse(arg0, ar);
						} catch (final Exception e) {
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									new AlertDialog.Builder(CommentTextActivity.this).setTitle("�ύʧ��")
									.setMessage(e.toString())
									.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											Comment_edit.setText("");
										}
									}).show();
								}
							});
						}
					}
					
					@Override
					public void onFailure(Call arg0, IOException arg1) {
						CommentTextActivity.this.onFailure(arg0, arg1);
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
				Comment_edit.setText("");
			}
		}).show();
	}
	
	public void  onFailure(Call arg0, Exception arg1) {
		new AlertDialog.Builder(this).setTitle("�ύʧ��")
		.setMessage(arg1.getMessage())
		.setNegativeButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Comment_edit.setText("");
			}
		}).show();
	}

	
	
}
