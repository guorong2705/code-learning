package com.guorong.iterator._two_way_iterator;

import java.util.ArrayList;
import java.util.List;

class DinnerMenu implements IMenu{

    /**
     * 使用数组存储菜单
     */
    private final List<MenuItem> storage;

    public DinnerMenu() {
        storage = new ArrayList<>();
        addItem("排骨煲仔饭", false, 13.34);
        addItem("农家小炒肉饭", false, 12.20);
        addItem("苦瓜炒蛋饭", true, 10.56);
        addItem("手撕包菜饭", true, 9.34);
    }

    public void addItem(String name, boolean vegetarian, double price) {
        storage.add(new MenuItem(name, vegetarian, price));
    }

    // 获取双向迭代器
    @Override
    public MyListIterator<MenuItem> listIterator() {
        return new DinnerMenuItr();
    }

    // 双向迭代器实现
    private class DinnerMenuItr implements MyListIterator<MenuItem> {

        private final List<MenuItem> itrStorage;

        private int cursor = 0;

        public DinnerMenuItr() {
            itrStorage = new ArrayList<>(storage);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public MenuItem previous() {
            return itrStorage.get(--cursor);
        }

        @Override
        public boolean hasNext() {
            return cursor < itrStorage.size();
        }

        @Override
        public MenuItem next() {
            return itrStorage.get(cursor++);
        }
    }


}
