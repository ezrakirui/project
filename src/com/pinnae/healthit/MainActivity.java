package com.pinnae.healthit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
  EditText username,password,cpassword,fname,lname,design,wardNum,phone_num;
  Button register,view,reset;
  DatabaseHelper db;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_main2);
        db = new  DatabaseHelper(this);
        username=(EditText) findViewById(R.id.serach);
        password=(EditText) findViewById(R.id.pwd);
        cpassword=(EditText)findViewById(R.id.cpass);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        design=(EditText)findViewById(R.id.design);
        wardNum=(EditText)findViewById(R.id.ward);
        phone_num=(EditText)findViewById(R.id.phone);
        register=(Button) findViewById(R.id.regbtn2);
        view=(Button)findViewById(R.id.viewbtn1);
        reset=(Button)findViewById(R.id.reset);
        addData();
        viewall();
        setEmpty();
        }
      	public void setEmpty(){
      		reset.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					username.setText("");password.setText("");cpassword.setText("");fname.setText("");
					lname.setText("");design.setText("");wardNum.setText("");phone_num.setText("");
				}
			});
      	}
		public void addData(){
        register.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        	if (username.getText().toString().equals("")||password.getText().equals("")||cpassword.getText().equals("")
        			||fname.getText().toString().equals("")||lname.getText().toString().equals("")||
        			design.getText().toString().equals("")||wardNum.getText().toString().equals("")||
        			phone_num.getText().toString().equals("")){
        			Toast.makeText(getApplicationContext(),"All fields are Required!!", Toast.LENGTH_SHORT).show();
        							}
        	
        	else if(db.checkUser(username.getText().toString())==true){
        			showmessage("Error", "Username Already exists, choose another username");
        										}
        	
        			else if(compare(password.getText().toString(),cpassword.getText().toString())==true){
        				
        				if(db.insert(username.getText().toString(),password.getText().toString())==true){
        						Toast.makeText(MainActivity.this, "Successful registration",Toast.LENGTH_LONG).show();
        						startActivity(new Intent(MainActivity.this,LoginActivity.class));
        						finish();
        						}
        				else{
        						Toast.makeText(MainActivity.this, "Data not inserted",Toast.LENGTH_LONG).show();
        						}
        
        					}else{
        						Toast.makeText(MainActivity.this, "Passwords Do Not Match!!",Toast.LENGTH_LONG).show();
        					}
        		
        }});
    }
			
        public void viewall(){
        
        	view.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					// TODO Auto-generated method stub
					Cursor result=db.getAllData();
					if(result.getCount()==0){
						showmessage("Error", "Nothing Found");
					}
					StringBuffer buffer=new StringBuffer();
					while (result.moveToNext()){
						buffer.append("id:"+result.getString(0)+"\n");
						buffer.append("Name:"+result.getString(1)+"\n");
						buffer.append("Pass:"+result.getString(2)+"\n\n");
					}
					//show all data
					showmessage("Data",buffer.toString());
			}}); 
        }
        public void showmessage(String title, String message){
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setCancelable(true);
        	builder.setTitle(title);
        	builder.setMessage(message);
        	builder.show();
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
    }
    public boolean compare(String pass, String cpass){
    	Boolean wrong=false;
    	if(! pass.equals(cpass)){
			wrong=false;
    	}
    	else{
    		wrong=true;
    	}
    	return wrong;
    }
    
}
