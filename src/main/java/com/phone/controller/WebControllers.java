package com.phone.controller;

import com.phone.connection.OracleDAO;
import com.phone.document.CheckPhoneDB;
import com.phone.document.TXTCopy;
import com.phone.model.Number;
import com.phone.subsystem.Other;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

/**
 * Created by amsalaye on 26.09.2017.
 */
@Controller
public class WebControllers {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Number number = (Number) context.getBean("number");

        model.addAttribute("IMSI", number.getImsi());
        model.addAttribute("ICCID", number.getIccid());
        model.addAttribute("MSISDN", number.getMsisdn());

        model.addAttribute("IMSI1", Other.PlusOne(number.getImsi()));
        model.addAttribute("ICCID1", Other.PlusOne(number.getIccid()));
        model.addAttribute("MSISDN1", Other.PlusOne(number.getMsisdn()));
        model.addAttribute("description", "");
        return "index";
    }
    @RequestMapping(value = "/generation", method = RequestMethod.GET)
    public String generation(@RequestParam String ICCID1,@RequestParam String IMSI1, @RequestParam String MSISDN1, @RequestParam String id,
                             @RequestParam String Student, @RequestParam String Teacher, @RequestParam String Test, @RequestParam String Rezerve, ModelMap model) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Number number = (Number) context.getBean("number");

        CheckPhoneDB checkPhoneDB = (CheckPhoneDB)context.getBean("check");


        String summary[] = number.getWriteFiles().WriteFiles(ICCID1, IMSI1, MSISDN1, id, Student, Teacher, Test, Rezerve, checkPhoneDB );

        TXTCopy txtCopy = (TXTCopy) context.getBean("copyFile");
        txtCopy.copy();

        number.setImsi(summary[1]);
        number.setIccid(summary[2]);
        number.setMsisdn(summary[0]);
        String description = "Файлы в дирректориях: <br>- " +
                txtCopy.getLinkTerminalCatalog() + "<br>- " +txtCopy.getLinkTerminalCatalogCopy()+
                "<br>созданы успешно!<br>" +
                "Найдено дублирующих элементов в Базах данных - " + summary[3] +
                "<br> При нахождении дублирующих элементов данные заменяются на следующие по порядку";

        model.addAttribute("IMSI", number.getImsi());
        model.addAttribute("ICCID", number.getIccid());
        model.addAttribute("MSISDN",number.getMsisdn());

        model.addAttribute("IMSI1", Other.PlusOne(number.getImsi()));
        model.addAttribute("ICCID1", Other.PlusOne(number.getIccid()));
        model.addAttribute("MSISDN1", Other.PlusOne(number.getMsisdn()));

        model.addAttribute("description", description);

        return "index";
    }

    @RequestMapping(value = "/clean", method = RequestMethod.GET)
    public String clean(ModelMap model){
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        TXTCopy txtCopy = (TXTCopy) context.getBean("copyFile");
        txtCopy.deletefile(new File(txtCopy.getLinkTerminalCatalog()));
        txtCopy.deletefile(new File(txtCopy.getLinkTerminalCatalogCopy()));

        String description = "Дирректории: <br>- " +
                txtCopy.getLinkTerminalCatalog() + "<br>-" + txtCopy.getLinkTerminalCatalogCopy() + "<br>"+
                "очищены";

        Number number = (Number) context.getBean("number");

        model.addAttribute("IMSI", number.getImsi());
        model.addAttribute("ICCID", number.getIccid());
        model.addAttribute("MSISDN", number.getMsisdn());

        model.addAttribute("IMSI1", Other.PlusOne(number.getImsi()));
        model.addAttribute("ICCID1", Other.PlusOne(number.getIccid()));
        model.addAttribute("MSISDN1", Other.PlusOne(number.getMsisdn()));
        model.addAttribute("description", description);


        return "index";

    }
}
