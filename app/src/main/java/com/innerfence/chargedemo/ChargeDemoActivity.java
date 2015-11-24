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
// Copyright (c) 2012 Inner Fence, LLC
//
package com.innerfence.chargedemo;

import com.innerfence.chargeapi.ChargeRequest;
import com.innerfence.chargeapi.ChargeResponse;

import android.app.*;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                ChargeRequest chargeRequest = new ChargeRequest();

                // 2-way Integration
                //
                // Credit Card Terminal will return to the activity
                // that started it when the transaction is complete.
                //
                // You can also include app-specific parameters by
                // calling the setExtraParams() method and passing in
                // a Bundle object. The extra params will be
                // accessible to you when we return to your app.
                //
                // In this sample, we include an app-specific
                // "record_id" parameter set to the value 123. You may
                // call extra parameters anything you like.
                Bundle extraParams = new Bundle();
                extraParams.putString( "record_id", "123" );
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
                try
                {
                    chargeRequest.submit( ChargeDemoActivity.this );
                }
                catch( ChargeRequest.ApplicationNotInstalledException ex )
                {
                    // An ApplicationNotInstalledException is thrown
                    // when we determine that Credit Card Terminal is
                    // not installed on the device. We suggest showing
                    // the user an error with a easy way to download
                    // the app by showing an AlertDialog similar to
                    // the one below.

                    new AlertDialog.Builder( ChargeDemoActivity.this )
                        .setTitle( "Credit Card Terminal Not Installed" )
                        .setMessage( "You'll need to install Credit Card Terminal before you can use this feature. Tap Install below to begin the installation process." )
                        .setPositiveButton( "Install", _installCCTerminalListener )
                        .setNegativeButton( android.R.string.cancel, null )
                        .create()
                        .show();
                }
            }
        };

    protected final DialogInterface.OnClickListener _installCCTerminalListener =
        new DialogInterface.OnClickListener()
        {
            @Override
            public final void onClick( DialogInterface dialog, int which )
            {
                // When the user taps on Install, we'll automatically
                // open the market link to Credit Card Terminal. The
                // link is stored at
                // ChargeRequest.CCTERMINAL_MARKET_LINK

                AlertDialog d = (AlertDialog)dialog;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(ChargeRequest.CCTERMINAL_MARKET_LINK));
                d.getContext().startActivity(intent);
            }
        };

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
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

            String message;
            String title;
            String recordId = null;

            Bundle extraParams = chargeResponse.getExtraParams();
            if( null != extraParams )
            {
                recordId = chargeResponse.getExtraParams().getString("record_id");
            }

            // You may want to perform different actions based on the
            // response code. This example shows an alert with the
            // response data when the charge is approved.
            if ( chargeResponse.getResponseCode() == ChargeResponse.Code.APPROVED )
            {
                title = "Charged!";
                message = String.format(
                    "Record: %s\n" +
                    "Transaction ID: %s\n" +
                    "Amount: %s %s\n" +
                    "Card Type: %s\n" +
                    "Redacted Number: %s",
                    recordId,
                    chargeResponse.getTransactionId(),
                    chargeResponse.getAmount(),
                    chargeResponse.getCurrency(),
                    chargeResponse.getCardType(),
                    chargeResponse.getRedactedCardNumber()
                );
            }
            else // other response code values are documented in ChargeResponse.h
            {
                title = "Not Charged!";
                message = String.format( "Record: %s", recordId );
            }

            // Generally you would do something app-specific here,
            // like load the record specified by recordId, record the
            // success or failure, etc. Since this sample doesn't
            // actually do much, we'll just pop an alert.
            new AlertDialog.Builder(this)
                .setTitle( title )
                .setMessage( message )
                .setNeutralButton( android.R.string.ok, null )
                .create()
                .show();
        }
    }
}
