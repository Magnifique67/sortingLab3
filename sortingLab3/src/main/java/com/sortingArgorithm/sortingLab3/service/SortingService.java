package com.sortingArgorithm.sortingLab3.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortingService {

    public List<Integer> heapSort(List<Integer> data) {
        Integer[] arr = data.toArray(new Integer[0]);
        heapSort(arr);
        return List.of(arr);
    }

    public List<Integer> quickSort(List<Integer> data) {
        Integer[] arr = data.toArray(new Integer[0]);
        quickSort(arr, 0, arr.length - 1);
        return List.of(arr);
    }

    public List<Integer> mergeSort(List<Integer> data) {
        if (data.size() <= 1) {
            return data;
        }

        int mid = data.size() / 2;
        List<Integer> left = mergeSort(data.subList(0, mid));
        List<Integer> right = mergeSort(data.subList(mid, data.size()));

        return merge(left, right);
    }

    public List<Integer> radixSort(List<Integer> data) {
        if (data.isEmpty()) return data;

        int max = data.stream().max(Integer::compareTo).get();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            data = countingSort(data, exp);
        }
        return data;
    }

    public List<Integer> bucketSort(List<Integer> data) {
        if (data.isEmpty()) return data;

        int max = data.stream().max(Integer::compareTo).get();
        int min = data.stream().min(Integer::compareTo).get();
        int bucketCount = max / 10 - min / 10 + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int num : data) {
            buckets.get((num - min) / 10).add(num);
        }

        data.clear();
        for (List<Integer> bucket : buckets) {
            bucket.sort(null);
            data.addAll(bucket);
        }

        return data;
    }

    // Helper methods for sorting algorithms

    private void heapSort(Integer[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private void heapify(Integer[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    private void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private List<Integer> merge(List<Integer> left, List<Integer> right) {
        int leftIndex = 0, rightIndex = 0;
        List<Integer> merged = new ArrayList<>();

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                merged.add(left.get(leftIndex++));
            } else {
                merged.add(right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        return merged;
    }

    private List<Integer> countingSort(List<Integer> data, int exp) {
        int n = data.size();
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            count[(data.get(i) / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(data.get(i) / exp) % 10] - 1] = data.get(i);
            count[(data.get(i) / exp) % 10]--;
        }
        return Arrays.stream(output).boxed().collect(Collectors.toList());
    }
}
