package pl.netcoffee.android.finserDroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsScreen extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
	}
	

}
