package com.guorong.singleton.lazy.static_singleton;

class Client {
    public static void main(String[] args) {
        StaticSingleton instance = StaticSingleton.getInstance();
        assert instance != null;
    }
}

/**
 * 静态内部类单例模式(懒汉式)
 */
class StaticSingleton {

    /**
     * 私有化构造函数: 防止外部创建该类实例
     */
    private StaticSingleton() {
        System.out.println("StaticSingleton instance is created");
    }

    /**
     * 该内部类持有单例
     */
    private static class SingletonHolder {
        private static final StaticSingleton instance = new StaticSingleton();
    }

    /**
     * 获取类实例
     * @return
     */
    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
