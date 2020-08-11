package day02.mergesort;

import java.util.*;

public class MergeSortTest {

    static int nums[] = { 8, 2, 8, 10, 7, 1, 9, 4, 15 };
    static int tmp[] = new int[nums.length];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nums));
        mergeSort(0,nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }

    // divide
    static void mergeSort(int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    // merge
    static void merge(int start, int mid, int end) {
        // idx : tmp index
        int ptr1 = start;
        int ptr2 = mid + 1;
        int idx = start;
        while (ptr1 <= mid && ptr2 <= end) {
            if (nums[ptr1] < nums[ptr2]) {
                tmp[idx++] = nums[ptr1++];
            } else {
                tmp[idx++] = nums[ptr2++];
            }
        }
        while (ptr1 <= mid) {
            tmp[idx++] = nums[ptr1++];
        }
        while (ptr2 <= end) {
            tmp[idx++] = nums[ptr2++];
        }
        for (int i = start;i <= end;i++) {
            nums[i] = tmp[i];
        }
    }
}