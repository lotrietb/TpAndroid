package brandon.clive.tpassignment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import brandon.clive.classes.DataHandler;

public class ViewAllData extends Activity {
	private DataHandler handler;
	private TableLayout table_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_all_data);
		
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		BuildTable();
		
		Button home = (Button)findViewById(R.id.allHome);
		Button previous = (Button)findViewById(R.id.allPrevious);
		
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Class myClass;
				try {
					myClass = Class.forName("brandon.clive.tpassignment.MainMenu");
					Intent i = new Intent(ViewAllData.this, myClass);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		previous.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	
	private void BuildTable() {
		  handler = new DataHandler(getBaseContext());
		  handler.open();
		  Cursor c = handler.returnInfo();

		  int rows = c.getCount();
		  int cols = c.getColumnCount();

		  c.moveToFirst();
		  // outer for loop
		  for (int i = 0; i < rows; i++) {

		   TableRow row = new TableRow(this);
		   /*row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		     LayoutParams.WRAP_CONTENT));*/

		   // inner for loop
		   for (int j = 0; j < cols; j++) {

			    TextView tv = new TextView(this);
			    /*tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
			      LayoutParams.WRAP_CONTENT));*/
			    //tv.setBackgroundResource(R.drawable.cell_shape);
			    tv.setGravity(Gravity.CENTER);
			    tv.setTextSize(18);
			    tv.setPadding(0, 5, 0, 5);
	
			    tv.setText(c.getString(j));
	
			    row.addView(tv);

		   }

		   c.moveToNext();

		   table_layout.addView(row);

		  }
		  handler.close();
	}
}
