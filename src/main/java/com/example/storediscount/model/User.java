package com.example.storediscount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String type; // EMPLOYEE, AFFILIATE, CUSTOMER

    @NotNull
    private LocalDate joinedDate;
}



