package com.example.storediscount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @NotNull
    private String name;

    @Min(0)
    private double price;

    private boolean isGrocery;
}