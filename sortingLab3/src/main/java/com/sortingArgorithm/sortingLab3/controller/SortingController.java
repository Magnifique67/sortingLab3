package com.sortingArgorithm.sortingLab3.controller;

import com.sortingArgorithm.sortingLab3.service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SortingController {

    private final SortingService sortingService;

    @Autowired
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @GetMapping("/")
    public String index() {
        return "sort";
    }

    @PostMapping("/sort")
    public String sort(@RequestParam("data") String data,
                       @RequestParam("algorithm") String algorithm,
                       Model model) {
        List<Integer> dataList = Arrays.stream(data.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> sortedData;
        switch (algorithm) {
            case "heap":
                sortedData = sortingService.heapSort(dataList);
                break;
            case "quick":
                sortedData = sortingService.quickSort(dataList);
                break;
            case "merge":
                sortedData = sortingService.mergeSort(dataList);
                break;
            case "radix":
                sortedData = sortingService.radixSort(dataList);
                break;
            case "bucket":
                sortedData = sortingService.bucketSort(dataList);
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }

        model.addAttribute("sortedData", sortedData);
        return "sort";
    }
}
