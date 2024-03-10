package com.guorong.composite.v1;

import javafx.util.Builder;

import java.util.ArrayList;
import java.util.List;

// 字母组合
abstract class LetterComposite {
    private final List<LetterComposite> children = new ArrayList<>();

    public void add(LetterComposite letter) {
        children.add(letter);
    }

    public int count() {
        return children.size();
    }

    // 显示前
    protected String showThisBefore() {
        return "";
    }

    // 显示后
    protected String showThisAfter() {
        return "";
    }

    protected String show() {
        StringBuilder sb =  new StringBuilder();
        sb.append(showThisBefore());
        // 获取全部子节点内容
        children.forEach(e -> sb.append(e.show()));
        sb.append(showThisAfter());
        return sb.toString();
    }
}

// 字母(由单个字符组成)
class Letter extends LetterComposite {
    private final char character;

    public Letter(char character) {
        this.character = character;
    }

    @Override
    protected String showThisBefore() {
        return String.valueOf(character);
    }
}

// 单词(由多个字母组成)
class Word extends LetterComposite {

    public Word(List<Letter> letterList) {
        letterList.forEach(this::add);
    }

    public Word(char... letters) {
        for (char c : letters) {
            this.add(new Letter(c));
        }
    }

    @Override
    protected String showThisBefore() {
        return " ";
    }
}

// 句子(由多个单词组成)
class Sentence extends LetterComposite {

    public Sentence(List<Word> words) {
        words.forEach(this::add);
    }

    @Override
    protected String showThisAfter() {
        return ".";
    }
}






















