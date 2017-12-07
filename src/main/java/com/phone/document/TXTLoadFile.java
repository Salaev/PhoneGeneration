package com.phone.document;

import com.phone.interfaces.LoadFile;
import com.phone.model.Number;

import java.io.*;

import java.util.List;
import java.util.Properties;

/**
 * Created by amsalaye on 27.09.2017.
 */
public class TXTLoadFile implements LoadFile {
    private String fileName ;

    //установка пути к файлу General.txt из файла ALLproperties.properties
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //чтение TXT документа
    public String loadFile() {
        int countLine = 0;
        String line = "Student_1 79001112235 123456789012345 12345678901234567890 2809 28092017 2809 28092017";

        FileInputStream fstream = null;

        try {
             fstream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                new File(fileName).createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        BufferedReader br = null;
        try {

            br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            while ((strLine = br.readLine()) != null) {
                line = strLine;
                countLine++;
            }


        } catch (IOException e) {
            //e.printStackTrace();
            line = "Student_1 79001112235 123456789012345 12345678901234567890 2809 28092017 2809 28092017";
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                line = "Student_1 79001112235 123456789012345 12345678901234567890 2809 28092017 2809 28092017";
                //  e.printStackTrace();
            }
        }



        return line;
    }
}
