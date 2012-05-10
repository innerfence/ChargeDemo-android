//
// ChargeDemoActivity.java
// Sample Code
//
// An example activity for a simple sort of application which might
// make use of ChargeRequest and ChargeResponse for processing credit
// card charges using Inner Fence's Credit Card Terminal for Android.
//
// You may license this source code under the MIT License. See COPYING.
//
// Copyright (c) 2009 Inner Fence, LLC
//
package com.innerfence.chargedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChargeDemoActivity extends Activity
{
    final int CCTERMINAL_RESPONSE_CODE = 1;

    protected final View.OnClickListener _chargeButtonClickListener =
        new View.OnClickListener()
        {
            @Override
            public final void onClick( View v )
            {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.innerfence.ccterminal");
                startActivityForResult( intent, CCTERMINAL_RESPONSE_CODE );
            }
        };

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        Button chargeButton = (Button)findViewById( R.id.charge_button );
        chargeButton.setOnClickListener( _chargeButtonClickListener );
    }

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        super.onActivityResult( requestCode, resultCode, data );

        if( requestCode == CCTERMINAL_RESPONSE_CODE )
        {
        }
    }
}
