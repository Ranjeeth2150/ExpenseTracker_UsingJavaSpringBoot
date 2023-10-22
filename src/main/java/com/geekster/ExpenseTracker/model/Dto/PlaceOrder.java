package com.geekster.ExpenseTracker.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrder {

    private LocalDate orderDate;
    private Integer productId;

}