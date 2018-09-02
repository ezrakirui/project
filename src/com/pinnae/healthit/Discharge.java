package com.pinnae.healthit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Discharge extends HomeActivity2{
	DatabaseHelper db;
	Button Discharge,Search;
	TextView Date,name,age,gender,resid,diagnosis;
	EditText ip;
	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.acitvity_disharge, null);
        drawerLayout.addView(contentView, 0);
        db=new DatabaseHelper(this);
        Discharge = (Button)findViewById(R.id.discharge);
        name=(TextView)findViewById(R.id.namee);
        Date=(TextView)findViewById(R.id.date);
        age=(TextView)findViewById(R.id.age1);
        gender=(TextView)findViewById(R.id.genderr);
        resid=(TextView)findViewById(R.id.residde);
        diagnosis=(TextView)findViewById(R.id.diagg);
        Search=(Button)findViewById(R.id.search);
        ip=(EditText)findViewById(R.id.iptext);
       
       // Date=(TextView)findViewById(R.id.date);
		Discharge.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(ip.getText().toString().equals("")){
					Toast.makeText(Discharge.this, "Please Enter an ip",Toast.LENGTH_SHORT).show();
				}
				
				else{
					Cursor result=db.getAllPatients();
				if(result.getCount()==0){
					showmessage("Error", "No Data Yet");
						}
				String ip_no=(ip.getText().toString());
				if(db.checkPatient(ip_no)==true){
					name.setText("");
					age.setText("");
					gender.setText("");
					resid.setText("");
					diagnosis.setText("");
					Date.setText("");
					db.remove(ip_no);
					Toast.makeText(Discharge.this, "Patient IP No.: "+ip_no+" has been discharged Succesfully",Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Discharge.this, "No such patient",Toast.LENGTH_SHORT).show();
				};
					
					
				
				}
				};
			}
		);
		Search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					String date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date ());
					if(ip.getText().toString().equals("")){
						Toast.makeText(Discharge.this, "Please Enter an ip",Toast.LENGTH_SHORT).show();
					}else{
						//getData();
						String ip_no=(ip.getText().toString());
						if(db.checkPatient(ip_no)==true){
							Cursor result =db.getPatient(ip_no);
							if(result.getCount()==0){
								showmessage("Error", "No Data Yet");
									}
							//StringBuffer buffer=new StringBuffer();
							while (result.moveToNext()){
								name.setText(result.getString(2));
								age.setText(result.getString(3));
								gender.setText(result.getString(4));
								resid.setText(result.getString(5));
								diagnosis.setText(result.getString(8));
								Date.setText(date);
								//calculateBMI();
								break;
							}
							String name=result.getString(2);
							showmessage("Infor", "Patient "+name+",IP: "+ip_no+" is In bed, please check the Patient");
						}else{
							name.setText("");
							age.setText("");
							gender.setText("");
							resid.setText("");
							diagnosis.setText("");
							Date.setText("");
							Toast.makeText(Discharge.this, "No such patient",Toast.LENGTH_SHORT).show();
						};
						//Date.setText(date);
						
						}
				
			}
		});}
		
		public void getData( ) {
			Cursor result=db.getAllPatients();
			if(result.getCount()==0){
				showmessage("Error", "No Data Yet");
					}
			//StringBuffer buffer=new StringBuffer();
			while (result.moveToNext()){
				name.setText(result.getString(2));
				age.setText(result.getString(3));
				gender.setText(result.getString(4));
				resid.setText(result.getString(5));
				diagnosis.setText(result.getString(6));
				break;
		}
		
	};
	 public void showmessage(String title, String message){
     	AlertDialog.Builder builder = new AlertDialog.Builder(this);
     	builder.setCancelable(true);
     	builder.setTitle(title);
     	builder.setMessage(message);
     	builder.show();
     }

}
