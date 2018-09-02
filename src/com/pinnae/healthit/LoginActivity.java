package com.pinnae.healthit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
  EditText usernamee,password;
  Button register, login;
  String uservalue,passwordvalue;
  DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle(R.string.login);
        db = new  DatabaseHelper(this);
        register=(Button) findViewById(R.id.viewbtn);
        login=(Button) findViewById(R.id.Button01);
        usernamee=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.pass);

        
        register.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
			}});
       login.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (usernamee.getText().toString().equals("")||password.getText().toString().equals("")){
        		Toast.makeText(getApplicationContext(),"Please fill all fields!!", Toast.LENGTH_SHORT).show();
			}
			
			else{
				if(db.check(usernamee.getText().toString(),password.getText().toString())==true){
					Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(LoginActivity.this,HomeActivity2.class));
					finish();
					
				}else{
					showmessage("Error", "Wrong Username or Password");
				}
				
				}
		}});
        }
    public void showmessage(String title, String message){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
    }
      
    
}
