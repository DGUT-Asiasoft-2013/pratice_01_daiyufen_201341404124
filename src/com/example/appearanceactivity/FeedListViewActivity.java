package com.example.appearanceactivity;

import com.example.tabFragment.Article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FeedListViewActivity extends Activity {

	private TextView tv;
	private Button comment_btn;
	Article article;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedlistviewactivity);
		tv=(TextView) findViewById(R.id.feed_liatview_textView);
		comment_btn=(Button) findViewById(R.id.comment_btn);
		
		comment_btn.setOnClickListener(new CommentOnClickListener());
		//String text=getIntent().getStringExtra("cont");     //获得传过来的内容
		article=(Article) getIntent().getSerializableExtra("data");
		tv.setText(article.getAuthorName()+":"+article.getText());
	}
	
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
}
