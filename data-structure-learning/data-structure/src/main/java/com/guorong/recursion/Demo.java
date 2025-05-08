package com.guorong.recursion;

import java.util.Stack;

public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        displayArrayStack(arr, 0, arr.length - 1);
    }

    private static int sumOf(int n) {
        if (n == 1) {
            return 1;
        }
        return sumOf(n - 1) + n;
    }

    private static void displayArray(int[] arr, int first, int last) {
        System.out.print(arr[first] + " ");
        if (first < last) {
            displayArray(arr, first + 1, last);
        }
    }

    private static void displayArray2(int[] arr, int first, int last) {
        if (first == last) {
            System.out.print(arr[first] + " ");
        } else {
            int mid = first + ((last - first) >> 1);
            displayArray2(arr, first, mid);
            displayArray2(arr, mid + 1, last);
        }
    }

    private static class Record {
        private int first;
        private int last;

        public Record(int first, int last) {
            this.first = first;
            this.last = last;
        }
    }

    private static void displayArrayStack(int[] array, int first, int last) {
        Stack<Record> stack = new Stack<>();
        stack.push(new Record(first, last));
        while (!stack.isEmpty()) {
            Record record = stack.pop();
            first = record.first;
            last = record.last;
            if (first == last) {
                System.out.print(array[first] + " ");
            } else {
                int mid = first + ((last - first) >> 1);
                displayArrayStack(array, first, mid);
                displayArrayStack(array, mid + 1, last);
            }
        }
    }
}


