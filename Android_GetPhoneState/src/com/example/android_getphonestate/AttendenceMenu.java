package com.example.android_getphonestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AttendenceMenu extends Activity implements OnClickListener {
	Button btn1, btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendencemenu);
		btn1 = (Button)findViewById(R.id.attendBtn);
		btn2 = (Button)findViewById(R.id.checkBtn);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == btn1.getId())
		{
			startActivity(new Intent(this, Attend.class));
		}
		if(v.getId() == btn2.getId())
		{
			startActivity(new Intent(this, showAttendence.class));
		}
	}
}
