package com.phone.subsystem;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by amsalaye on 27.09.2017.
 */
public class Other {
    public static String PlusOne(String text){
        BigDecimal bigDecimal = new BigDecimal(text).setScale(0);
        String number = bigDecimal.add(new BigDecimal(1)).toString();
        return number;
    }
    public static String negativeOne(String text){
        BigDecimal bigDecimal = new BigDecimal(text).setScale(0);
        String number = bigDecimal.add(new BigDecimal(-1)).toString();
        return number;
    }

}
