package com.example.singleTextInputFragment;

import com.example.appearanceactivity.Add_Click_Activity;
import com.example.appearanceactivity.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class TabFragment extends Fragment {

	View feed_tabs,note_tabs,add_btn,life_tabs,me_tabs;
	
	View[] ta;
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.tab_fragment_main, null);
		
		feed_tabs=view.findViewById(R.id.tab_feed);
		note_tabs=view.findViewById(R.id.tab_note);
		add_btn=view.findViewById(R.id.add_btn);
		add_btn.setOnClickListener(new AddbuttonClickListner());  //设置监听器
		life_tabs=view.findViewById(R.id.tab_life);
		me_tabs=view.findViewById(R.id.tab_me);
		
	
		ta=new View[] {feed_tabs,note_tabs,life_tabs,me_tabs};
		
		
		
		//使用foreach方法循环
		for (final View tabs : ta) {
			tabs.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					onTabClickListener(tabs);
				}
			});
		}
		return view;
	}
	
	/*
	 * 为+按钮添加监听器
	 */
	class AddbuttonClickListner implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Intent addintent=new Intent(getActivity(), Add_Click_Activity.class);
			startActivity(addintent);
		}
		
	}
	
	public void setSelecteditem(int index) {
		if (index>0 && index<=ta.length) {
			
			onTabClickListener(ta[index]);
		}
		
	}
	
	/*
	 * 创建tab的接口
	 */
	public static interface TabSelectClickListener
	{
		void OnTabSelected(int index);
	}
	
	//创建监听器对象
	TabSelectClickListener tabSelectClickListener;
	
	/*
	 * 创建监听器方法
	 */
	public void setOnTabSelectClickListener(TabSelectClickListener ontabSelectClickListener) {
		this.tabSelectClickListener=ontabSelectClickListener;
		
	}
	
	/*
	 * 为tabs设置监听器的方法
	 */
	protected void onTabClickListener(View tabs) {
		int selectIndex=-1;
		for (int i = 0; i < ta.length; i++) {
			View otabs=ta[i];          //将
			if (otabs==tabs) {
				otabs.setSelected(true);
				
				selectIndex=i;
				
			}
			else {
				otabs.setSelected(false);
			}
		}
		
		if (tabSelectClickListener!=null && selectIndex>=0) {
			tabSelectClickListener.OnTabSelected(selectIndex);
			
		}
	}
}
