package com.example.tabFragment;

import com.example.appearanceactivity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Feed_fragment extends Fragment {
	
	private ListView feed_listv;
	View view;
	//FeedAdapter adapter=new FeedAdapter();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			
			view=inflater.inflate(R.layout.feed_frament, null);
			feed_listv=(ListView) view.findViewById(R.id.feed_listView);
			
			feed_listv.setAdapter(new FeedAdapter());
		}
		return view;
	}

	class FeedAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return 25;
		}

		@Override
		public Object getItem(int position) {
			return " ";
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View feedview;
			if (convertView==null) {
			
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				feedview=inflater.inflate(android.R.layout.simple_list_item_1, null);
				
			}
			else {
				feedview=convertView;
			}
			TextView tView;
			tView=(TextView) feedview.findViewById(android.R.id.text1);
			tView.setText("this is"+position);
			return feedview;
		}
		
	}
}
