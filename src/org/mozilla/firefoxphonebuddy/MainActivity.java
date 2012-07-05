package org.mozilla.firefoxphonebuddy;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private PhoneStateListener listener = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This sets up a constant phone state listener
        // this listener type is really only good for location changes since it needs to be connected
        // to an actual call to listen to the phone state  see: @IncomingCallReceiver
        this.listener = new FPBPhoneStateListener(getApplicationContext());

        // This is an alternate method for monitoring location
        // I'm not really sure which one is the best to use but this one seems to actually
        // get us some latitude and longitude numbers so that may be the obvious win
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener(){

                    @Override
                    public void onLocationChanged(Location location) {
                        Toast.makeText(getApplicationContext(), "Location: " + (int)(location.getLatitude() * 1E6) + ", " + (int)(location.getLongitude() * 1E6), Toast.LENGTH_LONG).show();                      
                    }

                    @Override
                    public void onProviderDisabled(String provider) {}

                    @Override
                    public void onProviderEnabled(String provider) {}

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {}
        }); 
    }

    @Override
    protected void onPause()
    {
            super.onPause();
           
            this.stopListening();
    }

    @Override
    protected void onResume()
    {
            super.onResume();
           
            this.startSignalLevelListener();
    }
   
    @Override
    protected void onDestroy()
    {
            this.stopListening();
           
            super.onDestroy();
    }

    private void stopListening(){
        TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mTelephonyManager.listen(this.listener, PhoneStateListener.LISTEN_NONE);
    }

    private void startSignalLevelListener() {
        TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        mTelephonyManager.listen(this.listener, PhoneStateListener.LISTEN_CELL_LOCATION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
