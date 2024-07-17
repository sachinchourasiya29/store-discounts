package com.example.storediscount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillRequest {
    @NotNull
    @Valid
    private User user;

    @NotEmpty
    @Valid
    private List<Item> items;
}
