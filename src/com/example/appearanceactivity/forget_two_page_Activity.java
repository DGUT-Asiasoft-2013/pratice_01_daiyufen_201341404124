package com.example.appearanceactivity;

import java.io.IOException;

import com.example.servelet.Servelet;
import com.example.singleTextInputFragment.ForgetPasswordFragment;
import com.example.singleTextInputFragment.SingleTextViewInputFragment;
import com.example.tabFragment.News_fragment;
import com.example.widget.view.MD5;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class forget_two_page_Activity extends Activity {

	SingleTextViewInputFragment forget_emial;// 验证码

	SingleTextViewInputFragment forget_password; // 输入新密码
	SingleTextViewInputFragment forget_repeat_password; // 重新输入新密码

	private Button commit_btn; // 提交按钮
	String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// retrive the email address from intent.extra
		email=getIntent().getStringExtra("email");
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
				//如果密码相同就上传密码
				getRepeatPassword();

				
			} else {
				//否则输出提示框
				new AlertDialog.Builder(forget_two_page_Activity.this).setTitle("提示").setMessage("你两次输入的密码buzhengque")
						.setNegativeButton("确定", null).show();
			}
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

	public void getRepeatPassword() {

		// 创建客户端
		OkHttpClient client = Servelet.getOkHttpClient();
		// 把用户设置密码的信息传给服务器中标记好的String类型中
		MultipartBody body = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("email", email)            //左边的email是对应服务器的
				.addFormDataPart("password", forget_password.getText()).build();
				//.addFormDataPart("password", MD5.getMD5(forget_password.getText()));
		// 创建请求
		Request request = Servelet.requestuildApi("email").post(body).build();

		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(final Call arg0, Response arg1) throws IOException {
				try{
					//创建一个Boolean类型的值
					final Boolean succeed = new ObjectMapper().readValue(arg1.body().string(), Boolean.class);
					//runOnUiThread()代替Handle,即Activity.runOnUiThread(Runnable)，相当于主线程，可以在此更新UI
					runOnUiThread(new Runnable() {
						public void run() {
							if (succeed) {
								forget_two_page_Activity.this.onResponse(arg0, "succeed is true");
							}else{
								forget_two_page_Activity.this.onFailure(arg0, new Exception("succeed if false"));
							}
						}
					});
				}catch(final Exception e){
					runOnUiThread(new Runnable() {
						public void run() {
							forget_two_page_Activity.this.onFailure(arg0, e);
						}
					});
				}
				
				//forget_two_page_Activity.this.onResponse(arg0, arg1.body().string());
				//Toast.makeText(forget_two_page_Activity.this, arg1.body().string(), Toast.LENGTH_SHORT).show();
//				Intent intent = new Intent(forget_two_page_Activity.this, LoginActivity.class);
//				startActivity(intent);
//				finish();
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				//forget_two_page_Activity.this.onFailure(arg0, arg1);
				//Toast.makeText(forget_two_page_Activity.this, arg1.toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	void onResponse(Call arg0, String response)
	{
		new AlertDialog.Builder(forget_two_page_Activity.this)
		.setTitle("提交成功")
		.setMessage("您提交信息成功")
		.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(forget_two_page_Activity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}).show();
	}
	
	void onFailure(Call arg0, Exception arg1)
	{
		new AlertDialog.Builder(forget_two_page_Activity.this)
		.setTitle("提交失败")
		.setMessage(arg1.getMessage())                  //arg1.getMessage()会输出HTML文件用于显示错误
		.setPositiveButton("确定",null)
		.show();
	}
}
