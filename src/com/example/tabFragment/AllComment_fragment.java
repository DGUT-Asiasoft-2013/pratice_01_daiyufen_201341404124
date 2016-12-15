package com.example.tabFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.appearanceactivity.R;
import com.example.servelet.Servelet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class AllComment_fragment extends Fragment {

	
	View view;
	private ListView newslistview;
	int page;
	NewsAdapter adapter = new NewsAdapter();
	
	List<Comment> list=new ArrayList<Comment>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view==null) {
			
			view=inflater.inflate(R.layout.allcomment_frament, null);
			newslistview=(ListView) view.findViewById(R.id.news_listView);
			newslistview.setAdapter(adapter);
		}
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		LoadComment();          //下载评论
	}
	
	public void LoadComment() {
		//建立请求
		Request request=Servelet.requestuildApi("comments")
				.get()
				.build();
		Servelet.getOkHttpClient().newCall(request)
		.enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				
				try {
					String string=arg1.body().string();
					final PageComment<Comment> c;
					ObjectMapper objectMapper=new ObjectMapper();
					//把解析下来的数据给c
					c=objectMapper.readValue(string, new TypeReference<PageComment<Comment>>() {
					});
					
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							page=c.getNumber();           //把页数发给page
							list=c.getContent();         //把内容发给comment
							adapter.notifyDataSetInvalidated();
						}
					});
					
				} catch (final Exception e) {
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							new AlertDialog.Builder(getActivity())
							.setTitle("收取评论错误1")
							.setMessage(e.toString())
							.setNegativeButton("ok", null)
							.show();
						}
					});
				}
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				onFailure(arg0, arg1);
			}
			
		});
	
	}

      public void onFailure(Call arg0, final Exception arg1)
     {
	
    	  getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				new AlertDialog.Builder(getActivity())
				.setTitle("收取评论错误2")
				.setMessage(arg1.toString())
				.setNegativeButton("ok", null)
				.show();
			}
		});
      }

	class NewsAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return list==null?0:list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
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
				newsview=inflater.inflate(R.layout.all_comment_of_author, null);
				
			}
			else {
				newsview=convertView;
			}
			//获得某一行的comment内容
			Comment comment=list.get(position);
			TextView commenter_commentor_id=(TextView) newsview.findViewById(R.id.commenter_commentor_id);
			TextView commentor_edit_time=(TextView) newsview.findViewById(R.id.commentor_edit_time);
			TextView comment_content=(TextView) newsview.findViewById(R.id.comment_content);
			
			commenter_commentor_id.setText(comment.getId());
			String dString=DateFormat.format("yyyy-MM-dd hh:mm", comment.getCreateDate()).toString();
			commentor_edit_time.setText(dString);
			comment_content.setText(comment.getContent());
			
			return newsview;
		}
		
	}
}
