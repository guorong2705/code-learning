package com.guorong.template_method._01_impl;

/**
 * 模板方法: 冲泡饮料算法
 */
class App {
    public static void main(String[] args) {
        // 茶饮料
        Beverage tea = new Tea();
        tea.prepareRecipe();
        System.out.println("------------------------------------------");
        // 咖啡饮料
        Beverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}

/**
 * 饮料抽象类
 */
abstract class Beverage {

    /**
     * 模板方法(使用final修饰，防止子类重写)
     * 冲泡饮料的过程大致都是差不多的
     */
    public final void prepareRecipe() {
        boilWater(); // 煮沸水
        brew(); // 冲泡
        pourInCup(); // 把饮料倒入杯子
        if (isAddCondiment()) {
            addCondiment(); // 添加配料
        }
    }

    /**
     * 煮沸水
     */
    public void boilWater() {
        System.out.println("煮沸水....");
    }

    /**
     * 冲泡(钩子方法)
     */
    protected abstract void brew();

    /**
     * 添加配料(钩子方法)
     */
    protected abstract void addCondiment();

    /**
     * 把饮料倒入杯子
     */
    public void pourInCup() {
        System.out.println("把饮料倒入杯子....");
    }

    /**
     * 定义是否添加配料(钩子方法)
     * @return
     */
    public boolean isAddCondiment() {
        return true;
    }
}

/**
 * 咖啡
 */
class Coffee extends Beverage {
    @Override
    public void brew() {
        System.out.println("冲咖啡....");
    }
    @Override
    public void addCondiment() {
        System.out.println("加糖和牛奶....");
    }
}

/**
 * 茶叶
 */
class Tea extends Beverage {
    @Override
    public void brew() {
        System.out.println("泡茶....");
    }
    @Override
    public void addCondiment() {
        System.out.println("添加柠檬....");
    }
}