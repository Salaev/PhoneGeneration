package com.phone.model;

import com.phone.interfaces.LoadFile;

import com.phone.interfaces.WriteFiles;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by amsalaye on 27.09.2017.
 */
public class Number {

    private WriteFiles writeFiles;


    private String name;
    private String msisdn;
    private String iccid;
    private String imsi;
    private String pin1;
    private String puk1;
    private String pin2;
    private String puk2;

    public Number(){}

    public Number(String name, String msisdn, String iccid, String imsi, String pin1, String puk1, String pin2, String puk2) {
        this.name = name;
        this.msisdn = msisdn;
        this.iccid = iccid;
        this.imsi = imsi;
        this.pin1 = pin1;
        this.puk1 = puk1;
        this.pin2 = pin2;
        this.puk2 = puk2;
    }

public Number(LoadFile loadFile){

    String[] lineSplit = loadFile.loadFile().split(" ");
    this.name = lineSplit[0];
    this.msisdn = lineSplit[1];
    this.imsi = lineSplit[2];
    this.iccid = lineSplit[3];
    this.pin1 = lineSplit[4];
    this.puk1 = lineSplit[5];
    this.pin2 = lineSplit[6];
    this.puk2 = lineSplit[7];
}

    public WriteFiles getWriteFiles() {
        return writeFiles;
    }

    public void setWriteFiles(WriteFiles writeFiles) {
        this.writeFiles = writeFiles;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String number) {
        this.msisdn = number;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPin1() {
        return pin1;
    }

    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    public String getPuk1() {
        return puk1;
    }

    public void setPuk1(String puk1) {
        this.puk1 = puk1;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public String getPuk2() {
        return puk2;
    }

    public void setPuk2(String puk2) {
        this.puk2 = puk2;
    }


}
