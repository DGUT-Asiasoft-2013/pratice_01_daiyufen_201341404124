package com.example.appearanceactivity;

import com.example.singleTextInputFragment.TabFragment;
import com.example.singleTextInputFragment.TabFragment.TabSelectClickListener;
import com.example.tabFragment.Feed_fragment;
import com.example.tabFragment.Life_fragment;
import com.example.tabFragment.Me_fragment;
import com.example.tabFragment.News_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class AppActivity extends Activity {

	Feed_fragment feed_fragment= new Feed_fragment(); // feed界面
	News_fragment news_fragment=new News_fragment(); // news界面
	Life_fragment life_fragment=new Life_fragment(); // life界面
	Me_fragment me_fragment=new Me_fragment(); // me界面

	TabFragment tabFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_main);
		
		tabFragment=(TabFragment) getFragmentManager().findFragmentById(R.id.tab_fragment);
		
		tabFragment.setOnTabSelectClickListener(new TabSelectClickListener() {
			
			@Override
			public void OnTabSelected(int index) {
				ChangeContentFrament(index);
				
			}
		});
	}

	/*
	 * 设置第一个页面为默认页面
	 */
	@Override
	protected void onResume() {
		super.onResume();

		//设置初始界面为第一个界面
		tabFragment.setSelecteditem(0);
	}

	public void ChangeContentFrament(int index) {
		Fragment fragment = null;
		switch (index) {
		case 0:
			fragment = feed_fragment;

			break;
		case 1:
			fragment = news_fragment;
			break;

		case 2:
			fragment = life_fragment;
			break;

		case 3:
			fragment = me_fragment;
			break;

		default:
			break;
		}
		
		if (fragment==null) {
			return;
			
		}
		//下面即为把页面放进framelayout里面,一定要记住，事务和fragment最后都需要加.commit()才能显示
		getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
	}

}
