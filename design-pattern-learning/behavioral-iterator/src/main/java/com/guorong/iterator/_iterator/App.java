package com.guorong.iterator._iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guorong
 */
class App {
    public static void main(String[] args) {
        TreasureChest treasureChest = new TreasureChest();
        Iterator<Item> iterator = treasureChest.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

enum ItemType {
    ANY, RING, WEAPON, POTION,;
}

class Item {
    private ItemType type;
    private final String name;

    public Item(ItemType type, String name) {
        this.type = type;
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("type: %s name: %s", type.name(), name);
    }
}

interface Iterator<T> {
    boolean hasNext();

    T next();
}

// 百宝箱
class TreasureChest {

    private final List<Item> items;

    public TreasureChest() {
        items = new ArrayList<>();
        items.add(new Item(ItemType.POTION, "Potion of courage"));
        items.add(new Item(ItemType.RING, "Ring of shadows"));
        items.add(new Item(ItemType.POTION, "Potion of wisdom"));
        items.add(new Item(ItemType.POTION, "Potion of blood"));
        items.add(new Item(ItemType.WEAPON, "Sword of silver +1"));
        items.add(new Item(ItemType.POTION, "Potion of rust"));
        items.add(new Item(ItemType.POTION, "Potion of healing"));
        items.add(new Item(ItemType.RING, "Ring of armor"));
    }

    // 获取迭代器
    public Iterator<Item> iterator() {
        return new TreasureChestIterator();
    }

    // 获取全部项目
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    // 实际迭代器
    private class TreasureChestIterator implements Iterator<Item> {

        // 光标
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < items.size();
        }

        @Override
        public Item next() {
            return items.get(cursor++);
        }
    }
}



















