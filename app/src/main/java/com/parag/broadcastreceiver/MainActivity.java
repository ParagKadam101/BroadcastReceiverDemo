package com.parag.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    MyBroadcastReceiver myBroadcastReceiver;
    LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBroadcastReceiver = new MyBroadcastReceiver();
        // localBroadcastManager will stop the propagation of the broadcast beyond the scope of the app
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //localBroadcastManager.registerReceiver(myBroadcastReceiver,new IntentFilter("my-receiver"));
        registerReceiver(myBroadcastReceiver,new IntentFilter("my-receiver"));
    }

    @Override
    protected void onStop() {
        //localBroadcastManager.unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(myBroadcastReceiver);
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn:
                // The String should match the intent-filter's string of the receiver you intend to trigger
                Intent intent = new Intent("my-receiver");
                intent.putExtra("name","The name is Bond! James Bond!");
                //localBroadcastManager.sendBroadcast(intent);
                sendBroadcast(intent);
                break;
            default:break;
        }
    }
}
