package com.university;

import com.university.sort.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static final int SIZE = 10;
    public static final int FROM = 1;
    public static final int TO = 100;

    public static void main(String[] args) {
        List<Integer> integers = randomIntegerList(SIZE, FROM, TO);
        System.out.println(integers);
        List<Integer> sortedList = CountingSort.sort(integers);
        System.out.println(sortedList);

        sortedList = LibrarySort.sort(integers);
        System.out.println(sortedList);

        sortedList = MergeSort.sort(integers);
        System.out.println(sortedList);

        sortedList = MSDRadixSort.sort(integers);
        System.out.println(sortedList);

        sortedList = MultiPivotQuickSort.sort(integers);
        System.out.println(sortedList);
        sortedList = new IntroSort(integers).sort();
        System.out.println(sortedList);
    }

    public static List<Integer> randomIntegerList(int size, int from, int to) {
        Random random = new Random();
        return random.ints(size, from, to)
                .boxed()
                .collect(Collectors.toList());
    }
}
