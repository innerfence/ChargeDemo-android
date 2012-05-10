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
}
