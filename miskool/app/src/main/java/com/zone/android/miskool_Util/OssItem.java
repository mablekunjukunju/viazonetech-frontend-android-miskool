package com.zone.android.miskool_Util;

/**
 * Created by mwolfson on 11/12/16.
 */

public class OssItem {

    public String libName = "";
    public String licType = "";
    public String libUrl = "";
    public String copyright="";

    public static final String APACHE_LISC = "Apache 2.0";
    public static final String MISC_LISC = "BSD, MIT, Apache 2.0";

    public OssItem(String libNameIn, String licTypeIn, String libUrlIn, String coprightin) {
        libName = libNameIn;
        licType = licTypeIn;
        libUrl = libUrlIn;
        copyright=coprightin;

    }

}

