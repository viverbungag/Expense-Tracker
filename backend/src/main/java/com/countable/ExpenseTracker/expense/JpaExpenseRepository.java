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

    @Query(value = """
            SELECT TO_CHAR(DATE_TRUNC('week', date), 'YYYY-MM-DD') as start_date,
                TO_CHAR(DATE_TRUNC('week', date) + interval '6 days', 'YYYY-MM-DD') as end_date,
                SUM(amount) as total_amount 
            FROM expense 
            WHERE EXTRACT(YEAR from date) = :year 
            GROUP BY DATE_TRUNC('week', date) 
            ORDER BY start_date;
            """, nativeQuery = true)
    List<Object[]> getTotalAmountPerWeek(int year);
}
