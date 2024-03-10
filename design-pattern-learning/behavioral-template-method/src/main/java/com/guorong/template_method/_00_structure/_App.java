package com.guorong.template_method._00_structure;

class _App {
    public static void main(String[] args) {
        AbstractClass clazz = new ConcreteClass();
        clazz.templateMethod();
    }
}
