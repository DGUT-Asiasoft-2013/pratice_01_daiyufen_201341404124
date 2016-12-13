package com.example.tabFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.appearanceactivity.FeedListViewActivity;
import com.example.appearanceactivity.R;
import com.example.servelet.Servelet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	View loadMorebtn;        //底端按钮布局
	TextView load_tv;
	List<Article> ab= new ArrayList<Article>();
	protected int page;

	FeedAdapter feedAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {

			view = inflater.inflate(R.layout.feed_frament, null);
			loadMorebtn=inflater.inflate(R.layout.chart_buttom_page, null);
			load_tv=(TextView) loadMorebtn.findViewById(R.id.tv);
			feed_listv = (ListView) view.findViewById(R.id.feed_listView);
			//ab=new ArrayList<Article>();
			feed_listv.addFooterView(loadMorebtn);                //把这个btn放在底端必须在setAdapter之前完成

			feed_listv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					OnItemSelected(position);
				}
			});
			
			loadMorebtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AgainLoad();
				}
			});
			feedAdapter=new FeedAdapter();
			// 添加监听器
			feed_listv.setAdapter(feedAdapter);
		}
		return view;
	}

	protected void AgainLoad() {
		//点击后就设置不可再按了
		loadMorebtn.setEnabled(false);
		load_tv.setText("加载中");
		 //获得客户端
		OkHttpClient client=Servelet.getOkHttpClient();
		//获得请求
		Request request=Servelet.requestuildApi("feeds/"+(page+1)).get().build();
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						loadMorebtn.setEnabled(true);
						load_tv.setText("加载更多");
					}
				});
				
				try {
					ObjectMapper objectMapper=new ObjectMapper();
					final Page<Article> page=objectMapper.readValue(arg1.body().string(), new TypeReference<Page<Article>>() {
					});
					if (page.getNumber()>Feed_fragment.this.page) {
						if (ab==null) {
							//判断列表是否为空，如果为空，则把解析的数据赋值给列表
							ab=page.getContent();
							
						}
						else {
							ab.addAll(page.getContent());
						}
						
						
						getActivity().runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Feed_fragment.this.page=page.getNumber();     //把当前的页数赋值给int类型的page
								//刷新adapter
								feedAdapter.notifyDataSetInvalidated();
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {

				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						loadMorebtn.setEnabled(true);
						load_tv.setText("加载更多");
					}
				});
				
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		load();

	}

	public void load() {
		          //获得客户端
				OkHttpClient client=Servelet.getOkHttpClient();
				//获得请求
				Request request=Servelet.requestuildApi("feeds").get().build();
				
				//发起请求
				client.newCall(request).enqueue(new Callback() {
					
					@Override
					public void onResponse(Call arg0, Response arg1) throws IOException {
//						final Article article;
						try {
							final Page<Article> page;
							ObjectMapper objectMapper=new ObjectMapper();
							//把解析出来的用户内容放进article中
							page=objectMapper.readValue(arg1.body().string(), new TypeReference<Page<Article>>() {
							});
							
							
							getActivity().runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									//把解析下来的页数传给Feed_Fragment
									Feed_fragment.this.page=page.getNumber();
									//把解析下来的数据传给list
									Feed_fragment.this.ab=page.getContent();
									//刷新
									feedAdapter.notifyDataSetInvalidated();
								}
							});
						} catch (final Exception e) {
							getActivity().runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									
									new AlertDialog.Builder(getActivity())
									.setTitle("失败111")
									.setMessage(e.toString())
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
	
	public void onFailure(Call arg0, Exception arg1)  {
		new AlertDialog.Builder(getActivity())
		.setTitle("失败")
		.setMessage(arg1.toString())
		.show();
	}

	public void OnItemSelected(int position) {
		Article article = ab.get(position);
		String content = article.getAuthorName()+":"+article.getText(); // 获得content内容
		Intent intent = new Intent(getActivity(), FeedListViewActivity.class);

		// 把数据传入intent中去
		intent.putExtra("cont", content);
		startActivity(intent);
	}

	/*
	 * 为listview设置监听器
	 */
	class FeedAdapter extends BaseAdapter {

		ViewHolde viewHolde;

		@Override
		public int getCount() {
			return ab == null ? 0 : ab.size();
		}

		@Override
		public Object getItem(int position) {
			return ab.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			viewHolde = new ViewHolde(); // 创建ViewHolde对象
			if (convertView == null) {

				// 获得inflater对象，为下面的feedview获得layout做准备
				LayoutInflater inflater = LayoutInflater.from(parent.getContext());
				// 为feedview设置布局
				convertView = inflater.inflate(R.layout.chart_left_page, null);
				// 为用户的组件提供id
				viewHolde.userimage = (ImageView) convertView.findViewById(R.id.left_men);
				viewHolde.userName = (TextView) convertView.findViewById(R.id.left_name);
				viewHolde.userContent = (TextView) convertView.findViewById(R.id.content_textview);
				viewHolde.usertime=(TextView) convertView.findViewById(R.id.time);
				convertView.setTag(viewHolde);

			} else {
				viewHolde = (ViewHolde) convertView.getTag();
			}
			Article article=ab.get(position);
			viewHolde.userimage.setBackgroundResource(R.drawable.women);
			viewHolde.userName.setText(article.getAuthorName());
			viewHolde.userContent.setText(article.getText());
			String time=DateFormat.format("yyyy-MM-dd hh:mm", article.getCreateDate()).toString();
			viewHolde.usertime.setText(time);
			return convertView;

		}

	}

	class ViewHolde {
		ImageView userimage;
		TextView userName;
		TextView userContent;
		TextView usertime;
	}
}
