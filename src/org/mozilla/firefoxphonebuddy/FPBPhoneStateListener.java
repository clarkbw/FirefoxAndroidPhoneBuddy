/**
 * 
 */
package org.mozilla.firefoxphonebuddy;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @author clarkbw
 *
 */
public class FPBPhoneStateListener extends PhoneStateListener {

	private Context context = null;
	/**
	 * 
	 */
	public FPBPhoneStateListener(Context context) {
		super();
		this.context = context;		
	}

	@Override
    public void onCallStateChanged(int state, String incomingNumber)
    {
			super.onCallStateChanged(state, incomingNumber);

            String callState = "UNKNOWN";
           
            switch(state)
            {
                    case TelephonyManager.CALL_STATE_IDLE:          callState = "IDLE"; break;
                    case TelephonyManager.CALL_STATE_RINGING:       callState = "Ringing (" + incomingNumber + ")"; break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:       callState = "Offhook"; break;
            }

            Toast.makeText(this.context, "onCallState: " + callState, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCellLocationChanged(CellLocation location)
    {
            String locationString = location.toString();

            Toast.makeText(this.context, "onCellLocationChanged: " + locationString, Toast.LENGTH_LONG).show();

            super.onCellLocationChanged(location);
    }



}
