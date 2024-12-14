package com.guorong.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RenameFileDemo {
    public static void main(String[] args) throws IOException {
        String pathStr = "";
        Files.list(Paths.get(pathStr)).forEach(path -> {
            String newFileName = path.getFileName().toString().replace("[瑞客论坛 www.ruike1.com]", "");
            Path newPath = Paths.get(pathStr, newFileName);
            try {
                Files.move(path, newPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
