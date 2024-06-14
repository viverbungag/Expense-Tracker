package com.countable.ExpenseTracker.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TotalPerWeekExpenseDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;
}
