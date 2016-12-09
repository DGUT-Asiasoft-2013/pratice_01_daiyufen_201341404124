package com.example.singleTextInputFragment;

import java.io.ByteArrayOutputStream;

import com.example.appearanceactivity.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleImageInputFragment extends SingleAbstractSourse {
	private static final int TAKEPHOTO_CAMERA = 1;
	private static final int PHOTO = 2;
	private ImageView imageView;
	private TextView tv;
	private TextView hintTV;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView=inflater.inflate(R.layout.fragment_image_main, null);
		tv=(TextView) rootView.findViewById(R.id.image_textView);
		hintTV=(TextView) rootView.findViewById(R.id.hint_singleTextView);
		imageView=(ImageView) rootView.findViewById(R.id.single_image);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				onImageViewClicked();

				
			}
		});
		return rootView;
	}
	byte[] pngData;
	public byte[] getPngData() {
		return pngData;
	}
	
	void onImageViewClicked(){
		String[] options = {
				"≈ƒ’’",
				"œ‡≤·"
		};

		new AlertDialog.Builder(getActivity())
		.setTitle("—°‘Ò’’∆¨")
		.setItems(options, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					takePhoto();
					break;

				case 1:
					pickFromAlbum();
					break;

				default:
					break;
				}
			}
		})
		.setNegativeButton("»°œ˚", null)
		.show();
	}

	void takePhoto(){
		Intent itnt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(itnt, TAKEPHOTO_CAMERA);
	}

	void pickFromAlbum(){
		Intent itnt = new Intent(Intent.ACTION_GET_CONTENT);
		itnt.setType("image/*");
		startActivityForResult(itnt, PHOTO);
	}
	
	 void saveBitmap(Bitmap bitmap) {

		 ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		 bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
		 pngData=byteArrayOutputStream.toByteArray();
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_CANCELED) return;

		if(requestCode == TAKEPHOTO_CAMERA){
			//≈ƒ’’

			Bitmap bmp = (Bitmap)data.getExtras().get("data");
			imageView.setImageBitmap(bmp);
		}else if(requestCode == PHOTO){
			//œ‡∆¨
			try {
				Bitmap bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
				imageView.setImageBitmap(bmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public void pickPhoto() {
		Intent in=new Intent(Intent.ACTION_GET_CONTENT);
		in.setType("image/*");
		startActivityForResult(in, TAKEPHOTO_CAMERA);
	}
	
	public void takephoto() {
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, PHOTO);
	}

	
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (requestCode==Activity.RESULT_CANCELED) {
//			return;
//			
//		}
//		if (requestCode==2) {
//			//ªÒµ√Õº∆¨
//			Bitmap bitmap=(Bitmap) data.getExtras().get("data");
//			//…Ë÷√Õº∆¨
//			imageView.setImageBitmap(bitmap);
//			
//		}
//		else if (requestCode==1) {
//			try {
//				Bitmap bit=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
//						imageView.setImageBitmap(bit);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			
//		}
//		
//	}
//	
	
	public void setinputSingleTextLabel(String label) {
		tv.setText(label);
		
	}
	
	public void setinputSingleTextHintLabel(String hintlabel) {
		hintTV.setHint(hintlabel);
		
	}
	
}