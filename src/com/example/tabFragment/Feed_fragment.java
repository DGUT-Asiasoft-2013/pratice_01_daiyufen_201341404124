package com.example.tabFragment;

import java.util.Random;

import com.example.appearanceactivity.FeedListViewActivity;
import com.example.appearanceactivity.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Feed_fragment extends Fragment {
	
	private ListView feed_listv;
	View view;
	String[] ab;
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
			//创建随机数对象
			Random random=new Random();
			ab=new String[10+random.nextInt()%20];
			for (int i = 0; i < ab.length; i++) {
				ab[i]="第"+random.nextInt()+"行";             //为ab数组添加随机数
			}
			//添加监听器
			feed_listv.setAdapter(new FeedAdapter());
		}
		return view;
	}
	
	public void OnItemSelected(int position) 
	 {
		String content=ab[position];     //获得content内容
		Intent intent=new Intent(getActivity(), FeedListViewActivity.class);
		
		//把数据传入intent中去
		intent.putExtra("cont", content);
		startActivity(intent);
	}

	/*
	 * 为listview设置监听器
	 */
	class FeedAdapter extends BaseAdapter
	{

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
			View feedview;
			//String[] ab=new String[] {"A","B","C","D","E","F","G","HI","JK","L","M","N"
				//	,"O","P","Q","R","S","T","U","V","W","X","YZ"};
			if (convertView==null) {
			
				//获得inflater对象，为下面的feedview获得layout做准备
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				//为feedview设置布局
				feedview=inflater.inflate(android.R.layout.simple_list_item_1, null);
				
			}
			else {
				feedview=convertView;
			}
			TextView tView;
			tView=(TextView) feedview.findViewById(android.R.id.text1);
			/*for (int i = 0; i < position; i++) {
				tView.setText(ab[position]);
				
			}*/
			
			tView.setText(ab[position]);
			return feedview;
	
		}
		
	}
}
