package com.countable.ExpenseTracker.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JpaExpenseRepository extends JpaRepository<Expense, Long>{

    @Query("FROM Expense e WHERE e.description ILIKE %:searchDescription%")
    List<Expense> findAllByDescriptionContaining(String searchDescription);
}
