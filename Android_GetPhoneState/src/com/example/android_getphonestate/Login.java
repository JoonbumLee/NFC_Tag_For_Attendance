package com.example.android_getphonestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity implements OnClickListener
{
	Button btn1, btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn1 = (Button)findViewById(R.id.membership);
		btn2 = (Button)findViewById(R.id.confirm);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if(v.getId() == btn1.getId())
		{
			startActivity(new Intent(this, MembershipForm.class));
		}
		if(v.getId() == btn2.getId())
		{
			int a=2;
			if (a==1)
			{
			startActivity(new Intent(this, AttendenceMenu.class));
			}
			else
			{
				startActivity(new Intent(this, ProAttendenceMenu.class));
			}
		}
	}
}
