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
			//判断用户两次输入的密码是否一致，如果不一致则返回提示框提示用户输入不一致，否则继续执行下去
			new AlertDialog.Builder(RegistActivity.this)
			.setTitle("密码提示")
			.setMessage("您两次输入的密码不一致")
			.setNegativeButton("确定", null)
			.show();
			
			//返回
			return;
		}
		String num=fragment_num.getText();               //获得用户输入的账号
		String email=fragment_email.getText();            //获得用户输入的邮箱
		String name=fragment_name.getText();             //获得用户输入的用户名
		
		//以下为进度提示框
		final ProgressDialog progressDialog=new ProgressDialog(RegistActivity.this);
		progressDialog.setTitle("提交中");
		progressDialog.setMessage("请稍后");
		progressDialog.setCancelable(false);             //设置其不能取消
		progressDialog.setCanceledOnTouchOutside(false);
		//开始
		progressDialog.show();
		
		//创建客户端
		OkHttpClient client=new OkHttpClient();
		//把用户注册的信息传给服务器中标记好的String类型中
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
		//创建请求
		Request request=new Request.Builder()
				.url("http://172.27.0.5:8080/membercenter/api/register")
				.method("post", null)
				.post(body.build())
				.build();
		
		//发送请求
		client.newCall(request)
		.enqueue(new Callback() {
			
			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						//取消进度提示框
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
						//取消进度提示框
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
		.setTitle("提交成功")
		.setMessage("您提交信息成功")
		.setPositiveButton("确定",new DialogInterface.OnClickListener() {
			
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
		.setTitle("提交失败")
		.setMessage("您提交信息失败")
		.setPositiveButton("确定",null)
		.show();
	}
	@Override
	protected void onResume() {
		super.onResume();
		
		fragment_num.setinputSingleTextLabel("账号：");
		fragment_num.setinputSingleTextHintLabel("请输入你的账号");
		fragment_name.setinputSingleTextLabel("用户名：");
		fragment_name.setinputSingleTextHintLabel("请输入你的用户名：");
		fragment_password.setinputSingleTextLabel("密码：");
		fragment_password.setinputSingleTextHintLabel("请输入你的密码：");
		fragment_password.setIspassword(true);
		fragment_repeat_password.setinputSingleTextLabel("重复密码：");
		fragment_repeat_password.setIspassword(true);
		
		fragment_email.setinputSingleTextHintLabel("注册邮箱：");
		fragment_email.setinputSingleTextHintLabel("请输入你的邮箱");
	}

}
