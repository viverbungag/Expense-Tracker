package com.countable.ExpenseTracker.expense;

import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;
import com.countable.ExpenseTracker.expense.dto.TotalPerWeekExpenseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/expenses")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public Collection<Expense> getAllExpenses(@RequestParam("searchDescription") String searchDescription){
        if (searchDescription != null){
            return expenseService.getAllExpenses(searchDescription);
        }

        return expenseService.getAllExpenses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createExpense(@RequestBody CreateExpenseDto createExpenseDto){
        expenseService.createExpense(createExpenseDto);
    }

    @GetMapping("/totalAmountPerWeek/{year}")
    public Collection<TotalPerWeekExpenseDto> getTotalAmountPerWeek(@PathVariable int year){
        return expenseService.getTotalAmountPerWeek(year);
    }

    @GetMapping("/years")
    public Collection<Integer> getAllYears(){
        // This endpoint is used by the frontend so the user can select a year to view the expenses
        return expenseService.getAllYears();
    }
}
