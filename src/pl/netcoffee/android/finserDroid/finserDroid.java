package pl.netcoffee.android.finserDroid;

import android.content.Context;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class finserDroid extends Activity {
	
	static final int DIALOG_MANUAL_COMMAND = 1;
	static final int DIALOG_CARD_PAID = 2;
	
	private Dialog dialogManualCmd;
	private Dialog dialogCardPaid;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.manualAction);
        button.setOnClickListener(manualCommand);
        
        Button cardPaidButton = (Button)findViewById(R.id.cardPaidButton);
        cardPaidButton.setOnClickListener(cardPaidListener);
        
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

   public void disp(String text){
	 Toast.makeText(this, text, 2000).show();
   }
   
   
   
   protected Dialog onCreateDialog(int id){
	   Dialog dialog;
	   switch(id){
	   case DIALOG_MANUAL_COMMAND:
		   dialogManualCmd = new Dialog(this);
		   dialogManualCmd.setContentView(R.layout.manualactiondialog);
		   dialogManualCmd.setTitle("Ręczna akcja");
		
		   Button regi = (Button)dialogManualCmd.findViewById(R.id.sendManualAction);
		   regi.setOnClickListener(doSendManualCommand);
		   
		   return dialogManualCmd;
	   case DIALOG_CARD_PAID:
		   dialogCardPaid = new Dialog(this);
		   dialogCardPaid.setContentView(R.layout.cardpaid);
		   dialogCardPaid.setTitle("Płatność kartą");
		   
		   Button sendCardPaidButton = (Button)dialogCardPaid.findViewById(R.id.cardPaidSendButton);
		   sendCardPaidButton.setOnClickListener(doCardPaidSend);
		   return  dialogCardPaid;
	   default:
		   dialog = null;
	   }
	   return dialog;
   }
   
   protected void sendCommand(String command){
	   // preferencje
	  
	   AppLogic al = AppLogic.getInstance(this);
	   ServiceProvider api = al.getServiceProvider();
	   api.sendCommand(command);
	   
   }
   
   public OnClickListener doSendManualCommand = new OnClickListener(){
	   @Override
	   public void onClick(View v){
		   
		   Editable operacja;
		   EditText txt = (EditText) dialogManualCmd.findViewById(R.id.manualCommandText);
		   operacja = txt.getText();
		   
		   sendCommand(operacja.toString());

		   dismissDialog(DIALOG_MANUAL_COMMAND);
	   }
   };
   public OnClickListener doCardPaidSend = new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// W ogóle nieprzetestowane
		Context context = v.getContext();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		
		String karta = prefs.getString("defaultCardAccount", "");
		// Potrzebuje pobrać nazwę konta kartowego z preferencji
		String command = "$" + karta + " ";
		String kwota_string;
		EditText kwota_edit_text = (EditText) dialogCardPaid.findViewById(R.id.cardPaidAmount);
		kwota_string = kwota_edit_text.getText().toString();
		try{
			int kwota = Integer.parseInt(kwota_string);
			// Nie wiem dokładnie jak tu jest z formatami
			// Nie mam czasu sprawdzić jak parsuje tutaj
			// finser wymaga 0,33 a nie 0.33.. itd
		} catch( NumberFormatException e) {
			// Tutaj jakiś popup i przerwanie wykonywania
		}
		finally {
			//pass 
		}
		command += kwota_string + " ";
		
		String opis_string;
		EditText opis_edit_text = (EditText)dialogCardPaid.findViewById(R.id.cardPaidDesc);
		opis_string = opis_edit_text.toString();
		
		command += opis_string;
		
		sendCommand(command);
		
		
	}
};
   
   public OnClickListener manualCommand = new OnClickListener(){

	   @Override 
	   public void onClick(View v){
		   showDialog(DIALOG_MANUAL_COMMAND);
	   }	   
   };
   public OnClickListener cardPaidListener = new OnClickListener(){
		
		@Override
		public void onClick(View v) {
			showDialog(DIALOG_CARD_PAID);
		}
	};

    
}