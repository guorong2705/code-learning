package com.guorong.class_load_subsystem;

import java.io.FileNotFoundException;

/**
 * 自定义类加载器
 *
 * @author guorong
 */
class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取类的二进制字节数组
        byte[] result = getClassFromCustomPath(name);

        try {
            if (result == null) {
                throw new FileNotFoundException();
            } else {
                // 将字节数组转换为Class类的实例。在使用该类之前，必须先对其进行解析。
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        throw new ClassNotFoundException();
    }

    private byte[] getClassFromCustomPath(String name) {
        // 1. 从自定义路径中加载指定类：细节省略
        // 2. 如果指定路径的字节码文件进行了加密，此处要进行解密
        return null;
    }

}
