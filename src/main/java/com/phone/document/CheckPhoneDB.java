package com.phone.document;

import com.phone.connection.OracleDAO;
import com.phone.interfaces.CheckBD;

/**
 * Created by amsalaye on 02.10.2017.
 */
public class CheckPhoneDB implements CheckBD {
    private OracleDAO oracleDAO;
    private OracleDAO oracleDAO2;

    public OracleDAO getOracleDAO2() {
        return oracleDAO2;
    }

    public void setOracleDAO2(OracleDAO oracleDAO2) {
        this.oracleDAO2 = oracleDAO2;
    }

    public OracleDAO getOracleDAO() {
        return oracleDAO;
    }

    public void setOracleDAO(OracleDAO oracleDAO) {
        this.oracleDAO = oracleDAO;
    }


    public boolean checkPhoneDB(String MSISDN, String ICCID, String IMSI) {

        if (getOracleDAO().checkMSISDN(MSISDN) || getOracleDAO().checkIMSI(IMSI) || getOracleDAO2().checkICCID(ICCID)) {
            return true;
        } else {
            return false;
        }

    }

}
