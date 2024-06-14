package com.countable.ExpenseTracker.expense;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "expense")
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
    private Date date;

    @Column(name = "amount")
    @Setter
    @NonNull
    private Float amount;
}
