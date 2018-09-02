package com.pinnae.healthit;

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

public class PatientActivity extends HomeActivity2  {
	private EditText value;
	private TextView name,age,gender,resid,diagnosis,weight,height,bmi_result,drug,time,code;
	private Button Search, view;
	DatabaseHelper db;
	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_patient,null);
        drawerLayout.addView(contentView, 0);
        db=new DatabaseHelper(this);
        name=(TextView)findViewById(R.id.textView10);
        age=(TextView)findViewById(R.id.textView11);
        gender=(TextView)findViewById(R.id.gend);
        resid=(TextView)findViewById(R.id.residence);
        diagnosis=(TextView)findViewById(R.id.diagnosis);
        weight=(TextView)findViewById(R.id.weightt);
        height=(TextView)findViewById(R.id.heightt);
        bmi_result=(TextView)findViewById(R.id.bmi);
        drug=(TextView)findViewById(R.id.drug);
        code=(TextView)findViewById(R.id.code);
        
        time=(TextView)findViewById(R.id.tyme);
       
        Search=(Button)findViewById(R.id.searchbtn1);
        view=(Button)findViewById(R.id.button1);
        value=(EditText)findViewById(R.id.serach);
      
        viewall();
  view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Cursor result=db.getAllPatients();
				if(result.getCount()==0){
					showmessage("Error", "Nothing Found");
						}
				else{
				StringBuffer buffer=new StringBuffer();
				while (result.moveToNext()){
					buffer.append("id:		"+result.getString(0)+"\n");
					buffer.append("IP No:	"+result.getString(1)+"\n");
					buffer.append("Age:		"+result.getString(3)+"\n\n");
					buffer.append("Gender:	"+result.getString(4)+"\n\n");
					buffer.append("Place of Residence: "+result.getString(5)+"\n\n");
					buffer.append("Ward No:		"+result.getString(6)+"\n\n");
					buffer.append("Bed No:		"+result.getString(7)+"\n\n");
					buffer.append("Diagnosis:	"+result.getString(8)+"\n\n");
					buffer.append("Referred:	"+result.getString(9)+"\n\n");
					buffer.append("Reason for Referral: "+result.getString(10)+"\n\n");   
					}
			showmessage("Data",buffer.toString());
			}
			}
		});
	}
        
	public void viewall(){
            
        	Search.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(value.getText().toString().equals("")){
						Toast.makeText(PatientActivity.this, "Please Enter an Patient No. to Search",Toast.LENGTH_SHORT).show();
					}else{
						
						//calculateBMI();
						String ip_no=(value.getText().toString());
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
								height.setText(result.getString(10));
								weight.setText(result.getString(9));
								calculateBMI();
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
							height.setText("");
							weight.setText("");
							bmi_result.setText("");
							//Date.setText("");
							showmessage("Infor", "This patient was discharged, IP: "+ip_no);
						}
					}
				
					
					/*Cursor result=db.getAllPatients();
					if(result.getCount()==0){
						showmessage("Error", "Nothing Found");
							}
					else{
					StringBuffer buffer=new StringBuffer();
					while (result.moveToNext()){
						buffer.append("id:		"+result.getString(0)+"\n");
						buffer.append("IP No:	"+result.getString(1)+"\n");
						buffer.append("Age:		"+result.getString(3)+"\n\n");
						buffer.append("Gender:	"+result.getString(4)+"\n\n");
						buffer.append("Place of Residence: "+result.getString(5)+"\n\n");
						buffer.append("Ward No:		"+result.getString(6)+"\n\n");
						buffer.append("Bed No:		"+result.getString(7)+"\n\n");
						buffer.append("Diagnosis:	"+result.getString(8)+"\n\n");
						buffer.append("Referred:	"+result.getString(9)+"\n\n");
						buffer.append("Reason for Referral: "+result.getString(10)+"\n\n");   
						}
				showmessage("Data",buffer.toString());
				}*/
			}}); 
        }
        public void showmessage(String title, String message){
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setCancelable(true);
        	builder.setTitle(title);
        	builder.setMessage(message);
        	builder.show();
        };
    	public void getData( ) {
			Cursor result=db.getAllPatients();
			if(result.getCount()==0){
				showmessage("Error", "No Data Yet");
					}
			StringBuffer buffer=new StringBuffer();
			while (result.moveToNext()){
				name.setText(result.getString(2));
				age.setText(result.getString(3));
				gender.setText(result.getString(4));
				resid.setText(result.getString(5));
				diagnosis.setText(result.getString(8));
				height.setText(result.getString(10));
				weight.setText(result.getString(9));
				calculateBMI();
				break;
			}
				
	};
	public void calculateBMI() {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel = bmi + " , " + bmiLabel;
        bmi_result.setText(bmiLabel);
    }
}
        
       


