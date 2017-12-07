package com.phone.interfaces;

import javax.sql.DataSource;

/**
 * Created by amsalaye on 02.10.2017.
 */
public interface Phone {
    public void setDataSource(DataSource ds);
    public boolean checkMSISDN(String MSISDN);
    public boolean checkICCID(String ICCID);
    public boolean checkIMSI (String IMSI);
}
