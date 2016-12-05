package com.example.singleTextInputFragment;

import com.example.appearanceactivity.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
				
				String[] options={"’’∆¨","≈ƒ’’"};
				new AlertDialog
				.Builder(getActivity())
				.setTitle("—°‘ÒÕº∆¨")
				.setItems(options, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							pickPhoto();
							break;
							
							case 1:
								takephoto();
								break;

						default:
							break;
						}
						
					}

					
				}).setNegativeButton("»°œ˚", null)
				.show();
				
			}
		});
		return rootView;
	}
	
	public void takephoto() {
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1);
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==Activity.RESULT_CANCELED) {
			return;
			
		}
		if (requestCode==1) {
			//ªÒµ√Õº∆¨
			Bitmap bitmap=(Bitmap) data.getExtras().get("data");
			//…Ë÷√Õº∆¨
			imageView.setImageBitmap(bitmap);
			
		}
		else if (requestCode==2) {
			try {
				Bitmap bit=MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
						imageView.setImageBitmap(bit);
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}
		
	}
	public void pickPhoto() {
		Intent in=new Intent(Intent.ACTION_GET_CONTENT);
		
		startActivityForResult(in, 2);
	}
	
	public void setinputSingleTextLabel(String label) {
		tv.setText(label);
		
	}
	
	public void setinputSingleTextHintLabel(String hintlabel) {
		hintTV.setHint(hintlabel);
		
	}
	
}