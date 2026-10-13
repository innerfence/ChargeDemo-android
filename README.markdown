OVERVIEW
========

The ChargeDemo source code demonstrates how to implement 2-way
integration for accepting credit card payments using Credit Card
Terminal for Android.

ChargeDemo supplies a single charge button. When it's tapped, a
request is generated which will launch Credit Card Terminal in order
to accept credit card payment. When the user is done with Credit Card
Terminal, it'll close and return to Charge Demo, the calling app.

Please visit our [Developer API
page](http://www.innerfence.com/apps/credit-card-terminal/app-developers)
to see how the user experience flow will be like.

INTEGRATION CHECKLIST
=====================

* Add ChargeResponse.java and ChargeRequest.java to your Android
  project. You can just add the files if you like, or you can copy the
  chargeapi module and add that as a dependency.

* Request payment by creating a ChargeRequest object, setting its
  properties, and calling its submit method. You will also need to
  handle ChargeResponse.ApplicationNotInstalledException in case
  Credit Card Terminal is not installed on the device. For example:

```java
ChargeRequest chargeRequest = new ChargeRequest();

// Include my record_id so it comes back with the response
Bundle extraParams = new Bundle();
extraParams.putString( "record_id", "123" );
chargeRequest.setExtraParams( extraParams );

chargeRequest.setAmount("50.00");
chargeRequest.setDescription("Test transaction");
chargeRequest.setInvoiceNumber("321");

// Include a tax rate if you want Credit Card terminal to calculate
// sales tax. If you pass in "default", we'll use the default sales
// tax preset by the user. If you leave it as null, we’ll hide the
// sales tax option from the user.
chargeRequest.setTaxRate( "8.5" );

try
{
    // Pass in the Activity that will launch Credit Card Terminal
    chargeRequest.submit( this );
}
catch( ChargeRequest.ApplicationNotInstalledException ex )
{
    // We suggest providing the user a link to download Credit Card
    // Terminal when the app is not installed. For your convenience,
    // we have provided ChargeRequest.CCTERMINAL_MARKET_LINK which is
    // the direct link to Credit Card Terminal in the marketplace.
}
```

* Handle charge response in your calling activity's onActivityResult
  by creating a ChargeResponse object passing in the intent data. Use
  the value returned by getResponseCode() to determine if the
  transaction was successful. For example:

```java
@Override
public void onActivityResult( int requestCode, int resultCode, Intent data )
{
    // The requestCode should match ChargeRequest.CCTERMINAL_REQUEST_CODE
    // when returning from Credit Card Terminal.
    if( requestCode == ChargeRequest.CCTERMINAL_REQUEST_CODE )
    {
        ChargeResponse chargeResponse = new ChargeResponse( data );

        // My record_id from the request is available in the Bundle returned
        // from getExtraParams()
        Bundle extraParams = chargeResponse.getExtraParams();
        if( null != extraParams )
        {
            String recordId = chargeResponse.getExtraParams().getString("record_id");
        }

        if ( chargeResponse.getResponseCode() == ChargeResponse.Code.APPROVED )
        {
            // Transaction succeeded, check out these properties:
            //  * chargeResponse.getTransactionId()
            //  * chargeResponse.getAmount() (includes tax and tip)
            //  * chargeResponse.getTaxAmount()
            //  * chargeResponse.getTaxRate()
            //  * chargeResponse.getTipAmount()
            //  * chargeResponse.getCardType()
            //  * chargeResponse.getRedactedCardNumber()
        }
        else
        {
            // Transaction failed.
        }
    }
}
```

CHARGE REQUEST
================

The Charge request is simply a Bundle of parameters that is included
in the Intent that starts Credit Card Terminal

* `returnAppName` - your app's name, displayed to give the user context
* `returnImmediately` - if set to 1, we will return to your app immediately instead of waiting for the end user to tap through the “Approved” screen
* `amount` - amount of the transaction (e.g. `10.99`, `1.00`, `0.90`)
* `amountFixed` - if set to 1, the amount (subtotal) will be unchangable. If tips or sales tax is enabled, the final amount can still differ
* `taxRate` - sales tax rate to apply to amount (e.g. `8`, `8.5`, `8.25`, `8.125`)
* `currency` - currecy code of amount (e.g. `USD`)
* `email` - customer's email address for receipt
* `firstName` - billing first name
* `lastName` - billing last name
* `company` - billing company name
* `address` - billing street address
* `city` - billing city
* `state` - billing state or province (e.g. `TX`, `ON`)
* `zip` - billing zip or postal code
* `phone` - billing phone number
* `country` - billing country code (e.g. `US`)
* `invoiceNumber` - merchant-assigned invoice number
* `description` - description of goods or services

* `extraParams` - your own app-specific parameters Bundle

CHARGE RESPONSE
=================

When the transaction is completed, cancelled, or has an error, it'll
return to your app with a Bundle of parameters inside the result
Intent.

* `ifcc_responseType` - `approved`, `cancelled`, `declined`, or `error`
* `ifcc_transactionId` - transaction id (e.g. `100001`)
* `ifcc_amount` - amount charged (e.g. `10.99`)
* `ifcc_taxAmount` - tax portion from amount (e.g. `0.93`)
* `ifcc_taxRate` - tax rate applied to original amount (e.g. `8.5`)
* `ifcc_tipAmount` - tip portion from amount (e.g. `1.50`)
* `ifcc_currency` - currency of amount (e.g. `USD`)
* `ifcc_redactedCardNumber` - redacted card number (e.g. `XXXXXXXXXXXX1111`)
* `ifcc_cardType` - card type: `Visa`, `MasterCard`, `Amex`, `Discover`, `Maestro`, `Solo`, or `Unknown`
* `ifcc_errorMessage` - diagnostic message if `responseType` was `error`

* `ifcc_extraParams` - your app-specific parameters Bundle provided in the
  original request

SETTING UP PROJECT
==================

Use JDK 17, Android SDK Platform 35, and Build-Tools 35.0.1. Set
`ANDROID_HOME` to the SDK location or set `sdk.dir` in an untracked
`local.properties` file. The checked-in wrapper uses Gradle 8.9 with
Android Gradle Plugin 8.7.0. The demo requires Android API 30 or later.

COMPILING AND INSTALLING
========================

Run these commands from the ChargeDemo repository root to compile the
library and demo:

```bash
./gradlew :chargeapi:assembleDebug :app:assembleDebug
```

To install the demo on a connected test device or emulator:

```bash
./gradlew :app:installDebug
```

The Credit Card Terminal app must also be installed to exercise the payment
flow. When this repository is included as a submodule, the parent project's
applicable guidance also applies. See `AGENTS.md` for maintenance guidance
and verification pointers.

FILE MANIFEST
=============

* README.markdown

This file.

* COPYING

A copy of the MIT License, under which you may reuse any of the source
code in this sample.

* chargeapi/src/main/java/com/innerfence/chargeapi/ChargeResponse.java
* chargeapi/src/main/java/com/innerfence/chargeapi/ChargeRequest.java

The ChargeRequest and ChargeResponse classes. Copy these files into
your own Android project. There are no external dependencies other
than the android libraries.

The rest of the files comprise a simple demonstration android app
using the chargeapi classes.

