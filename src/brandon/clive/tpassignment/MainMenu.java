package brandon.clive.tpassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		Button add = (Button) findViewById(R.id.add_user);
		Button view = (Button) findViewById(R.id.view_data);
		
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Class myClass;
				try {
					myClass = Class.forName("brandon.clive.tpassignment.AddUser");
					Intent i = new Intent(MainMenu.this, myClass);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Class myClass;
				try {
					myClass = Class.forName("brandon.clive.tpassignment.ViewSomeData");
					Intent i = new Intent(MainMenu.this, myClass);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
