package com.pinnae.healthit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.InputStream;
public class ShareActivity extends HomeActivity2 {
	private ImageView img;
	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_share,null);
        drawerLayout.addView(contentView, 0);

		      img=(ImageView)findViewById(R.id.image);
		      Button b1=(Button)findViewById(R.id.button);

		      b1.setOnClickListener(new View.OnClickListener() {
		         @Override
		         public void onClick(View v) {
		            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		            Uri screenshotUri = Uri.parse("android.resource://comexample.sairamkrishna.myapplication/*");

		            try {
		               InputStream stream = getContentResolver().openInputStream(screenshotUri);
		            } catch (FileNotFoundException e) {
		               // TODO Auto-generated catch block
		               e.printStackTrace();
		            }
		               sharingIntent.setType("image/jpeg");
		               sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
		               startActivity(Intent.createChooser(sharingIntent, "Share image using"));
		         }
		      });
		   }
		}
	


