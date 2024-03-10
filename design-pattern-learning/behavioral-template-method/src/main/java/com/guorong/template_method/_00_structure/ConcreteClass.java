package com.guorong.template_method._00_structure;


class ConcreteClass extends AbstractClass {

    private static final String CLASS_NAME = ConcreteClass.class.getSimpleName();

    @Override
    protected void operation1() {
        System.out.println(CLASS_NAME + " ...operation1");
    }

    @Override
    protected void operation2() {
        System.out.println(CLASS_NAME + " ...operation2");
    }
}
