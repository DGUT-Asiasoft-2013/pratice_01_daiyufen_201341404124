package com.example.appearanceactivity;

import java.io.IOException;

import android.app.Activity;
import okhttp3.Request;
import okhttp3.Request.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		/*//创建延时，然后转到登陆界面
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);*/
		
		/*
		 *以下为连接网路操作
		 */
		//创建客户端
		OkHttpClient client=new OkHttpClient();
		//创建请求
		String string = "http://172.27.0.5:8080/menbercenter/hello";
		Request request=new Request.Builder().url(string).method("get", null).build();
		//客户端发送一个请求newCall（），然后enqueue()进去对列，最后Callback()发送回连接的成功与否的信息
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, final Response arg1) throws IOException {
				//成功的时候返回数据
				MainActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(MainActivity.this, arg1.toString(), Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(MainActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});
			}
			
			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				//失败的时候
				MainActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(MainActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(MainActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});				
			}
		});
		
	}
}
