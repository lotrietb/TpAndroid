package brandon.clive.tpassignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import brandon.clive.classes.DataHandler;

public class AddUser extends Activity {

	private DataHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);
		
		//Get TextBox values
		final EditText txtname = (EditText)findViewById(R.id.name);
		final EditText txtsurname = (EditText)findViewById(R.id.surname);
		final EditText txtage = (EditText)findViewById(R.id.age);
		final EditText txtcity = (EditText)findViewById(R.id.city);
		final EditText txtpostalCode = (EditText)findViewById(R.id.postal_code);
		
		Button save = (Button) findViewById(R.id.btnSave);
		Button load = (Button) findViewById(R.id.btnLoadData);
		Button back = (Button) findViewById(R.id.btnBack);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Some validation here
				String name = txtname.getText().toString(); 
				String surname = txtsurname.getText().toString();
				String age = txtage.getText().toString(); 
				String city = txtcity.getText().toString(); 
				String postalCode = txtpostalCode.getText().toString(); 
				
				if(empty(name) || empty(surname)|| empty(age)|| empty(city)|| empty(postalCode))
					Toast.makeText(AddUser.this, "You did not enter all the values. Please type values in all the fields.", Toast.LENGTH_LONG).show();
				else
				{
					handler = new DataHandler(getBaseContext());
					handler.open();
					handler.insertInfo(name, surname, age, city, postalCode);
					handler.close();
					
					AlertDialog.Builder builder = new AlertDialog.Builder(AddUser.this);
					builder.setMessage("User saved to the database!"); 
					builder.setCancelable(false);
					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
					AlertDialog alert = builder.create();
					alert.show();
					txtname.setText("");
					txtsurname.setText("");
					txtage.setText("");
					txtcity.setText("");
					txtpostalCode.setText("");
				}
                
			}
		});
		
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Class myClass;
				try {
					myClass = Class.forName("brandon.clive.tpassignment.ViewSomeData");
					Intent i = new Intent(AddUser.this, myClass);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	private boolean empty(String val)
	{
		if(val.trim().equals(""))
			return true;
		return false;
	}
}
