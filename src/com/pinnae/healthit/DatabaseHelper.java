package com.pinnae.healthit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper{
public static final String DATABASE_NAME="login.db";
public static final String TABLE_NAME="user";
public static final String TABLE_NAME2="patients";
public static final String COL1="id";
public static final String COL2="username";
public static final String COL3="password";
public static final String COLUMN1="id";
public static final String COLUMN2="ip";
public static final String COLUMN3="Name";
public static final String COLUMN4="Age";
public static final String COLUMN5="Gender";
public static final String COLUMN6="Residence";
public static final String COLUMN7="WardNo";
public static final String COLUMN8="BedNo";
public static final String COLUMN9="Diagnosis";
public static final String COLUMN10="Referring_facility";
public static final String COLUMN11="Reason";
	
public DatabaseHelper(Context context) {
		super(context,DATABASE_NAME , null, 1);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table "+TABLE_NAME2+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,ip INT,Name TEXT,Age INT,Gender TEXT,Residence TEXT,WardNo INT,BedNo INT, Diagnosis TEXT,Referring_facility TEXT, Reason TEXT)");
		db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,username text, password textPassword,firstname text,lastname text,designation text,ward Number,phone_No phone)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
		onCreate(db);
	}
	
	//inserting in database
	public boolean insert (String username, String password){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COL2,username);
		contentValues.put(COL3,password);
		long result = db.insert(TABLE_NAME, null, contentValues);
		db.close();
		if(result==-1) {
			return false;
			}
		else return true;
	}
	public boolean insertPatient(int ip,String name,int age,String gender,String residence,int ward,int BedNo,String Diagnosis,String facility, String reason ){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN2,ip);contentValues.put(COLUMN3,name);contentValues.put(COLUMN4,age);
		contentValues.put(COLUMN5,gender);contentValues.put(COLUMN6,residence);contentValues.put(COLUMN7,ward);
		contentValues.put(COLUMN8,BedNo);contentValues.put(COLUMN9, Diagnosis);contentValues.put(COLUMN10,facility);
		contentValues.put(COLUMN11, reason);
		long result = db.insert(TABLE_NAME2, null, contentValues);
		db.close();
		if(result==-1) {
			return false;
			}
		else return true;
	}
		//retrieving all data
		public Cursor getAllData(){
		SQLiteDatabase db= this.getWritableDatabase();
		Cursor result=db.rawQuery("select * from "+TABLE_NAME, null);
		return result;
	}
		public Cursor getAllPatients(){
			SQLiteDatabase db= this.getWritableDatabase();
			Cursor result=db.rawQuery("select * from "+TABLE_NAME2, null);
			return result;
		}
		//selecting one patient
		public Cursor getPatient(String ip){
			SQLiteDatabase db= this.getWritableDatabase();
			Cursor result=db.rawQuery("select * from "+TABLE_NAME2+" WHERE "+COLUMN2+" = "+ip, null);
			return result;
		}
		//validating password
		public Boolean check(String username, String password){
			SQLiteDatabase db= this.getWritableDatabase();
			Cursor result=db.rawQuery("select * from "+TABLE_NAME, null);
			Boolean exists=false;
			if(result !=null && result.getCount()>0){
				result.moveToFirst();
				do{
					if(result.getString(result.getColumnIndex(COL2)).equals(username)){
						if(result.getString(result.getColumnIndex(COL3)).equals(password)){
							exists=true;
							break;
						}
					}
					
				}while(result.moveToNext());
			};
			result.close();
			db.close();
			return exists;
		};
		public Boolean checkIp(String username){
			SQLiteDatabase db= this.getWritableDatabase();
			Cursor result=db.rawQuery("select * from "+TABLE_NAME2, null);
			Boolean exists=false;
			if(result !=null && result.getCount()>0){
				result.moveToFirst();
				do{
					if(result.getString(result.getColumnIndex(COLUMN2)).equals(username)){
							exists=true;
							break;
					}
					
				}while(result.moveToNext());
			};
			result.close();
			db.close();
			return exists;
		};
		public Boolean checkUser(String username){
			SQLiteDatabase db= this.getWritableDatabase();
			Cursor result=db.rawQuery("select * from "+TABLE_NAME, null);
			Boolean exists=false;
			if(result !=null && result.getCount()>0){
				result.moveToFirst();
				do{
					if(result.getString(result.getColumnIndex(COL2)).equals(username)){
							exists=true;
							break;
					}
					
				}while(result.moveToNext());
			};
			result.close();
			db.close();
			return exists;
		};
		public Boolean checkPatient(String ip){
			SQLiteDatabase db= this.getWritableDatabase();
			Boolean exists=false;
			Cursor result=db.rawQuery("select * from "+TABLE_NAME2, null);
			if(result !=null && result.getCount()>0){
				result.moveToFirst();
				do{
					if(result.getString(result.getColumnIndex(COLUMN2)).equals(ip)){
						exists=true;
						//remove(ip);
							break;
					}
					
				}while(result.moveToNext());
			}
			result.close();
			db.close();
			return exists;
		};
		public Boolean remove(String ip){
			Boolean status=false;
			SQLiteDatabase db= this.getWritableDatabase();
			db.execSQL("DELETE FROM "+TABLE_NAME2+" WHERE "+COLUMN2+" = "+ip);
			return status;
		};
		
	
}
