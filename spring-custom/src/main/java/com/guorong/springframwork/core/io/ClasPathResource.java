package com.guorong.springframwork.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * 类路径资源
 */
public class ClasPathResource implements Resource{

    private String path;

    public ClasPathResource(String path) {
        this.path = path;
    }


    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);
        if (Objects.isNull(inputStream)) {
            throw new FileNotFoundException(String.format("%s not found in classpath", path));
        }
        return inputStream;
    }
}
