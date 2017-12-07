package com.phone.document;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by amsalaye on 12.10.2017.
 */
public class TXTCopy implements com.phone.interfaces.TXTCopy {
    private String linkTerminalCatalog;
    private String linkTerminalCatalogCopy;

    public String getLinkTerminalCatalog() {
        return linkTerminalCatalog;
    }

    public String getLinkTerminalCatalogCopy() {
        return linkTerminalCatalogCopy;
    }

    public void setLinkTerminalCatalog(String linkTerminalCatalog) {
        this.linkTerminalCatalog = linkTerminalCatalog;
    }

    public void setLinkTerminalCatalogCopy(String linkTerminalCatalogCopy) {
        this.linkTerminalCatalogCopy = linkTerminalCatalogCopy;
    }

    public void copy() {
        Path pathSource = Paths.get(linkTerminalCatalog);
        Path pathTarget = Paths.get(linkTerminalCatalogCopy);

        //рекурсивное удаление предыдущих данных
        File file = new File(linkTerminalCatalogCopy);
        deletefile(file);

        //передача для копирования данных  дерриктрий
        try {
            Files.walkFileTree(pathSource, new MyFileCopyVisitor(pathSource, pathTarget));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //метод для удаления файлов и папок используется рекрсивно
    public void deletefile(File file) {

        if (file.isDirectory()) {
            for (File file1 : file.listFiles()) {
                if (file.isDirectory()) deletefile(file1);
                else file.delete();
            }
        }
        //проверям каталоги метод для извлечения имени каталога куда будут генерироваться данные, что бы их не удалять
        if (!file.getName().equalsIgnoreCase(catalogIgnor(getLinkTerminalCatalog())) & !file.getName().equalsIgnoreCase(catalogIgnor(getLinkTerminalCatalogCopy())))
            file.delete();
    }
    //метод для извлечения имени каталога куда будут генерироваться данные, это надо для того чтобы их не удалять при очитске
    private String catalogIgnor(String link){
        String temp = link.substring(0,link.length()-1);
        String catalogIgnor = temp.substring(temp.lastIndexOf('/')+1);

        return catalogIgnor;
    }

}

