package com.example.tabFragment;

import java.io.IOException;

import com.example.appearanceactivity.AppActivity;
import com.example.appearanceactivity.LoginActivity;
import com.example.appearanceactivity.R;
import com.example.servelet.Servelet;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Me_fragment extends Fragment {

	View view;
	private TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {

			view = inflater.inflate(R.layout.me_frament, null);
			tv = (TextView) view.findViewById(R.id.textView1);

		}
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		// 创建客户端
		OkHttpClient client = Servelet.getOkHttpClient();
		// 把用户登录的信息传给服务器中标记好的String类型中
//		MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("num", num)
//				.addFormDataPart("password", password);
		// 创建请求
		/*
		 * Request request = new Request.Builder()
		 * .url("http://172.27.0.5:8080/membercenter/api/login")
		 * .post(body.build()) .build();
		 */
		Request request = Servelet.requestBuildApi("me")
				//.post(body.build())
				.build();
		// 客户端发送一个请求newCall（），然后enqueue()进去对列，最后Callback()发送回连接的成功与否的信息
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				final User user;
				ObjectMapper objectMapper=new ObjectMapper();
				user = objectMapper.readValue(arg1.body().string(), User.class);
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						tv.setText(user.getName()+","+user.getAccount());
					}
				});
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				
				Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
