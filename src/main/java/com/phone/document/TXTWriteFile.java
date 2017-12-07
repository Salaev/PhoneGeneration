package com.phone.document;

import com.phone.interfaces.WriteFiles;
import com.phone.subsystem.Other;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amsalaye on 27.09.2017.
 */
public class TXTWriteFile implements WriteFiles {
    //устанвока доступа к терминальной машине и каталогу в которой необходимо создать папки и документы
    private String linkTerminalCatalog;
    private String LinkFileGenerel;

    public void setLinkFileGenerel(String linkFileGenerel) {
        LinkFileGenerel = linkFileGenerel;
    }

    public void setLinkTerminalCatalog(String linkTerminalCatalog) {
        this.linkTerminalCatalog = linkTerminalCatalog;
    }

    private String ICCID;
    private String IMSI;
    private String MSISDN;
    int rejected;

    public String[] WriteFiles(String ICCID, String IMSI, String MSISDN, String id, String Student, String Teacher, String Test, String Rezerve, CheckPhoneDB checkPhoneDB) {
        this.ICCID = ICCID;
        this.IMSI = IMSI;
        this.MSISDN = MSISDN;
        this.rejected = 0;


        //данные ввиде даты для создания каталога и запси PIN и PUK
        Date currentDate = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd_MM_yyyy");
        SimpleDateFormat formatPin = new SimpleDateFormat("ddMM");
        SimpleDateFormat formatPuk = new SimpleDateFormat("ddMMyyyy");

        //создаем ссылку на каталог
        String directory = linkTerminalCatalog + "SIM_" + formatForDateNow.format(currentDate).toString();

        File descriptionFile = new File(directory);
        if (!descriptionFile.exists()) {
            descriptionFile.mkdirs();
        }

        //создаем файл для записи описания
        descriptionFile = new File(directory + "/Description.txt");
        //очищаем предыдущие данные
        try {
            if (descriptionFile.exists()) {
                descriptionFile.delete();
                descriptionFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //записываем в файл текущую дату
        BufferedWriter bwDescriptionFile = null;
        try {
            bwDescriptionFile = new BufferedWriter(new FileWriter(descriptionFile, true));
            bwDescriptionFile.write(formatForDateNow.format(currentDate));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bwDescriptionFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //создаем подкаталоги
        if (id.equals("kontrakt,komplect")) {
            String link = directory + "/kontrakt/";
            write(link, this.ICCID, this.IMSI, this.MSISDN, Student, Teacher, Test, Rezerve, "kontrakt", descriptionFile, checkPhoneDB);
            link = directory + "/komplect/";
            write(link, this.ICCID, this.IMSI, this.MSISDN, Student, Teacher, Test, Rezerve, "komplect", descriptionFile, checkPhoneDB);
        }
        if (id.equals("kontrakt")) {
            String link = directory + "/kontrakt/";
            write(link, this.ICCID, this.IMSI, this.MSISDN, Student, Teacher, Test, Rezerve, "kontrakt", descriptionFile, checkPhoneDB);
        }
        if (id.equals("komplect")) {
            String link = directory + "/komplect/";
            write(link, this.ICCID, this.IMSI, this.MSISDN, Student, Teacher, Test, Rezerve, "komplect", descriptionFile, checkPhoneDB);
        }


        return new String[]{Other.negativeOne(this.MSISDN), Other.negativeOne(this.IMSI), Other.negativeOne(this.ICCID), Integer.toString(rejected)};
    }

    //метод для записи в файлы
    public void write(String directory, String ICCID, String IMSI, String MSISDN, String Student, String Teacher, String Test, String Rezerve, String nameSale, File descriptionFile, CheckPhoneDB checkPhoneDB) {

        int count = Integer.parseInt(Student) + Integer.parseInt(Teacher) + Integer.parseInt(Test) + Integer.parseInt(Rezerve);

        int j = 1;


        //проверка что передан запрос хотя бы на создания одного телефона
        if (count != 0) {

            //текущая дата в разных форматах
            Date currentDate = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd_MM_yyyy");
            SimpleDateFormat formatPin = new SimpleDateFormat("ddMM");
            SimpleDateFormat formatPuk = new SimpleDateFormat("ddMMyyyy");


            //открываем файл classpath/General.txt
            File generalFile = new File(LinkFileGenerel);

            //формирование имени файла
            String name = "_Student";
            for (int i = 0; i < count; i++) {

                if ((i == Integer.parseInt(Student)) || Integer.parseInt(Student) == 0) {
                    name = "_Teacher";
                    j = 1;
                }
                if ((i == (Integer.parseInt(Student) + Integer.parseInt(Teacher))) || (Integer.parseInt(Student) + Integer.parseInt(Teacher)) == 0) {
                    name = "_Test";
                    j = 1;
                }

                if ((i == (Integer.parseInt(Student) + Integer.parseInt(Teacher) + Integer.parseInt(Test)))) {
                    name = "_Rezerve";
                    j = 1;
                }

                //проверка на уникальность по базам данных
                while (checkPhoneDB.checkPhoneDB(MSISDN, ICCID, IMSI)) {
                    ICCID = Other.PlusOne(ICCID);
                    IMSI = Other.PlusOne(IMSI);
                    MSISDN = Other.PlusOne(MSISDN);
                    rejected++;
                }


                //формирование полного пути к файлу с параметрами комплекта или контракта
                //создаем каталог для комплекта или контракта
                File catalog = new File(directory);

                if (!catalog.exists()) {
                    catalog.mkdirs();
                }

                String link = directory + nameSale + name + "_" + j + "_" + MSISDN + ".txt";//создаем ссылку с именем файла для записи

                //создание файла для записи в файл с параметрами комплекта или контракта
                File file = new File(link.trim());

                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Не удается создать файл " + link);
                }


                //формируем полные данные для записи и из записываем
                String PIN = formatPin.format(currentDate).toString();
                String PUK = formatPuk.format(currentDate).toString();


                String lineSeparator = System.getProperty("line.separator");

                BufferedWriter bw = null;
                BufferedWriter bwGeneralFile = null;
                BufferedWriter bwDescriptionFile = null;


                try {
                    //записываем в файл с параметрами комплекта или клнтракта
                    bw = new BufferedWriter(new FileWriter(file));
                    bw.write(IMSI + " " + ICCID + "0" + " " + PIN + " " + PUK + " " + PIN + " " + PUK);

                    //перезаписываем строку в General.txt
                    bwGeneralFile = new BufferedWriter(new FileWriter(generalFile));
                    bwGeneralFile.write(lineSeparator + nameSale + "_" + name.substring(1) + "_" + j + " " + MSISDN + " " + IMSI + " " + ICCID + " " + PIN + " " + PUK + " " + PIN + " " + PUK);

                    //записываем в Description.txt
                    bwDescriptionFile = new BufferedWriter(new FileWriter(descriptionFile, true));
                    bwDescriptionFile.write(lineSeparator + nameSale + "_" + name.substring(1) + "_" + j + " " + MSISDN);


                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        bw.close();
                        bwGeneralFile.close();
                        bwDescriptionFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                ICCID = Other.PlusOne(ICCID);
                IMSI = Other.PlusOne(IMSI);
                MSISDN = Other.PlusOne(MSISDN);

                j++;
            }
            this.ICCID = ICCID;
            this.IMSI = IMSI;
            this.MSISDN = MSISDN;


        }

    }

}
