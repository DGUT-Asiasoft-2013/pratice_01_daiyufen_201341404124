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

public class Life_fragment extends Fragment {

	private ListView life_listv;
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			
			view=inflater.inflate(R.layout.life_frament, null);
			life_listv=(ListView) view.findViewById(R.id.life_listView);
			life_listv.setAdapter(new LifeAdapter());
		}
		return view;
	}
	
	class LifeAdapter extends BaseAdapter
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
			View lifeview;
			if (convertView==null) {
			
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				lifeview=inflater.inflate(android.R.layout.simple_list_item_1, null);
				
			}
			else {
				lifeview=convertView;
			}
			TextView tView;
			tView=(TextView) lifeview.findViewById(android.R.id.text1);
			tView.setText("这是第"+position+"行");
			return lifeview;
		}
		
	}
}
