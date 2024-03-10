package com.guorong.composite.v1;


class App {
    public static void main(String[] args) {
        Messenger messenger = new Messenger();
        // 句子
        LetterComposite sentence = messenger.messageFromElves();
        System.out.println(String.format("发送消息给精灵: %s", sentence.show()));
        // 单词
        LetterComposite world = messenger.wordReturnFromElves();
        System.out.println(String.format("精灵回答: %s", world.show()));
    }
}
