package com.university.sort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MultiPivotQuickSort {

    public static List<Integer> sort(List<Integer> list) {
        int[] intArray = list.stream().mapToInt(Integer::intValue).toArray();
        sort(intArray, new AtomicInteger(0), new AtomicInteger(intArray.length - 1));
        return Arrays.stream(intArray).boxed().collect(Collectors.toList());
    }

    public static void sort(int[] arr, AtomicInteger low, AtomicInteger high) {
        if (low.get() < high.get()) {
            // lp means left pivot, and rp means right pivot.
            AtomicInteger lp = new AtomicInteger(0);
            AtomicInteger rp = new AtomicInteger(0);
            rp = partition(arr, low, high, lp);
            sort(arr, low, new AtomicInteger(lp.get() - 1));
            sort(arr, new AtomicInteger(lp.get() + 1), new AtomicInteger(rp.get() - 1));
            sort(arr, new AtomicInteger(rp.get() + 1), high);
        }
    }

    static void swap(int[] arr, int index1, int index2) {

        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    static AtomicInteger partition(int[] arr, AtomicInteger lowInt, AtomicInteger highInt, AtomicInteger lp) {
        int low = lowInt.get();
        int high = highInt.get();
        if (arr[low] > arr[high])
            swap(arr, low, high);

        // p is the left pivot, and q is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1, p = arr[low], q = arr[high];
        while (k <= g) {

            // if elements are less than the left pivot
            if (arr[k] < p) {
                swap(arr, k, j);
                j++;
            }

            // if elements are greater than or equal
            // to the right pivot
            else if (arr[k] >= q) {
                while (arr[g] > q && k < g)
                    g--;
                swap(arr, k, g);
                g--;
                if (arr[k] < p) {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // bring pivots to their appropriate positions.
        swap(arr, low, j);
        swap(arr, high, g);

        // returning the indices of the pivots.
        lp.set(j); // because we cannot return two elements
        // from a function.

        lowInt.set(low);
        highInt.set(high);
        return new AtomicInteger(g);
    }
}
