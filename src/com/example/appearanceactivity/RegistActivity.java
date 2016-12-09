package com.example.appearanceactivity;

import java.io.IOException;

import com.example.singleTextInputFragment.SingleImageInputFragment;
import com.example.singleTextInputFragment.SingleTextViewInputFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistActivity extends Activity {
	
	SingleTextViewInputFragment fragment_num;
	SingleTextViewInputFragment fragment_password;
	SingleTextViewInputFragment fragment_repeat_password;
	SingleTextViewInputFragment fragment_email;
	SingleTextViewInputFragment fragment_name;
	
	SingleImageInputFragment fragment_image;
	
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
		fragment_name=(SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_name);
		fragment_image=(SingleImageInputFragment) getFragmentManager().findFragmentById(R.id.fragment_four);
	
		commit_btn=(Button) findViewById(R.id.commit);
		commit_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*Intent intit=new Intent(RegistActivity.this, LoginActivity.class);
				startActivity(intit);
				finish();*/
				commit_methor();
			}
		});
		
	}
	protected void commit_methor() {
		String password=fragment_password.getText();
		String passwordrepeat=fragment_repeat_password.getText();
		if (!password.equals(passwordrepeat)) {
			//�ж��û���������������Ƿ�һ�£������һ���򷵻���ʾ����ʾ�û����벻һ�£��������ִ����ȥ
			new AlertDialog.Builder(RegistActivity.this)
			.setTitle("������ʾ")
			.setMessage("��������������벻һ��")
			.setNegativeButton("ȷ��", null)
			.show();
			
			//����
			return;
		}
		String num=fragment_num.getText();               //����û�������˺�
		String email=fragment_email.getText();            //����û����������
		String name=fragment_name.getText();             //����û�������û���
		
		//����Ϊ������ʾ��
		final ProgressDialog progressDialog=new ProgressDialog(RegistActivity.this);
		progressDialog.setTitle("�ύ��");
		progressDialog.setMessage("���Ժ�");
		progressDialog.setCancelable(false);             //�����䲻��ȡ��
		progressDialog.setCanceledOnTouchOutside(false);
		//��ʼ
		progressDialog.show();
		
		//�����ͻ���
		OkHttpClient client=new OkHttpClient();
		//���û�ע�����Ϣ�����������б�Ǻõ�String������
		MultipartBody.Builder body=new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("num", num)
				.addFormDataPart("password", password)
				.addFormDataPart("email", email)
				.addFormDataPart("name", name);
		
		if (fragment_image.getPngData()!=null) {
			body.addFormDataPart("avatar", "avatar",
					RequestBody.create(MediaType.parse("image/png")
							, fragment_image.getPngData()));
			
		}
		//��������
		Request request=new Request.Builder()
				.url("http://172.27.0.5:8080/membercenter/api/register")
				.method("post", null)
				.post(body.build())
				.build();
		
		//��������
		client.newCall(request)
		.enqueue(new Callback() {
			
			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						//ȡ��������ʾ��
						progressDialog.dismiss();
						RegistActivity.this.onResponse(arg0,arg1.body().toString());
					}
				});
			}
			
			@Override
			public void onFailure(final Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						//ȡ��������ʾ��
						progressDialog.dismiss();
						onFailure(arg0,arg1);
					}
				});
			}
		});
		
		
		
	}

	void onResponse(Call arg0, String response)
	{
		new AlertDialog.Builder(RegistActivity.this)
		.setTitle("�ύ�ɹ�")
		.setMessage("���ύ��Ϣ�ɹ�")
		.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		})
		.show();
	}
	void onFailure(Call arg0, Exception arg1)
	{
		new AlertDialog.Builder(RegistActivity.this)
		.setTitle("�ύʧ��")
		.setMessage("���ύ��Ϣʧ��")
		.setPositiveButton("ȷ��",null)
		.show();
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		fragment_num.setinputSingleTextLabel("�˺ţ�");
		fragment_num.setinputSingleTextHintLabel("����������˺�");
		fragment_name.setinputSingleTextLabel("�û�����");
		fragment_name.setinputSingleTextHintLabel("����������û�����");
		fragment_password.setinputSingleTextLabel("���룺");
		fragment_password.setinputSingleTextHintLabel("������������룺");
		fragment_password.setIspassword(true);
		fragment_repeat_password.setinputSingleTextLabel("�ظ����룺");
		fragment_repeat_password.setIspassword(true);
		
		fragment_email.setinputSingleTextHintLabel("ע�����䣺");
		fragment_email.setinputSingleTextHintLabel("�������������");
	}

}
