package com.example.tabFragment;

import java.io.IOException;

import com.example.appearanceactivity.R;
import com.example.servelet.Servelet;
import com.example.widget.view.getImage;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Me_fragment extends Fragment {

	View view;
	private TextView tv;
	private com.example.widget.view.getImage image;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {

			view = inflater.inflate(R.layout.me_frament, null);
			tv = (TextView) view.findViewById(R.id.textView1);
			image=(getImage) view.findViewById(R.id.getimage);

		}
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		// �����ͻ���
		OkHttpClient client = Servelet.getOkHttpClient();
		// ���û���¼����Ϣ�����������б�Ǻõ�String������
//		MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("num", num)
//				.addFormDataPart("password", password);
		// ��������
		/*
		 * Request request = new Request.Builder()
		 * .url("http://172.27.0.5:8080/membercenter/api/login")
		 * .post(body.build()) .build();
		 */
		Request request = Servelet.requestuildApi("me")
				//.post(body.build())
				.build();
		// �ͻ��˷���һ������newCall������Ȼ��enqueue()��ȥ���У����Callback()���ͻ����ӵĳɹ�������Ϣ
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
				
				Toast.makeText(getActivity(), "����", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
