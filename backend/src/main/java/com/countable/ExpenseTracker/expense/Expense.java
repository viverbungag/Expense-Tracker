package com.countable.ExpenseTracker.expense;


import com.countable.ExpenseTracker.expense.dto.CreateExpenseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    @Setter
    @NonNull
    private String description;

    @Column(name = "date")
    @Setter
    @NonNull
    private LocalDate date;

    @Column(name = "amount")
    @Setter
    @NonNull
    private Float amount;

    public static Expense from(CreateExpenseDto createExpenseDto) {
        return new Expense(createExpenseDto.description(), createExpenseDto.date(), createExpenseDto.amount());
    }
}
