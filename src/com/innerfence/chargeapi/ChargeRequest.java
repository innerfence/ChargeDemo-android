//
// ChargeRequest.java
// Inner Fence Credit Card Terminal for Android
// API 1.0.0
//
// You may license this source code under the MIT License, reproduced
// below.
//
// Copyright (c) 2012 Inner Fence, LLC
//
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without
// restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following
// conditions:
//
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.
//
package com.innerfence.chargeapi;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.net.Uri;
import android.os.Bundle;

public class ChargeRequest
{
    public static final int CCTERMINAL_REQUEST_CODE = 0x698893c1;

    public static class Keys
    {
        public static final String ADDRESS         = "address";
        public static final String AMOUNT          = "amount";
        public static final String CITY            = "city";
        public static final String COMPANY         = "company";
        public static final String COUNTRY         = "country";
        public static final String CURRENCY        = "currency";
        public static final String DESCRIPTION     = "description";
        public static final String EMAIL           = "email";
        public static final String EXTRA_PARAMS    = "extraParams";
        public static final String FIRST_NAME      = "firstName";
        public static final String INVOICE_NUMBER  = "invoiceNumber";
        public static final String LAST_NAME       = "lastName";
        public static final String PHONE           = "phone";
        public static final String RETURN_APP_NAME = "returnAppName";
        public static final String STATE           = "state";
        public static final String ZIP             = "zip";
    }

    public static boolean IsAppInstalled( Context context )
    {
        try
        {
            ApplicationInfo appInfo =
                context.getPackageManager().getApplicationInfo(
                    "com.innerfence.ccterminal", PackageManager.GET_META_DATA );

            return true;
        }
        catch( PackageManager.NameNotFoundException ex )
        {
            return false;
        }
    }

    protected String _address;
    protected String _amount;
    protected String _city;
    protected String _company;
    protected String _country;
    protected String _currency;
    protected String _description;
    protected String _email;
    protected Bundle _extraParams;
    protected String _firstName;
    protected String _invoiceNumber;
    protected String _lastName;
    protected String _phone;
    protected String _returnAppName;
    protected String _state;
    protected String _zip;

    protected final DialogInterface.OnClickListener _installCCTerminalListener =
        new DialogInterface.OnClickListener()
        {
            @Override
            public final void onClick( DialogInterface dialog, int which )
            {
                AlertDialog d = (AlertDialog)dialog;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.innerfence.ccterminal"));
                d.getContext().startActivity(intent);
            }
        };

    public ChargeRequest()
    {
    }

    public String getAddress()
    {
        return _address;
    }

    public void setAddress( String value )
    {
        _address = value;
    }

    public String getAmount()
    {
        return _amount;
    }

    public void setAmount( String value )
    {
        _amount = value;
    }

    public String getCity()
    {
        return _city;
    }

    public void setCity( String value )
    {
        _city = value;
    }

    public String getCompany()
    {
        return _company;
    }

    public void setCompany( String value )
    {
        _company = value;
    }

    public String getCountry()
    {
        return _country;
    }

    public void setCountry( String value )
    {
        _country = value;
    }

    public String getCurrency()
    {
        return _currency;
    }

    public void setCurrency( String value )
    {
        _currency = value;
    }

    public String getDescription()
    {
        return _description;
    }

    public void setDescription( String value )
    {
        _description = value;
    }

    public String getEmail()
    {
        return _email;
    }

    public void setEmail( String value )
    {
        _email = value;
    }

    public Bundle getExtraParams()
    {
        return _extraParams;
    }

    public void setExtraParams( Bundle value )
    {
        _extraParams = value;
    }

    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName( String value )
    {
        _firstName = value;
    }

    public String getInvoiceNumber()
    {
        return _invoiceNumber;
    }

    public void setInvoiceNumber( String value )
    {
        _invoiceNumber = value;
    }

    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName( String value )
    {
        _lastName = value;
    }

    public String getPhone()
    {
        return _phone;
    }

    public void setPhone( String value )
    {
        _phone = value;
    }

    public String getReturnAppName()
    {
        return _returnAppName;
    }

    public void setReturnAppName( String value )
    {
        _returnAppName = value;
    }

    public String getState()
    {
        return _state;
    }

    public void setState( String value )
    {
        _state = value;
    }

    public String getZip()
    {
        return _zip;
    }

    public void setZip( String value )
    {
        _zip = value;
    }

    public void submit( Activity callingActivity )
    {
        if( null == _returnAppName )
        {
            try
            {
                String packageName = callingActivity.getPackageName();
                ApplicationInfo appInfo = callingActivity.getPackageManager().getApplicationInfo( packageName, PackageManager.GET_META_DATA );
                _returnAppName = callingActivity.getPackageManager().getApplicationLabel( appInfo ).toString();
            }
            catch( PackageManager.NameNotFoundException ex )
            {
            }
        }

        Bundle bundle = new Bundle();
        bundle.putString( Keys.ADDRESS,         _address );
        bundle.putString( Keys.AMOUNT,          _amount );
        bundle.putString( Keys.CITY,            _city );
        bundle.putString( Keys.COMPANY,         _company );
        bundle.putString( Keys.COUNTRY,         _country );
        bundle.putString( Keys.CURRENCY,        _currency );
        bundle.putString( Keys.DESCRIPTION,     _description );
        bundle.putString( Keys.EMAIL,           _email );
        bundle.putBundle( Keys.EXTRA_PARAMS,    _extraParams );
        bundle.putString( Keys.FIRST_NAME,      _firstName );
        bundle.putString( Keys.INVOICE_NUMBER,  _invoiceNumber );
        bundle.putString( Keys.LAST_NAME,       _lastName );
        bundle.putString( Keys.PHONE,           _phone );
        bundle.putString( Keys.STATE,           _state );
        bundle.putString( Keys.ZIP,             _zip );
        bundle.putString( Keys.RETURN_APP_NAME, _returnAppName );

        Intent intent = new Intent();
        intent.setClassName("com.innerfence.ccterminal", "com.innerfence.ccterminal.TerminalActivity");
        intent.putExtras( bundle );

        if( ChargeRequest.IsAppInstalled(callingActivity) )
        {
            callingActivity.startActivityForResult( intent, CCTERMINAL_REQUEST_CODE );
        }
        else
        {
            AlertDialog d = new AlertDialog.Builder( callingActivity )
                .setTitle( "MISSING: Credit Card Terminal" )
                .setMessage( "You'll need to install Credit Card Terminal before you can use this feature. Tap Install below to begin the installation process." )
                .setPositiveButton( "Install", _installCCTerminalListener )
                .setNegativeButton( android.R.string.cancel, null )
                .create();

            d.show();
        }
    }
}

