package com.guorong.composite.v1;

import java.util.Arrays;
import java.util.List;

// 消息
class Messenger {

    /**
     * 来自精灵的消息
     *
     * @return 返回一个句子
     */
    public LetterComposite messageFromElves() {
        List<Word> words = Arrays.asList(
                new Word('M', 'u', 'c', 'h'),
                new Word('w', 'i', 'n', 'd'),
                new Word('p', 'o', 'u', 'r', 's'),
                new Word('f', 'r', 'o', 'm'),
                new Word('y', 'o', 'u', 'r'),
                new Word('m', 'o', 'u', 't', 'h')
        );
        return new Sentence(words);
    }


    /**
     * 兽人收到消息的回复
     *
     * @return 返回一个单词
     */
    public LetterComposite wordReturnFromElves() {
        return new Word('o', 'k');
    }

}
