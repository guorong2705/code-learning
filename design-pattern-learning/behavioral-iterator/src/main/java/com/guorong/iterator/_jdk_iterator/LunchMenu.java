package com.guorong.iterator._jdk_iterator;


import java.util.Iterator;

/**
 * 正餐菜单
 * @author guorong
 */
class LunchMenu implements Iterable<MenuItem>{

    /**
     * 最大菜单长度
     */
    private static final int MAX_MENU_LEN = 6;

    private int currentIndex = 0;

    /**
     * 使用数组存储菜单
     */
    private MenuItem[] menuItemArray = new MenuItem[MAX_MENU_LEN];

    public LunchMenu() {
        addItem("排骨煲仔饭", false, 13.34);
        addItem("农家小炒肉饭", false, 12.20);
        addItem("苦瓜炒蛋饭", true, 10.56);
        addItem("手撕包菜饭", true, 9.34);
    }


    public void addItem(String name, boolean vegetarian, double price){
        if (currentIndex >= MAX_MENU_LEN) {
            System.out.println("对不起，菜单项已经满了，无法添加菜单");
        }else {
            menuItemArray[currentIndex++] = new MenuItem(name,vegetarian,price);
        }
    }

    /**
     * 获取迭代器
     * @return
     */
    @Override
    public Iterator<MenuItem> iterator() {
        return new DinnerMenuIterator();
    }



    /**
     * 正餐迭代器(使用JDK自带的util包下的迭代器)
     */
    private class DinnerMenuIterator implements Iterator<MenuItem> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            if (cursor >= menuItemArray.length || menuItemArray[cursor] == null) {
                return false;
            }
            return true;
        }

        @Override
        public MenuItem next() {
            return menuItemArray[cursor++];
        }
    }


}
