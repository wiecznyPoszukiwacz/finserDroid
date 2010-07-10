package pl.netcoffee.android.finserDroid;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class FinserPlAPI extends ServiceProvider{

	public FinserPlAPI(String uname, String pass, String defaultCashAccount, String defaultCardAccount) {
		super(uname, pass, defaultCashAccount, defaultCardAccount);
		
	}

	protected boolean login(){
		
		if(isLoggedIn == true){
			return true;
		}
		
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);  
        nameValuePairs.add(new BasicNameValuePair("username", username));  
        nameValuePairs.add(new BasicNameValuePair("password", password));  

        HttpResponse response = makeHttpPostRequest("http://api.finser.pl/login/", nameValuePairs);
	        
        Header c = response.getFirstHeader("Set-Cookie");
        cookie = c.getValue();
        
        isLoggedIn = true;
		
		return true;
	}

	public boolean sendCommand(String command){
		login();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);  
        nameValuePairs.add(new BasicNameValuePair("text", command));  

        makeHttpPostRequest("http://api.finser.pl/insert/", nameValuePairs);
		
		return true;

	}
	public boolean removeLast(){
		login();
		
		makeHttpRequest("http://api.finser.pl/remove/");
		return true;
		
	}
	
	
}
