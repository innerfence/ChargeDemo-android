OVERVIEW
========

The ChargeDemo source code demonstrates how to implement 2-way
integration for accepting credit card payments using Credit Card
Terminal for Android.

ChargeDemo supplies a single charge button. When it's tapped, a
request is generated which will launch Credit Card Terminal in order
to accept credit card payment. When the user is done with Credit Card
Terminal, it'll close and return to Charge Demo, the calling app.

CHARGE REQUEST
================

The Charge request is simply a Bundle of parameters that is included
in the Intent that starts Credit Card Terminal

* `amount` - amount of the transaction (e.g. `10.99`, `1.00`, `0.90`)
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

If you want to include your own app-specific parameters, please
include your own Bundle inside `extraParams`

CHARGE RESPONSE
=================

When the transaction is completed, cancelled, or has an error, it'll
return to your app with a Bundle of parameters inside the result
Intent.

* `responseType` - `approved`, `cancelled`, or `error`
* `amount` - amount charged (e.g. `10.99`)
* `currency` - currency of amount (e.g. `USD`)
* `redactedCardNumber` - redacted card number (e.g. `XXXXXXXXXXXX1111`)
* `cardType` - card type: `Visa`, `MasterCard`, `Amex`, `Discover`, `Maestro`, `Solo`, or `Unknown`

FILE MANIFEST
=============

* README.markdown

This file.

* COPYING

A copy of the MIT License, under which you may reuse any of the source
code in this sample.

* src/com/innerfence/chargedemo/ChargeResponse.java
* src/com/innerfence/chargedemo/ChargeRequest.java

The ChargeRequest and ChargeResponse classes. Copy these files into
your own Android project. There are no external dependencies other
than the android libraries.

* res/values/ids.xml

This is used to generate a unique request code for launching Credit
Card Terminal, so when it finishes, you’ll it’s returning from Credit
Card Terminal. If you already have an existing ids.xml, feel free to
merge it into yours or rename the file.

* src/com/innerfence/chargedemo/ChargeDemoActivity.java
* res/layout/main.xml

A very simple activity that provides a single Charge button. When the
button is tapped, an ChargeRequest object is created and submitted.

* src/com/innerfence/chargedemo/ChargeDemoApplication.java
* AndroidManifest.xml

These are files needed so that it can be compiled into an Android app.

