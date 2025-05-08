package demo;

import com.guorong.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 将后缀表达式转换为二叉树
 */
class BinaryTreeConvert {

    private static class Node {
        String value;
        Node left;
        Node right;
        public Node(String value) {
            this.value = value;
        }
    }

    Node convertExpressPost(String express) {
        if (express == null || express.isEmpty()) {
            return null;
        }
        List<String> operatorList = new ArrayList<>(Arrays.asList("+","-","*","/"));
        Stack<Node> stack = new Stack<>();
        for (char chs : express.toCharArray()) {
            Node node = new Node(String.valueOf(chs));
            if (operatorList.contains(String.valueOf(chs))) {
                node.right = stack.pop();
                node.left = stack.pop();
                stack.push(node);
            }else {
                stack.push(node);
            }
        }
        return stack.pop();
    }
    @Test
    void testConvertExpressPost() {
        String express = "ab+cde+**";
        Node node = convertExpressPost(express);
        System.out.println(111);
    }
}
