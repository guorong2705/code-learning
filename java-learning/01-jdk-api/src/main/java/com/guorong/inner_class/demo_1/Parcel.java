package com.guorong.inner_class.demo_1;

/**
 * @author guorong
 * @date 2021-08-10
 */
class Parcel {
    /**
     * 内容内部类
     */
    class Content {

        private int value = 12;

        public int getValue() {
            return value;
        }
    }

    /**
     * 描述内部类
     */
    private class Destination {

        private String message;

        public Destination(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     * 获取 Content 内部类实例
     * @return
     */
    public Content getContent() {
        return new Content();
    }

    /**
     * 获取 Destination 内部类实例
     * @param message
     * @return
     */
    public Destination getDestination(String message) {
        return new Destination(message);
    }

    public void printDestination(String message) {
        System.out.println(getDestination(message).getMessage());
    }
}
