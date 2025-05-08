package com.guorong.tree.impl;

import com.guorong.tree.IAVLTree;

import java.util.*;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

    /**
     * 根节点指针
     */
    private AVLNode root;

    /**
     * AVL树中的元素个数
     */
    private int size;
    /**
     * 元素比较器
     */
    private final Comparator<? super T> comparator;

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean insert(T element) {
        if (element == null) {
            return false;
        }
        int oldSize = size;
        root = insertRec(root, element);
        return size > oldSize;
    }

    /**
     * 递归插入元素并平衡树。
     *
     * @param node    当前节点
     * @param element 要插入的元素
     * @return 插入并平衡后的节点
     */
    private AVLNode insertRec(AVLNode node, T element) {
        if (node == null) { // 生成新节点
            size++;
            return new AVLNode(element);
        }
        int compare = compare(element, node.value);
        if (compare < 0) { // 往左侧插入
            node.left = insertRec(node.left, element);
        } else if (compare > 0) { // 往右侧插入
            node.right = insertRec(node.right, element);
        } else { // 重复值不插入
            return node;
        }
        // 更新树高度
        updateHeight(node);
        // 节点平衡处理
        node = balanceNode(node);
        return node;
    }

    /**
     * 平衡节点
     *
     * @param node 需要调整的节点
     * @return 返回调整后的根节点
     */
    private AVLNode balanceNode(AVLNode node) {
        if (node == null) {
            return null;
        }
        // 平衡因子
        int balance = getBalanceFactor(node);
        int leftBalance = getBalanceFactor(node.left);
        int rightBalance = getBalanceFactor(node.right);

        // 四种失衡情况
        AVLNode newRoot = node;
        // 1. LL情况: 当前节点右旋
        // 节点A的平衡因子为+2（左子树比右子树高2）。A的左子节点（B）的平衡因子为+1（左重）或0（平衡）。
        if (balance == 2 && leftBalance >= 0) {
            newRoot = rightRotate(node);
        }
        // 2. LR情况: 先左节点左旋，然后当前节点右旋
        // 节点A的平衡因子为+2（左重）。A的左子节点（B）的平衡因子为-1（右重）。
        if (balance == 2 && leftBalance == -1) {
            node.left = leftRotate(node.left);
            newRoot = rightRotate(node);
        }
        // 3. RR情况：当前节点左旋
        // 节点A的平衡因子为-2（右子树比左子树高2）。A的右子节点（B）的平衡因子为-1（右重）或0（平衡）。
        if (balance == -2 && rightBalance <= 0) {
            newRoot = leftRotate(node);
        }
        // 4. RL情况: 先右节点右旋，然后当前节点左旋
        // 节点A的平衡因子为-2（右重）。A的右子节点（B）的平衡因子为+1（左重）。
        if (balance == -2 && rightBalance == 1) {
            node.right = rightRotate(node.right);
            newRoot = leftRotate(node);
        }
        return newRoot;
    }

    /**
     * 对给定的AVL树节点进行左旋转，以恢复树的平衡。
     * 当右子树的高度大于左子树（平衡因子为-2）时，使用此操作。
     *
     * @param root 需要旋转的子树的根节点
     * @return 旋转后新的根节点
     * @throws IllegalArgumentException 如果根节点或其右子节点为空
     */
    private AVLNode leftRotate(AVLNode root) {
        if (root == null || root.right == null) {
            throw new IllegalArgumentException("无法执行左旋：根节点或右子节点为空");
        }
        // 1.确认新的根节点(当前根节点的右子节点)
        AVLNode newRoot = root.right;

        // 2. 保存新根节点的左子树(将成为旧根节点的右子树)
        AVLNode newRootLeftSubtree = newRoot.left;

        // 3. 执行左旋
        // - 将旧根节点设置为新根节点的左子节点
        newRoot.left = root;
        // - 将新根节点的左子树设置为旧根节点的右子树
        root.right = newRootLeftSubtree;

        // 4. 更新受影响节点的高度
        // - 先更新旧根节点的高度，因为它的新子节点已更改
        updateHeight(root);
        // - 再更新新根节点的高度，因为它依赖旧根节点的高度
        updateHeight(newRoot);

        // 5. 返回左旋后的子树的新根节点
        return newRoot;
    }

    /**
     * 对给定的AVL树节点进行右旋转，以恢复树的平衡。
     * 当左子树的高度大于右子树（平衡因子为2）时，使用此操作。
     *
     * @param root 需要旋转的子树的根节点
     * @return 旋转后新的根节点
     * @throws IllegalArgumentException 如果根节点或其左子节点为空
     */
    private AVLNode rightRotate(AVLNode root) {
        if (root == null || root.left == null) {
            throw new IllegalArgumentException("无法执行右旋转：根节点或左子节点为空");
        }
        // 1. 确定新的根节点
        AVLNode newRoot = root.left;
        // 2. 保存新根节点的右子树
        AVLNode newRootRightSubtree = newRoot.right;
        // 3. 执行右旋
        // - 将旧根节点设置为新根节点的右子树
        newRoot.right = root;
        // - 将新根节点的右子树设置为旧根节点的左子树
        root.left = newRootRightSubtree;
        // 4. 更新树高度
        updateHeight(root);
        updateHeight(newRoot);
        // 5. 返回右旋后的新根节点
        return newRoot;
    }

    /**
     * 更新节点的高度。
     *
     * @param node 要更新的节点
     */
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    /**
     * 获取节点高度
     *
     * @param node 目标节点
     * @return 返回节点高度
     */
    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 计算节点的平衡因子
     *
     * @param node 目标节点
     * @return 返回节点的平衡因子
     */
    private int getBalanceFactor(AVLNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public boolean remove(T element) {
        int oldSize = size;
        root = removeRec(root, element);
        return oldSize > size;
    }

    /**
     * 递归删除元素并平衡
     *
     * @param node    当前节点
     * @param element 要删除的元素
     * @return 删除并平衡后的节点
     */
    private AVLNode removeRec(AVLNode node, T element) {
        if (element == null) {
            throw new IllegalArgumentException("element is not null");
        }
        if (node == null) { // 找到末尾未找到返回null
            return null;
        }
        int compare = compare(element, node.value);
        if (compare < 0) { // 在左侧继续查找
            node.left = removeRec(node.left, element);
        } else if (compare > 0) { // 在右侧继续查找
            node.right = removeRec(node.right, element);
        } else { // 找到节点，执行删除
            size--;
            if (node.left == null) { // 返回当前节点的右子节点，作为上一个节点的子节点
                return node.right;
            } else if (node.right == null) { // 返回当前节点的左子节点，作为上一个节点的子节点
                return node.left;
            }
            // 左右子节点都存在，使用右侧子树，最小的节点代替
            AVLNode mindNode = findMindNode(node.right);
            node.value = mindNode.value;
            // 再递归删除，交换后的删除值，已经没有左节点了
            node.right = removeRec(node.right, mindNode.value);
        }
        // 更新高度
        updateHeight(node);
        // 调整平衡性
        return balanceNode(node);
    }

    /**
     * 使用迭代删除节点
     *
     * @param node    从当前节点查找删除
     * @param element 要删除的元素
     * @return 删除成功返回true，否则返回false
     */
    private boolean removeIter(AVLNode node, T element) {
        if (node == null || element == null) {
            return false;
        }
        // 栈用来存储父节点
        LinkedList<AVLNode> stack = new LinkedList<>();

        // 循环查找元素
        AVLNode parent = null;
        AVLNode current = node;
        while (current != null) {
            int compare = compare(element, current.value);
            if (compare == 0) {
                break;
            }
            parent = current;
            current = compare < 0 ? current.left : current.right;
            // 入栈父节点
            stack.push(parent);
        }

        // 未找到元素
        if (current == null) {
            return false;
        }

        // 移除找到的节点
        // 情况1：有左右子节点
        if (current.left != null && current.right != null) {
            // 找到有右子树的最小节点
            AVLNode minParent = current;
            AVLNode minNode = current.right;
            // 父亲节点入栈
            stack.push(minParent);
            while (minNode.left != null) {
                minParent = minNode;
                minNode = minNode.left;
                // 父节点入栈
                stack.push(minParent);
            }
            // 替换值并删除最小节点
            current.value = minNode.value;
            if (minNode == current.right) { // node.right就是最小节点
                current.right = minNode.right;
            } else {
                minParent.left = minNode.right;
            }
        }
        // 情况2：没有子节点，或者只有一个子节点
        else {
            // 选出一个子节点，有么是left，要么是right，要么是null
            AVLNode child = current.left != null ? current.left : current.right;
            if (current == this.root) {
                this.root = child;
            } else if (current == parent.left) {
                parent.left = child;
            } else if (current == parent.right) {
                parent.right = child;
            }
        }

        // 调整移除节点的全部父节点平衡性
        while (!stack.isEmpty()) {
            AVLNode rotateNode = stack.pop();
            // 调整节点平衡
            AVLNode newNode = balanceNode(rotateNode);
            // 未发生旋转更新高度
            if (rotateNode == newNode) {
                updateHeight(newNode);
            }
            if (stack.isEmpty()) {
                root = newNode;
            } else {
                // 关联调整平衡后的新节点
                AVLNode parentNode = stack.peek();
                if (rotateNode == parentNode.left) {
                    parentNode.left = newNode;
                } else {
                    parentNode.right = newNode;
                }
            }
        }

        // 元素个数递减
        size--;
        return true;
    }

    /**
     * 查找当前节点的最小的节点
     *
     * @param node 当前查找的起始节点
     * @return 返回查找到的最小节点
     */
    private AVLNode findMindNode(AVLNode node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }


    @Override
    public boolean contains(T element) {
        return findNodeRec(root, element) != null;
    }

    /**
     * 递归查找元素节点
     *
     * @param root    要查找的子树根节点
     * @param element 查找元素值
     * @return 如果找到元素返回节点，否则返回null
     */
    private AVLNode findNodeRec(AVLNode root, T element) {
        if (element == null) {
            throw new IllegalArgumentException("element is not null");
        }
        if (root == null) { // 找到最后没有节点了
            return null;
        }
        // 判断当前元素是否是当前值
        int compare = compare(element, root.value);
        if (compare == 0) {
            return root;
        } else if (compare < 0) {
            return findNodeRec(root.left, element);
        } else {
            return findNodeRec(root.right, element);
        }
    }

    @Override
    public int getHeight() {
        return isEmpty() ? 0 : root.height;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public List<T> preOrderTraversal() {
        ArrayList<T> list = new ArrayList<>(size);
        preOrderTraversal(list, root);
        return list;
    }

    /**
     * 先序遍历递归函数
     *
     * @param list 存储遍历后的元素
     * @param node 要遍历的节点
     */
    private void preOrderTraversal(List<T> list, AVLNode node) {
        if (node == null) {
            return;
        }
        list.add(node.value);
        if (node.left != null) {
            preOrderTraversal(list, node.left);
        }
        if (node.right != null) {
            preOrderTraversal(list, node.right);
        }
    }

    @Override
    public List<T> inOrderTraversal() {
        ArrayList<T> list = new ArrayList<>(size);
        inOrderTraversal(list, root);
        return list;
    }

    /**
     * 中序遍历递归函数
     *
     * @param list 存储中序遍历的元素
     * @param node 遍历的根节点
     */
    private void inOrderTraversal(List<T> list, AVLNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inOrderTraversal(list, node.left);
        }
        list.add(node.value);
        if (node.right != null) {
            inOrderTraversal(list, node.right);
        }
    }

    @Override
    public List<T> postOrderTraversal() {
        ArrayList<T> list = new ArrayList<>(size);
        postOrderTraversal(list, root);
        return list;
    }

    /**
     * 候选遍历递归函数
     *
     * @param list 存储递归遍历的元素
     * @param node 遍历的根节点
     */
    private void postOrderTraversal(List<T> list, AVLNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            postOrderTraversal(list, node.left);
        }
        if (node.right != null) {
            postOrderTraversal(list, node.right);
        }
        list.add(node.value);
    }

    @Override
    public List<T> levelOrderTraversal() {
        ArrayList<T> list = new ArrayList<>(size);
        if (isEmpty()) {
            return list;
        }
        // 按层级遍历节点
        Queue<AVLNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            AVLNode node = queue.poll();
            // 左子节点入队
            Optional.ofNullable(node.left).ifPresent(queue::offer);
            // 右子节点入队
            Optional.ofNullable(node.right).ifPresent(queue::offer);
            // 加入遍历集合返回
            list.add(node.value);
        }
        return list;
    }

    @Override
    public Comparator<? super T> getComparator() {
        return comparator;
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean isValidAVLTree() {
        return isValidAVLTreeRec(root);
    }

    /**
     * 递归验证树是否为有效的AVL树
     *
     * @param root 验证AVL的树根节点
     * @return 若为有效AVL树返回true，否则返回false
     */
    private boolean isValidAVLTreeRec(AVLNode root) {
        if (root == null) {
            return true;
        }
        AVLNode leftNode = root.left;
        AVLNode rightNode = root.right;
        // 验证是否符合二叉搜索树的规则: 左子树<根节点<右子树
        if (leftNode != null && compare(leftNode.value, root.value) >= 0) {
            return false;
        }
        if (rightNode != null && compare(rightNode.value, root.value) <= 0) {
            return false;
        }
        // 判断节点平衡性[-1,1]
        int balanceFactor = getBalanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        // 判断root节点的高度(当前节点高度应该左右子树高度的最大值+1)
        int expectedHeight = Math.max(getHeight(leftNode), getHeight(rightNode)) + 1;
        if (root.height != expectedHeight) {
            return false;
        }
        // 递归验证左右子树
        return isValidAVLTreeRec(leftNode) && isValidAVLTreeRec(rightNode);
    }


    /**
     * 比较两个元素大小
     *
     * @param a 前一个元素
     * @param b 后一个元素
     * @return 相等返回0，a大于b返回1，a小于b返回-1
     */
    private int compare(T a, T b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("a or b cannot is null");
        }
        int compare = a.compareTo(b);
        if (comparator != null) {
            compare = comparator.compare(a, b);
        }
        return compare;
    }


    // 树节点定义
    private class AVLNode {
        private T value;
        private int height;
        private AVLNode left;
        private AVLNode right;

        public AVLNode(T value) {
            this.value = value;
            this.height = 1; // 默认节点高度为1
        }
    }
}
