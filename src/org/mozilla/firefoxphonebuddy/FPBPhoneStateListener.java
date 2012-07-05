/**
 * 
 */
package org.mozilla.firefoxphonebuddy;

import android.content.Context;
import android.content.Intent;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * @author clarkbw
 *
 */
public class FPBPhoneStateListener extends PhoneStateListener {

	private Context mContext = null;
	private Intent mIntent = null;
	/**
	 * 
	 */
	public FPBPhoneStateListener(Context context) {
		super();
		this.mContext = context;
	}

	public void setIntent(Intent intent) {
		this.mIntent = intent;
	}

	@Override
    public void onCallStateChanged(int state, String incomingNumber)
    {
			super.onCallStateChanged(state, incomingNumber);
			String number = incomingNumber;
			
            String callState = "UNKNOWN";
           
            switch(state)
            {
                    case TelephonyManager.CALL_STATE_IDLE:          
                    	callState = "IDLE"; 
                    	break;
                    case TelephonyManager.CALL_STATE_RINGING:       
                    	callState = "Ringing (" + number + ")"; 
                    	break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    	if (this.mIntent != null) {
                    		number = this.mIntent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                    	}
                    	callState = "Offhook (" + number + ")"; 
                    	break;
            }

            Toast.makeText(this.mContext, "onCallState: " + callState, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCellLocationChanged(CellLocation location)
    {
            String locationString = location.toString();

            Toast.makeText(this.mContext, "onCellLocationChanged: " + locationString, Toast.LENGTH_LONG).show();

            super.onCellLocationChanged(location);
    }



}
