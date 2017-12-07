package com.phone.connection;

import com.phone.interfaces.Phone;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


/**
 * Методы для проверки MSISDN, ICCID и IMSI на присутвие в БД
 */

public class OracleDAO implements Phone {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    public boolean checkMSISDN(String MSISDN) {

        String SQL = "SELECT COUNT (INTERNATIONAL_FORMAT) FROM PHONE_NUMBER WHERE INTERNATIONAL_FORMAT='" + MSISDN + "'";
        int i = jdbcTemplate.getJdbcOperations().queryForObject(SQL, Integer.class);
        if (i == 0) return false;
        else {
            return true;
        }
    }

    public boolean checkICCID(String ICCID) {
        String SQL = "SELECT COUNT (SERIAL_NUMBER) FROM SIMS WHERE SERIAL_NUMBER='" + ICCID + "'";
        int i = jdbcTemplate.getJdbcOperations().queryForObject(SQL, Integer.class);
        if (i == 0) return false;
        else return true;

    }

    public boolean checkIMSI(String IMSI) {
        String SQL = "SELECT COUNT (*) FROM SIM_SERIES WHERE "+IMSI+" BETWEEN START_IMSI_NUMBER AND END_IMSI_NUMBER";
        int i = jdbcTemplate.getJdbcOperations().queryForObject(SQL, Integer.class);
        if (i == 0) return false;
        else return true;
    }

}
