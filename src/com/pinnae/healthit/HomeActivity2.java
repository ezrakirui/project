package com.pinnae.healthit;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
@SuppressLint("NewApi")
public class HomeActivity2 extends Activity {
 
    private String[] drawerListViewItems;
    protected DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
 
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome");
 
        // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
        // get ListView defined in home.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
 
        // Set the adapter for the list view
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview, drawerListViewItems));
 
        // 2. App Icon 
       drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
 
        // 2.1 create ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_action_name,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                );
 
        // 2.2 Set actionBarDrawerToggle as the DrawerListener
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
 
        // 2.3 enable and show "up" arrow
        getActionBar().setDisplayHomeAsUpEnabled(true);
 
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
         actionBarDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
         // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
        // then it has handled the app icon touch event
 
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            //Toast.makeText(HomeActivity2.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
            switch(position){
            case 0:{
            	startActivity(new Intent(HomeActivity2.this,HomeActivity2.class));
            	break;
            }
            case 1:{
            	startActivity(new Intent(HomeActivity2.this,PatientActivity.class));
            	break;
            }
            case 2:{
            	startActivity(new Intent(HomeActivity2.this,HomeActivity.class));
            	break;
            }
            case 3:{
            	startActivity(new Intent(HomeActivity2.this,Discharge.class));
            	break;
            }
            case 4:{
            	startActivity(new Intent(HomeActivity2.this,ShareActivity.class));
            	break;
            }case 5:{
            	startActivity(new Intent(HomeActivity2.this,HomeActivity.class));
            	break;
            }case 6:{
            	startActivity(new Intent(HomeActivity2.this,aboutActivity.class));
            	break;
            }
            case 7:{
            	startActivity(new Intent(HomeActivity2.this,logout.class));
            	break;
            }
            }
            drawerListView.setItemChecked(position, true);
            drawerListView.setSelection(position);
            drawerLayout.closeDrawer(drawerListView);
 
        }
    }
    
}