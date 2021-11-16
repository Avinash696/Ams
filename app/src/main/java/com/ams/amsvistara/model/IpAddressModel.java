package com.ams.amsvistara.model;

import android.util.Patterns;

public class IpAddressModel {
    private String strIpAddress;
    private String strSubAddress;

    public IpAddressModel(String IpAddress,String SubAddress) {
        strIpAddress = IpAddress;
        strSubAddress=SubAddress;

    }

    public String getStrIpAddress() {
        return strIpAddress;
    }
    public String getStrSubAddress() {
        return strSubAddress;
    }


    public boolean isIpValid() {
        return Patterns.IP_ADDRESS.matcher(getStrIpAddress()).matches();
    }

    public boolean isSubValid() {
        return getStrSubAddress().length()>0;
    }


    public boolean isIpLengthGreaterThan5() {
        return getStrIpAddress().length() > 5;
    }
}
