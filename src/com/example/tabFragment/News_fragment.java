package com.example.tabFragment;

import com.example.appearanceactivity.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class News_fragment extends Fragment {

	
	View view;
	private ListView newslistview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			
			view=inflater.inflate(R.layout.news_frament, null);
			newslistview=(ListView) view.findViewById(R.id.news_listView);
			newslistview.setAdapter(new NewsAdapter());
		}
		return view;
	}
	
	class NewsAdapter extends BaseAdapter
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
			View newsview;
			if (convertView==null) {
			
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				newsview=inflater.inflate(android.R.layout.simple_list_item_1, null);
				
			}
			else {
				newsview=convertView;
			}
			TextView tView;
			tView=(TextView) newsview.findViewById(android.R.id.text1);
			tView.setText("这是第"+position+"行");
			return newsview;
		}
		
	}
}
