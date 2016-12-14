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
 * �����б���������������ҳ��
 */
public class FeedListViewActivity extends Activity {

	private TextView tv;
	private Button comment_btn;
	private Button like_btn;
	Article article;
	private ListView comment_list;        //�����б�
	
	private int page;         //ҳ��
	
	//����list��������
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
		
		
		//String text=getIntent().getStringExtra("cont");     //��ô�����������
		article=(Article) getIntent().getSerializableExtra("data");
		tv.setText(article.getAuthorName()+":"+article.getText());
		commentAdapter=new CommentAdapter();
		comment_list.setAdapter(commentAdapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadComment();           //��������
	}
	
	public void loadComment() {
		//��������
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
					//�ѽ��������Ķ�������pageComment��
					pageComment=objectMapper.readValue(arg1.body().string(),
							new TypeReference<PageComment<Comment>>() {});
					
					FeedListViewActivity.this.runOnUiThread(new Runnable() {
						
						

						@Override
						public void run() {
							//�ѽ���������ҳ������FeedListViewActivity
							page=pageComment.getNumber();
							comment=pageComment.getContent();
							//ˢ��
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
							.setTitle("ʧ��ing")
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
		.setTitle("ʧ��")
		.setMessage(arg1.toString())
		.show();
	}


	/*
	 * ���۵ļ�����
	 */
	class CommentOnClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Intent intent=new Intent(FeedListViewActivity.this, CommentTextActivity.class);
			//��article������Ҳ���͹�ȥ
			intent.putExtra("article", article);
			startActivity(intent);
		}
		
	}
	
	/*
	 * �б��������
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
				//�����ͼΪ�գ�������ͼ
				LayoutInflater inflater=LayoutInflater.from(parent.getContext());
				view=inflater.inflate(R.layout.comment_content_list, null);
				 
			}
			else {
				view=convertView;
			}
			TextView comment_name=(TextView) view.findViewById(R.id.commentor_name);
			TextView comment_time=(TextView) view.findViewById(R.id.commentor_time);
			TextView comment_content=(TextView) view.findViewById(R.id.comment_text);
			//���ĳһ�е�����
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
