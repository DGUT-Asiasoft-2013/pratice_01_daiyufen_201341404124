package com.example.appearanceactivity;

import java.io.IOException;

import com.example.servelet.Servelet;
import com.example.singleTextInputFragment.ForgetPasswordFragment;
import com.example.singleTextInputFragment.SingleTextViewInputFragment;
import com.example.widget.view.MD5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class forget_two_page_Activity extends Activity {

	SingleTextViewInputFragment forget_emial; // ��֤��

	ForgetPasswordFragment for_fragment;

	SingleTextViewInputFragment forget_password; // ����������
	SingleTextViewInputFragment forget_repeat_password; // ��������������

	private Button commit_btn; // �ύ��ť

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two_page_forget);
		initabb();
	}

	public void initabb() {
		forget_emial = (SingleTextViewInputFragment) getFragmentManager().findFragmentById(R.id.fragment_forget_email);
		forget_password = (SingleTextViewInputFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_forget_password);
		forget_repeat_password = (SingleTextViewInputFragment) getFragmentManager()
				.findFragmentById(R.id.fragment_forget_repeat_password);
		commit_btn = (Button) findViewById(R.id.forget_repeat_comit_btn);

		commit_btn.setOnClickListener(new commitOnClickListener());

	}

	class commitOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (forget_password.getText().equals(forget_repeat_password.getText())) {
				//���������ͬ���ϴ�����
				getRepeatPassword();

				
			} else {
				//���������ʾ��
				new AlertDialog.Builder(forget_two_page_Activity.this).setTitle("��ʾ").setMessage("���������������buzhengque")
						.setNegativeButton("ȷ��", null).show();
			}
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

		forget_emial.setinputSingleTextLabel("��֤�룺");
		forget_password.setinputSingleTextLabel("�������룺");
		forget_password.setinputSingleTextHintLabel("�������������õ����룺");
		forget_repeat_password.setinputSingleTextLabel("�����������룺");
		forget_repeat_password.setinputSingleTextHintLabel("�����������������õ����룺");
	}

	public void getRepeatPassword() {

		// �����ͻ���
		OkHttpClient client = Servelet.getOkHttpClient();
		// ���û������������Ϣ�����������б�Ǻõ�String������
		MultipartBody.Builder body = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("emial", for_fragment.getText())
				.addFormDataPart("password", MD5.getMD5(forget_password.getText()));
		// ��������
		Request request = Servelet.requestuildApi("email").post(body.build()).build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {

				forget_two_page_Activity.this.onResponse(arg0, arg1.body().string());
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				forget_two_page_Activity.this.onFailure(arg0, arg1);
			}
		});
	}
	
	void onResponse(Call arg0, String response)
	{
		new AlertDialog.Builder(forget_two_page_Activity.this)
		.setTitle("�ύ�ɹ�")
		.setMessage("���ύ��Ϣ�ɹ�")
		.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(forget_two_page_Activity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		})
		.show();
	}
	void onFailure(Call arg0, Exception arg1)
	{
		new AlertDialog.Builder(forget_two_page_Activity.this)
		.setTitle("�ύʧ��")
		.setMessage("���ύ��Ϣʧ��")
		.setPositiveButton("ȷ��",null)
		.show();
	}
}
