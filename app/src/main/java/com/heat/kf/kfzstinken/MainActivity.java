package com.heat.kf.kfzstinken;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import heat.kf.com.tinkenlibrary.service.Judgment;
import heat.kf.com.tinkenlibrary.tools.MyConstants;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bug(View view){
        Toast.makeText(this,"KF: " + NUM.num(),Toast.LENGTH_SHORT).show();
        Log.d("FLY","SD:   "+ MyConstants.SDCard_FILEPATH+ File.separator+this.getPackageName()+File.separator);
    }

    public void repair(View view){
        Judgment judgment = new Judgment();
        judgment.fileDex(this);
    }
}
