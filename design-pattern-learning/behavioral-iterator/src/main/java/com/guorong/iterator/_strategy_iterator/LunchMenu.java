package com.guorong.iterator._strategy_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author guorong
 * @date 2021-08-25
 */
class LunchMenu implements Iterable<MenuItem> {


    private List<MenuItem> storage = new ArrayList<>(16);

    public LunchMenu() {
        addItem("排骨煲仔饭", false, 13.34);
        addItem("农家小炒肉饭", false, 12.20);
        addItem("苦瓜炒蛋饭", true, 10.56);
        addItem("手撕包菜饭", true, 9.34);
    }


    public void addItem(String name, boolean vegetarian, double price) {
        storage.add(new MenuItem(name, vegetarian, price));
    }


    @Override
    public Iterator<MenuItem> iterator() {
        return new LunchMenuIterator();
    }


    // 迭代器
    private class LunchMenuIterator implements Iterator<MenuItem> {

        // 迭代器拥有一份数据
        private List<MenuItem> list = null;

        // 游标
        private int cursor = 0;

        public LunchMenuIterator() {
            list = new ArrayList<>();
            // 过滤掉小于10的
            list.addAll(storage.stream().filter(e -> e.getPrice() > 10).collect(Collectors.toList()));
        }

        @Override
        public boolean hasNext() {
            if (Objects.isNull(list) || cursor >= list.size()) {
                return false;
            }
            return true;
        }

        @Override
        public MenuItem next() {
            return list.get(cursor++);
        }
    }


}
