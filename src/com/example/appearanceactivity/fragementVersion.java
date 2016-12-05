package com.example.appearanceactivity;

import android.app.Fragment;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragementVersion extends Fragment {
	private TextView tv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView=inflater.inflate(R.layout.fragment_version_main, container);
		tv=(TextView) rootView.findViewById(R.id.tv1);
		
		//获得管理对象
		PackageManager packageManager=getActivity().getPackageManager();
		try {
			//PackageInfo对象
			PackageInfo packageInfo=packageManager.getPackageInfo(getActivity().getPackageName(), 0);
			tv.setText(packageInfo.packageName+" "+packageInfo.versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			tv.setText("this packge is errors");
		}
		return rootView;
	}

}
