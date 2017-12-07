package com.phone.document;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

//класс для копирования папок с вложениями
class MyFileCopyVisitor extends SimpleFileVisitor {
    private Path source, target;


    public MyFileCopyVisitor(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) {
        Path path = (Path) dir;
        Path newstarge = target.resolve(source.relativize(path));
        try {
            Files.copy(path, newstarge, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) {
        Path path = (Path) file;
        Path newstarge = target.resolve(source.relativize(path));
        try {
            Files.copy(path, newstarge, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

}