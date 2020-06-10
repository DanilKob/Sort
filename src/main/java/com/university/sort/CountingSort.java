package com.university.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CountingSort {
    public static List<Integer> sort(List<Integer> list) {
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        max.orElseThrow(() -> new RuntimeException("No max element found"));
        int initialCapacity = max.get() + 1;
        Integer[] counter = new Integer[initialCapacity];
        List<Integer> result = new LinkedList<>();
        for (Integer integer : list) {
            Integer counterIndex = counter[integer];
            if (counterIndex == null) {
                counterIndex = 0;
            }
            counterIndex = counterIndex + 1;
            counter[integer] = counterIndex;
        }

        for (int i = 0; i < counter.length; i++) {
            Integer repeats = counter[i];
            if (repeats != null) {
                for (int repeat = 0; repeat < repeats; repeat++) {
                    result.add(i);
                }
            }

        }

        return result;
    }
}

