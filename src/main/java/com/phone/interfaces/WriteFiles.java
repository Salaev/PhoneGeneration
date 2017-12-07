package com.phone.interfaces;

import com.phone.document.CheckPhoneDB;

/**
 * Created by amsalaye on 27.09.2017.
 */
public interface WriteFiles {
    public String[] WriteFiles(String ICCID1, String IMSI1, String MSISDN1, String id, String Student, String Teacher, String Test, String Rezerve, CheckPhoneDB checkPhoneDB);
}
