package pl.netcoffee.android.finserDroid;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppLogic {

	
	
	static private AppLogic instance = null;
	
	private Context appContext;

	private ServiceProvider api = null;
	
	static public AppLogic getInstance(Context context){
		if(instance == null){
			instance = new AppLogic(context);
		}
		return instance;
	}
	
	protected AppLogic(Context context){
		
		this.appContext = context;
		
	}
	
	
	public ServiceProvider getServiceProvider(){
		
		if(api == null){
		
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.appContext);
	
			String selectedApi = prefs.getString("apiCode", "");
			   
			selectedApi = selectedApi.trim();
			
			String login = prefs.getString("serviceUserName", "");
			String passw = prefs.getString("servicePassword", "");
			String defaultCashAccount = prefs.getString("defaultCashAccount", "");
			String defaultCardAccount = prefs.getString("defaultCardAccount", "");
			   
			if(selectedApi.equalsIgnoreCase("debug")){
				api = new DebugAPI(login, passw, defaultCashAccount, defaultCardAccount); 
			}else{
				api = new FinserPlAPI(login, passw, defaultCashAccount, defaultCardAccount);
			}

		}
		
		return api;
		
	}
	
	
	
	
	
	
}
