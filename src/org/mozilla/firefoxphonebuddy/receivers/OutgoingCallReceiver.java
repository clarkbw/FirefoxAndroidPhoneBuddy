/**
 * 
 */
package org.mozilla.firefoxphonebuddy.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @author clarkbw
 *
 */
public class OutgoingCallReceiver extends BroadcastReceiver {

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
        final String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Toast.makeText(context, "Caught Outgoing Phone Number: " + number + " : " + state, Toast.LENGTH_LONG).show();

	}

}
