package com.countable.ExpenseTracker.expense;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final JpaExpenseRepository expenseRepository;

    @Override
    public Collection<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
