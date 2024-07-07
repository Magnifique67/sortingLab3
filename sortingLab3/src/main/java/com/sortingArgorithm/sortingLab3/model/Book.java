package com.sortingArgorithm.sortingLab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String publishedDate;
    private String isbn;
    private boolean availability;
}
