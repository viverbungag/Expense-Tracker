package com.countable.ExpenseTracker.expense;

import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;

import java.util.Collection;

interface ExpenseService {

    Collection<Expense> getAllExpenses();
    void createExpense(CreateExpenseDto createExpenseDto);
}
