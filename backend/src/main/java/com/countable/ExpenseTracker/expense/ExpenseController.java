package com.countable.ExpenseTracker.expense;

import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public Collection<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createExpense(@RequestBody CreateExpenseDto createExpenseDto){
        expenseService.createExpense(createExpenseDto);
    }
}
