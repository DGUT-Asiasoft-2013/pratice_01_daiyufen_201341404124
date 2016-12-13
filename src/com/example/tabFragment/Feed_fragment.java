package com.example.tabFragment;

import java.io.IOException;
import java.util.Random;

import com.example.appearanceactivity.FeedListViewActivity;
import com.example.appearanceactivity.R;
import com.example.servelet.Servelet;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Feed_fragment extends Fragment {
	
	private ListView feed_listv;
	View view;
	String[] ab;
	String[] image;     //�û�ͷ��
	String[] title;      //���±���
	String[] text;        //��������
	//FeedAdapter adapter=new FeedAdapter();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			
			view=inflater.inflate(R.layout.feed_frament, null);
			feed_listv=(ListView) view.findViewById(R.id.feed_listView);
			
			feed_listv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					OnItemSelected(position);
				}
			});
			//�������������
			Random random=new Random();
			ab=new String[10+random.nextInt()%20];
			image=new String[10+random.nextInt()%20];
			title=new String[10+random.nextInt()%20];
			text=new String[10+random.nextInt()%20];
			for (int i = 0; i < ab.length; i++) {
				ab[i]="��"+random.nextInt()+"��";             //Ϊab������������
			}
			//��Ӽ�����
			feed_listv.setAdapter(new FeedAdapter());
		}
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//��ÿͻ���
		OkHttpClient client=Servelet.getOkHttpClient();
		//�������
		Request request=Servelet.requestuildApi("feeds").build();
		
		//��������
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				final Article article;
				ObjectMapper objectMapper=new ObjectMapper();
				//�ѽ����������û����ݷŽ�article��
				article=objectMapper.readValue(arg1.body().string(), Article.class);
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						for (int i = 0; i < image.length; i++) {
							image[i]=article.getAuthorImage();
						}
						
						for (int j = 0; j < title.length; j++) {
							title[j]=article.getTitle();
						}
						for (int n = 0; n < text.length; n++) {
							text[n]=article.getText();
							
						}
					}
				});
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				onFailure(arg0, arg1);
			}
		});
		
		
	}
	
	public void onFailure(Call arg0, Exception arg1)  {
		new AlertDialog.Builder(getActivity())
		.setTitle("ʧ��")
		.setMessage(arg1.toString())
		.show();
	}
	
	public void OnItemSelected(int position) 
	 {
		String content=ab[position];     //���content����
		Intent intent=new Intent(getActivity(), FeedListViewActivity.class);
		
		//�����ݴ���intent��ȥ
		intent.putExtra("cont", content);
		startActivity(intent);
	}

	/*
	 * Ϊlistview���ü�����
	 */
	class FeedAdapter extends BaseAdapter
	{

		ViewHolde viewHolde;
		@Override
		public int getCount() {
			return ab==null? 0:ab.length;
		}

		@Override
		public Object getItem(int position) {
			return ab[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			viewHolde=new ViewHolde();             //����ViewHolde����
			//String[] ab=new String[] {"A","B","C","D","E","F","G","HI","JK","L","M","N"
				//	,"O","P","Q","R","S","T","U","V","W","X","YZ"};
			if (convertView==null) {
			
				//���inflater����Ϊ�����feedview���layout��׼��
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				//Ϊfeedview���ò���
				convertView=inflater.inflate(R.layout.chart_left_page, null);
				//Ϊ�û�������ṩid
				viewHolde.userimage=(ImageView) convertView.findViewById(R.id.left_men);
				viewHolde.userName=(TextView) convertView.findViewById(R.id.left_name);
				viewHolde.userContent=(TextView) convertView.findViewById(R.id.content_textview);
				convertView.setTag(viewHolde);
				
			}
			else {
				viewHolde=(ViewHolde) convertView.getTag();
			}
			viewHolde.userimage.setBackgroundResource(R.drawable.women);
			viewHolde.userName.setText("С��");
			viewHolde.userContent.setText("�����������");
			return convertView;
	
		}
		
	}
	
	class ViewHolde
	{
		ImageView userimage;
		TextView userName;
		TextView userContent;
	}
}
