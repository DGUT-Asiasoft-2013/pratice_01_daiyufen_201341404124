package com.example.widget.view;

import java.io.IOException;

import com.example.servelet.Servelet;
import com.example.tabFragment.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getImage extends View {

	public getImage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public getImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public getImage(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	//创建画笔
	Paint paint;
	//创建画布
	Bitmap bitmap;
	//创建半径
	float radius;
	Handler handler=new Handler();
	
	public void setBitmap(Bitmap bitmap) {
		if (bitmap==null) {
			return;
			
		}
		paint=new Paint();
		//设置阴影
		paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.REPEAT));
		radius=Math.min(bitmap.getHeight(), bitmap.getWidth())/2;
		
		invalidate();          //用来刷新View的，必须是在UI线程中进行工作
		
	}
	
	
	
	//下载图片
	public void load(User user) {
		OkHttpClient client=Servelet.getOkHttpClient();
		
		Request request=new Request
				.Builder()
				.url("http://172.27.0.37:8080/membercenter/api/"+user.getAvatar())
				.method("get", null)
				.build();
		client.newCall(request).enqueue(new Callback() {
			
			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				//final Boolean succed=new ObjectMapper().readValue(arg1.body().string(), Boolean.class);
				try {
					
					
					byte[] data=arg1.body().bytes();
					//获得画布
					final Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
					//开始画图，因为这里是后台，所以需要使用handle
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							setBitmap(bitmap);
						}
					});
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
			
			@Override
			public void onFailure(Call arg0, IOException arg1) {
				
			}
		});
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (paint!=null) {
			//如果画笔不为空，则画图
			canvas.drawCircle(getWidth()/2, getHeight()/2, radius, paint);
			
		}
	}
	

}
