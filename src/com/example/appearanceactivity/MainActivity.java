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
		/*//������ʱ��Ȼ��ת����½����
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(MainActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);*/
		
		/*
		 *����Ϊ������·����
		 */
		//�����ͻ���
		OkHttpClient client=new OkHttpClient();
		//��������
		String string = "http://172.27.0.5:8080/menbercenter/hello";
		Request request=new Request.Builder().url(string).method("get", null).build();
		//�ͻ��˷���һ������newCall������Ȼ��enqueue()��ȥ���У����Callback()���ͻ����ӵĳɹ�������Ϣ
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, final Response arg1) throws IOException {
				//�ɹ���ʱ�򷵻�����
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
				//ʧ�ܵ�ʱ��
				MainActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast.makeText(MainActivity.this, "����ʧ��", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent(MainActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}
				});				
			}
		});
		
	}
}
