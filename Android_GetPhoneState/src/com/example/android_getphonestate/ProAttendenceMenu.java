package com.example.android_getphonestate;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ProAttendenceMenu extends Activity implements OnClickListener {

	Button btn1, btn2;
	ArrayAdapter<String> adapter; 
	ListView listView; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proattendencemenu);
		btn1 = (Button)findViewById(R.id.AttendenceCheck);
		btn2 = (Button)findViewById(R.id.AllAttendence);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		
		ArrayList<String> courselist = new ArrayList<String>();
		courselist.add("알고리즘");
		courselist.add("소프트웨어공학");
		//listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,courselist);
		listView = (ListView) findViewById(R.id.courselist);
		listView.setAdapter(adapter);
}
	@Override
	public void onClick(View v) {
		if(v.getId() == btn1.getId())
		{
			startActivity(new Intent(this, ProOneAttend.class));
		}
		if(v.getId() == btn2.getId())
		{
			startActivity(new Intent(this, ProAllAttend.class));
		}
	}
}
