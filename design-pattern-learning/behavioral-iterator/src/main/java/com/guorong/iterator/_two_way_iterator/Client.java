package com.guorong.iterator._two_way_iterator;

/**
 * 双向迭代器演示
 */
class Client {

    public static void main(String[] args) {
        DinnerMenu dinnerMenu = new DinnerMenu();
        MyListIterator<MenuItem> listIterator = dinnerMenu.listIterator();
        if (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous() + "-----------");
        }

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        System.out.println("============================");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

    }


}
