package com.countable.ExpenseTracker.expense;

import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;
import com.countable.ExpenseTracker.expense.dto.TotalPerWeekExpenseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final JpaExpenseRepository expenseRepository;

    @Override
    public Collection<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Collection<Expense> getAllExpenses(String searchDescription) {
        return expenseRepository.findAllByDescriptionContaining(searchDescription);
    }

    @Override
    @Transactional
    public void createExpense(CreateExpenseDto createExpenseDto) {
        Expense expense = Expense.from(createExpenseDto);
        expenseRepository.save(expense);
    }

    @Override
    public Collection<TotalPerWeekExpenseDto> getTotalAmountPerWeek(int year) {
        Collection<TotalPerWeekExpenseDto> totalPerWeekExpenseDtos = new ArrayList<>();

        // Need to do this because the query returns an Object[] and we need to convert it to a DTO. The Spring Data JPA cannot automatically convert to an entity that is outside of its scope
        for (Object[] objects : expenseRepository.getTotalAmountPerWeek(year)) {
            LocalDate startDate = LocalDate.parse((String) objects[0]);
            LocalDate endDate = LocalDate.parse((String) objects[1]);
            BigDecimal totalAmount = (BigDecimal) objects[2];

            TotalPerWeekExpenseDto totalPerWeekExpenseDto = new TotalPerWeekExpenseDto(startDate, endDate, totalAmount);
            totalPerWeekExpenseDtos.add(totalPerWeekExpenseDto);
        }

        return totalPerWeekExpenseDtos;
    }

    @Override
    public List<Integer> getAllYears() {
        return expenseRepository.getAllYears();
    }
}
