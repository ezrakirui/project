package com.pinnae.healthit;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends HomeActivity2 {
	  DatabaseHelper db;
	  EditText ip, name,resd,diagn,weight,height,age,gender, wardNo, bedNo;
	  Button registerbttn;
	  @SuppressLint("NewApi")
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        LayoutInflater inflater= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View contentView = inflater.inflate(R.layout.home_activity,null);
	        drawerLayout.addView(contentView, 0);
	        db = new  DatabaseHelper(this );
	        ip =(EditText)findViewById(R.id.ip);
	        name=(EditText)findViewById(R.id.pname);
	        age=(EditText)findViewById(R.id.serach);
	        gender=(EditText)findViewById(R.id.editText2);
	        resd=(EditText)findViewById(R.id.resid);
	        diagn=(EditText)findViewById(R.id.notes);
	        weight=(EditText)findViewById(R.id.weight);
	        height=(EditText)findViewById(R.id.editText1);
	        wardNo=(EditText)findViewById(R.id.editText3);
	        bedNo=(EditText)findViewById(R.id.bed);
	        
	        registerbttn=(Button)findViewById(R.id.registerbttn);
	       
	       
	       registerbttn.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v) {
				
				  if (ip.getText().toString().equals("")||name.getText().toString().equals("")||resd.getText().toString().equals("")
				      ||age.getText().toString().equals("")||gender.getText().toString().equals("")||
				      diagn.getText().toString().equals("")||weight.getText().toString().equals("")||
				      height.getText().toString().equals("")||wardNo.getText().toString().equals("")||
				      bedNo.getText().toString().equals("")){
				       Toast.makeText(getApplicationContext(),"All fields are Required!!", Toast.LENGTH_SHORT).show();
				  		}
				     else if(db.checkIp(ip.getText().toString())==true){
				    	 showmessage("Warning!","A patient with this Ip exists");
				    	 //Toast.makeText(HomeActivity.this, "",Toast.LENGTH_LONG).show();
				      		}
				    	 // if(compare(ip.getText().toString(),cpassword.getText().toString())==true){
				      else if(db.insertPatient((Integer.parseInt(ip.getText().toString())),name.getText().toString(),(Integer.parseInt(age.getText().toString())),
				        		gender.getText().toString(),resd.getText().toString(),(Integer.parseInt(wardNo.getText().toString())),(Integer.parseInt(bedNo.getText().toString())),
				        		diagn.getText().toString(),weight.getText().toString(),height.getText().toString())==true){
				    	  		Toast.makeText(HomeActivity.this, "Registered",Toast.LENGTH_LONG).show();
				   
				        						}
				        	else{
				        		Toast.makeText(HomeActivity.this, "Data not inserted",Toast.LENGTH_LONG).show();
				        						};
				        
				     
				    	  
				      };
				     });
	       }
	  public void showmessage(String title, String message){
      	AlertDialog.Builder builder = new AlertDialog.Builder(this);
      	builder.setCancelable(true);
      	builder.setTitle(title);
      	builder.setMessage(message);
      	builder.show();
      };}
	

