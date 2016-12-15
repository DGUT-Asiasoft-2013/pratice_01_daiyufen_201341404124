package com.example.appearanceactivity;

import java.io.IOException;

import com.example.servelet.Servelet;
import com.example.tabFragment.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity {
	private Button regist_btn;
	private Button login_btn;

	private TextView forget_password_tv; // 忘记密码
	private EditText num_ed;
	private EditText password_ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_mian);

		init(); // 初始化
		forget_password_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Forget_password();
			}
		});

		regist_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
			}
		});

		login_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Login_into();

			}
		});
	}

	public void init() {

		regist_btn = (Button) findViewById(R.id.button2);
		login_btn = (Button) findViewById(R.id.button1);

		num_ed = (EditText) findViewById(R.id.number);
		password_ed = (EditText) findViewById(R.id.password_ed);

		forget_password_tv = (TextView) findViewById(R.id.forget_tv);
	}

	protected void Login_into() {
		// 获得账户
		String num = num_ed.getText().toString();
		// 获得密码
		String password = password_ed.getText().toString();

		// 以下为进度提示框
		final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
		progressDialog.setTitle("登录中");
		progressDialog.setMessage("请稍后");
		progressDialog.setCancelable(false); // 设置其不能取消
		progressDialog.setCanceledOnTouchOutside(false);
		// 开始
		progressDialog.show();

		//OkHttpClient client = new OkHttpClient();

		// 创建客户端
		OkHttpClient client=Servelet.getOkHttpClient();
		// 把用户登录的信息传给服务器中标记好的String类型中
		MultipartBody.Builder body = new MultipartBody
				.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("num", num)
				.addFormDataPart("password", password);
		// 创建请求
		/*Request request = new Request.Builder()
				.url("http://172.27.0.37:8080/membercenter/api/login")
				.post(body.build())
				.build();*/
		Request request = Servelet
				.requestuildApi("login")
				.post(body.build())
				.build();
		// 客户端发送一个请求newCall（），然后enqueue()进去对列，最后Callback()发送回连接的成功与否的信息
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call arg0, final Response arg1) throws IOException {
				final String string=arg1.body().string();
				
				LoginActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						try {
							//获得解析数据
							User user;
							progressDialog.dismiss();
							ObjectMapper objectMapper=new ObjectMapper();
							
							user=objectMapper.readValue(string, User.class);
							
							new AlertDialog.Builder(LoginActivity.this)
							.setTitle("登录成功")
							.setMessage(user.getName()+","+user.getAccount())
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									Intent intent=new Intent(LoginActivity.this, AppActivity.class);
									startActivity(intent);
									finish();
								}
							}).show();
							
						} catch (final JsonParseException e) {
							LoginActivity.this.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									progressDialog.dismiss();
									new AlertDialog.Builder(LoginActivity.this)
									.setTitle("失败1")
									.setMessage(e.toString())
									.setNegativeButton("ok", null)
									.show();

								}
							});
						} catch (final JsonMappingException e) {
							LoginActivity.this.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									progressDialog.dismiss();
									new AlertDialog.Builder(LoginActivity.this)
									.setTitle("失败2")
									.setMessage(e.toString())
									.setNegativeButton("ok", null)
									.show();

								}
							});
						} catch (final IOException e) {
							LoginActivity.this.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									progressDialog.dismiss();
									new AlertDialog.Builder(LoginActivity.this)
									.setTitle("失败3")
									.setMessage(e.toString())
									.setNegativeButton("ok", null)
									.show();

								}
							});
						}

					}
				});
			}

			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				LoginActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						progressDialog.dismiss();
						//Toast.makeText(LoginActivity.this, arg1.toString(), Toast.LENGTH_SHORT).show();
						new AlertDialog.Builder(LoginActivity.this)
						.setTitle("失败登录")
						.setMessage(arg1.toString())
						.setNegativeButton("ok", null)
						.show();

					}
				});
			}
		});

	}

	protected void Forget_password() {
		Intent forget_intent = new Intent(LoginActivity.this, ForgetPassord_Activity.class);
		startActivity(forget_intent);
		finish();
	}

}
