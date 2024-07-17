package com.example.storediscount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {
    private double totalAmount;
    private double percentageDiscount;
    private double flatDiscount;
    private double netPayableAmount;
}
