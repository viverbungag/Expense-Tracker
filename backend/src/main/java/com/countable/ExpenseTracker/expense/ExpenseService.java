package com.countable.ExpenseTracker.expense;

import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;
import com.countable.ExpenseTracker.expense.dto.TotalPerWeekExpenseDto;

import java.util.Collection;

interface ExpenseService {

    Collection<Expense> getAllExpenses();
    Collection<Expense> getAllExpenses(String searchDescription);
    void createExpense(CreateExpenseDto createExpenseDto);
    Collection<TotalPerWeekExpenseDto> getTotalAmountPerWeek(int year);
}
