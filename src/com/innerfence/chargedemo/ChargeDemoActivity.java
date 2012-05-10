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
import java.util.HashMap;

public class ChargeDemoActivity extends Activity
{
    // Here we set up an ChargeRequest object and submit it in order
    // to invoke Credit Card Terminal.
    protected final View.OnClickListener _chargeButtonClickListener =
        new View.OnClickListener()
        {
            @Override
            public final void onClick( View v )
            {
                // Create the ChargeRequest using the default
                // constructor.
                //
                // If we wanted to, we could pass in a object that
                // implements IChargeResponseListener as the first
                // parameter. When no listener is provided, a simple
                // UI alert is displayed in the case that the charge
                // request cannot be invoked.
                ChargeRequest chargeRequest = new ChargeRequest();

                // 2-way Integration
                //
                // Credit Card Terminal will return to the activity
                // that started it when the transaction is complete.
                //
                // You can also include app-specific parameters by
                // calling the setExtraParams() method and passing in
                // a Map<String,String> object. The extra params will
                // be accessible to you when we return to your app.
                //
                // In this sample, we include an app-specific
                // "record_id" parameter set to the value 123. You may
                // call extra parameters anything you like.
                HashMap<String,String> extraParams = new HashMap<String,String>();
                extraParams.put( "record_id", "123" );
                chargeRequest.setExtraParams( extraParams );

                // Finally, we can supply customer and transaction
                // data so that it will be pre-filled for submission
                // with the charge.
                chargeRequest.setAddress("123 Test St");
                chargeRequest.setAmount("50.00");
                chargeRequest.setCurrency("USD");
                chargeRequest.setCity("Nowhereville");
                chargeRequest.setCompany("Company Inc");
                chargeRequest.setCountry("US");
                chargeRequest.setDescription("Test transaction");
                chargeRequest.setEmail("john@example.com");
                chargeRequest.setFirstName("John");
                chargeRequest.setInvoiceNumber("321");
                chargeRequest.setLastName("Doe");
                chargeRequest.setPhone("555-1212");
                chargeRequest.setState("HI");
                chargeRequest.setZip("98021");

                // Submitting the request will launch Credit Card
                // Terminal from the passed in Activity
                chargeRequest.submit( ChargeDemoActivity.this );
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

        // When the transaction is complete, the calling activity's
        // onActivityResult() method will be called. You can validate
        // that it's returning from our app by confirming the request
        // code matches ours.
        if( requestCode == ChargeRequest.CCTERMINAL_REQUEST_CODE )
        {
            ChargeResponse chargeResponse = new ChargeResponse( data );
        }
    }
}
