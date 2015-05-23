package com.example.android_getphonestate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProAllAttend  extends Activity {

	TextView allattend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proallattend);

		allattend = (TextView) findViewById(R.id.proallattend);
	}
}
