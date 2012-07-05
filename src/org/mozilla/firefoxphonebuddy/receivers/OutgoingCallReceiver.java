/**
 * 
 */
package org.mozilla.firefoxphonebuddy.receivers;

import org.mozilla.firefoxphonebuddy.FPBPhoneStateListener;

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

	private FPBPhoneStateListener listener = null;

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		if (this.listener == null) {
			this.listener = new FPBPhoneStateListener(context);
		}
		this.listener.setIntent(intent);

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Toast.makeText(context, "Outgoing Phone State: " + state, Toast.LENGTH_LONG).show();

        String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Toast.makeText(context, "Outgoing Phone Number: " + number, Toast.LENGTH_LONG).show();
	}

}
