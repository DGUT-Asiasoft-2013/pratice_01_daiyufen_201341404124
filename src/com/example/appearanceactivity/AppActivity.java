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

	Feed_fragment feed_fragment= new Feed_fragment(); // feed����
	News_fragment news_fragment=new News_fragment(); // news����
	Life_fragment life_fragment=new Life_fragment(); // life����
	Me_fragment me_fragment=new Me_fragment(); // me����

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
	 * ���õ�һ��ҳ��ΪĬ��ҳ��
	 */
	@Override
	protected void onResume() {
		super.onResume();

		//���ó�ʼ����Ϊ��һ������
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
		//���漴Ϊ��ҳ��Ž�framelayout����,һ��Ҫ��ס�������fragment�����Ҫ��.commit()������ʾ
		getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
	}

}
