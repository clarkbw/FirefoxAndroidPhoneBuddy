/**
 * 
 */
package org.mozilla.firefoxphonebuddy.receivers;

import org.mozilla.firefoxphonebuddy.FPBPhoneStateListener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @author clarkbw
 *
 */
public class IncomingCallReceiver extends BroadcastReceiver {

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
        Toast.makeText(context, "Incoming Phone State: " + state, Toast.LENGTH_LONG).show();

        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Toast.makeText(context, "Incoming Phone Number: " + number, Toast.LENGTH_LONG).show();

        TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
        	// incoming phone number ringing
            mTelephonyManager.listen(this.listener, PhoneStateListener.LISTEN_CALL_STATE);
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
        	// This call has ended, user hung up
        	mTelephonyManager.listen(this.listener, PhoneStateListener.LISTEN_NONE);
        } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
        	// This is an outgoing call
        	if (number == null) {
        		mTelephonyManager.listen(this.listener, PhoneStateListener.LISTEN_NONE);
        	}
        }
	}
}
