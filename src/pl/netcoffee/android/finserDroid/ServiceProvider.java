package pl.netcoffee.android.finserDroid;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class ServiceProvider {

	protected String username;
	protected String password;
	protected String defaultCashAccount = "";
	protected String defaultCardAccount = "";
	
	protected String cookie = "";
	
	protected boolean isLoggedIn = false;
	
	public ServiceProvider(String uname, String pass, String defaultCashAccount, String defaultCardAccount){
		username = uname;
		password = pass;
		this.defaultCardAccount = defaultCardAccount;
		this.defaultCashAccount = defaultCashAccount;
		
	}
	
	protected boolean login(){
		return true;
	}
	
	
	protected boolean logout(){
		return true;
	}
	
	protected boolean sendCommand(String command){
		return true;
	}
	
	
	protected HttpResponse makeHttpPostRequest(String url, List<NameValuePair> values){
		
		HttpResponse response = null;
  
	    HttpClient httpclient = new DefaultHttpClient();  
	      
	    HttpPost httppost = new HttpPost(url);
	  
	    httppost.setHeader("User-Agent", "finserDroid/1.0");
	    httppost.setHeader("Accept", "application/json, text/javascript, */*");
	    httppost.setHeader("X-Finser-API", "0.5");
	    httppost.setHeader("Cookie", cookie);
	    
	    try {  
	        // Add your data  
	          
	        httppost.setEntity(new UrlEncodedFormEntity(values));  
	  
	        // Execute HTTP Post Request  
	        response = httpclient.execute(httppost);  
	        return response;
	        
	    } catch (ClientProtocolException e) {  
	         
	    } catch (IOException e) {  
	          
	    }  
		 
		return null;
		
	}

	
	
}
