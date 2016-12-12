package com.example.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;

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
	
	public void setBitmap(Bitmap bitmap) {
		paint=new Paint();
		//设置阴影
		paint.setShader(new BitmapShader(bitmap, TileMode.CLAMP, TileMode.REPEAT));
		radius=Math.min(bitmap.getHeight(), bitmap.getWidth())/2;
		invalidate();
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (canvas!=null) {
			canvas.drawCircle(getWidth()/2, getHeight()/2, radius, paint);
			
		}
	}
	
	

}
