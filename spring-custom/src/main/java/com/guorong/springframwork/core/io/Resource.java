package com.guorong.springframwork.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源抽象接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
