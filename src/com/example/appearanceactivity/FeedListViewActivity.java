package com.example.appearanceactivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.servelet.Servelet;
import com.example.tabFragment.Article;
import com.example.tabFragment.Comment;
import com.example.tabFragment.PageComment;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/*
 * 文章列表点击进来文章详情页面
 */
public class FeedListViewActivity extends Activity {

	private TextView tv;
	private Button comment_btn;
	private Button like_btn;
	Article article;
	private ListView comment_list;        //评论列表
	
	private int page;         //页数
	
	//创建list包括评论
	List<Comment> comment=new ArrayList();
	CommentAdapter commentAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedlistviewactivity);
		tv=(TextView) findViewById(R.id.feed_liatview_textView);
		comment_btn=(Button) findViewById(R.id.comment_btn);
		comment_btn.setOnClickListener(new CommentOnClickListener());
		like_btn=(Button) findViewById(R.id.likes);
		comment_list=(ListView) findViewById(R.id.comment_list);
		
		
		//String text=getIntent().getStringExtra("cont");     //获得传过来的内容
		article=(Article) getIntent().getSerializableExtra("data");
		tv.setText(article.getAuthorName()+":"+article.getText());
		commentAdapter=new CommentAdapter();
		comment_list.setAdapter(commentAdapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadComment();           //下载评论
	}
	
	public void loadComment() {
		//建立请求
		Request request=Servelet.requestuildApi("article/"+article.getId()+"/comment")
				.get()
				.build();
		Servelet.getOkHttpClient().newCall(request)
		.enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				try {
					final PageComment<Comment> pageComment;
					ObjectMapper objectMapper=new ObjectMapper();
					//把解析下来的东西传入pageComment中
					pageComment=objectMapper.readValue(arg1.body().string(),
							new TypeReference<PageComment<Comment>>() {});
					
					FeedListViewActivity.this.runOnUiThread(new Runnable() {
						
						

						@Override
						public void run() {
							//把解析下来的页数传给FeedListViewActivity
							page=pageComment.getNumber();
							comment=pageComment.getContent();
							//刷新
							commentAdapter.notifyDataSetInvalidated();
						}
					});
				}catch (JsonParseException e) {
					e.printStackTrace();
					
				} catch (JsonMappingException e) {
					e.printStackTrace();
					
				}catch (final Exception e) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							
							new AlertDialog.Builder(FeedListViewActivity.this)
							.setTitle("失败ing")
							.setMessage(e.toString())
							.show();
						}
					});
				}
				
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				FeedListViewActivity.this.onFailure(arg0, arg1);
			}
		});
	}
	
	public void onFailure(Call arg0, Exception arg1)  {
		new AlertDialog.Builder(this)
		.setTitle("失败")
		.setMessage(arg1.toString())
		.show();
	}


	/*
	 * 评论的监听器
	 */
	class CommentOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Intent intent=new Intent(FeedListViewActivity.this, CommentTextActivity.class);
			//把article的数据也发送过去
			intent.putExtra("article", article);
			startActivity(intent);
		}
		
	}
	
	/*
	 * 列表的适配器
	 */
	class CommentAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return comment==null?0:comment.size();
		}

		@Override
		public Object getItem(int position) {
			return comment.get(position);
			
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			
			
			
			if (convertView==null) {
				//如果视图为空，则建立视图
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				view=inflater.inflate(R.layout.comment_content_list, null);
				 
			}
			else {
				view=convertView;
			}
			TextView comment_name=(TextView) view.findViewById(R.id.commentor_name);
			TextView comment_time=(TextView) view.findViewById(R.id.commentor_time);
			TextView comment_content=(TextView) view.findViewById(R.id.comment_text);
			//获得某一行的评论
			Comment c=comment.get(position);
			comment_name.setText(c.getCommentor().getName());
			/*comment_name.setText("asaasdddd");
			comment_content.setText("hhhhhhhhhhhhhhhhh");
			comment_time.setText("2016-12-14 16:52");*/
			comment_content.setText(c.getContent());
			String time=DateFormat.format("yyyy-MM-dd hh:mm", article.getCreateDate()).toString();
			comment_time.setText(time);
			return view;
		}
		
	}
}
