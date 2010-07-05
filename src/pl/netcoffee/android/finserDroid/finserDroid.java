package pl.netcoffee.android.finserDroid;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class finserDroid extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.homemenu, menu);
    	return true;
    	
    }

    
   public boolean onOptionsItemSelected(MenuItem item){
		
    	if(item.getItemId() == R.id.launchSettings){
    		
    		Intent intent = new Intent(this, SettingsScreen.class);
    		startActivity(intent);
    	}
    	
    	return true;
    	
    }
    
    
}