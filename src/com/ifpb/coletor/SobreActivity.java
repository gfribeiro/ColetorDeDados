package com.ifpb.coletor;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;

public class SobreActivity extends Activity {

	private TextView ver;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre_activity);
        try {
			String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
			
			ver = (TextView) findViewById(R.id.txVersao);
			
			ver.setText(versionName);
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
