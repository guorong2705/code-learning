package com.guorong.heap;

/**
 * 最大堆：
 */
class MaxHeap {

    private int[] heap;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MaxHeap(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        this.heap = new int[capacity];
        this.capacity = capacity;
        this.size = 0;
    }


    /**
     * 插入元素
     *
     * @param value
     */
    public void insert(int value) {
        if (size >= capacity) {
            throw new RuntimeException("heap is full");
        }
        int currIdx = size;
        size++;
        heap[currIdx] = value;
        // 上浮操作
        siftUp(currIdx);
    }

    /**
     * 构建堆
     *
     * @param array
     */
    public void buildHeap(int[] array) {
        if (array == null) {
            throw new RuntimeException("array is null");
        }
        // 判断容量
        if (array.length > capacity - size) {
            throw new RuntimeException("heap is full");
        }
        // 添加入数组最少
        for (int i = 0; i < array.length; i++) {
            heap[size++] = array[i];
        }
        // 堆化:
        // 最后一个节点的索引是 size - 1，其父节点是 (size - 1 - 1) / 2 = (size - 2) / 2。
        // 因此，(size - 2) / 2 是最后一个非叶子节点的索引。叶子节点无需下沉（因为它们没有子节点），所以从最后一个非叶子节点开始即可。
        for (int i = (size - 2) / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 删除并返回堆顶元素
     *
     * @return
     */
    public int extract() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        int topIdx = 0;
        int topValue = heap[topIdx];
        heap[topIdx] = heap[--size]; // 将最后一个移到第一位置
        siftDown(topIdx); //下沉操作(非叶子节点)
        return topValue;
    }

    /**
     * 删除元素
     *
     * @param index
     */
    public int removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("invalid index");
        }
        int removeValue = heap[index];
        int endIdx = size - 1;
        size--;
        heap[index] = heap[endIdx];
        // 如果删除的是最后一个元素，无需调整
        if (index == endIdx) {
            return removeValue;
        }
        // 调整index位置的元素
        if (isSiftUp(index)) {
            siftUp(index);
        } else {
            siftDown(index);
        }
        return removeValue;
    }

    /**
     * 获取堆顶元素(不删除)
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        return heap[0];
    }


    /**
     * 获取元素个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 判断堆是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断是否为堆
     *
     * @return
     */
    public boolean isHeap() {
        for (int i = 0; i < size; i++) {
            // 判断是否大于左右孩子节点
            int leftChildIdx = leftChildIdx(i);
            if (leftChildIdx < size && heap[i] < heap[leftChildIdx]) {
                return false;
            }
            int rightChildIdx = rightChildIdx(i);
            if (rightChildIdx < size && heap[i] < heap[rightChildIdx]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取父节点索引
     *
     * @param index
     * @return
     */
    private int parentIdx(int index) {
        return (index - 1) / 2;
    }

    /**
     * 获取左子节点索引
     *
     * @param index
     * @return
     */
    private int leftChildIdx(int index) {
        return index * 2 + 1;
    }

    /**
     * 获取右子节点索引
     *
     * @param index
     * @return
     */
    private int rightChildIdx(int index) {
        return index * 2 + 2;
    }

    /**
     * 上浮操作，维护堆性质
     *
     * @param index
     */
    private void siftUp(int index) {
        while (index > 0 && heap[index] > heap[parentIdx(index)]) {
            swap(index, parentIdx(index));
            index = parentIdx(index);
        }
    }

    /**
     * 下沉操作，维护堆性质
     *
     * @param index
     */
    private void siftDown(int index) {
        int maxIdx = index;
        while (true) {
            int leftChildIdx = leftChildIdx(index);
            int rightChildIdx = rightChildIdx(index);
            // 获取三个节点中的最大值索引
            if (leftChildIdx < size && heap[leftChildIdx] > heap[maxIdx]) {
                maxIdx = leftChildIdx;
            }
            if (rightChildIdx < size && heap[rightChildIdx] > heap[maxIdx]) {
                maxIdx = rightChildIdx;
            }
            // 不需要下沉
            if (maxIdx == index) {
                break;
            }
            // 当前元素下沉
            swap(maxIdx, index);
            // 当前元素往下移动一个
            index = maxIdx;
        }

    }

    /**
     * 是否上浮
     * @param index
     * @return
     */
    private boolean isSiftUp(int index) {
        // 当前值比父亲值大上浮
        int parentValue = index == 0 ? Integer.MAX_VALUE : heap[parentIdx(index)];
        return heap[index] > parentValue;
    }


    /**
     * 交换指定索引的值
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        boolean flag = i < 0 || i >= size || j < 0 || j >= size;
        if (flag) {
            throw new IndexOutOfBoundsException("i or j out of bounds");
        }
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

}
