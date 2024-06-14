package com.countable.ExpenseTracker.expense.dto;

import java.time.LocalDate;

public record CreateExpenseDto(String description, LocalDate date, Float amount) {}
