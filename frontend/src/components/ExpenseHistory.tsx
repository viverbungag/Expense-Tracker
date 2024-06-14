'use client';

import { fetchAllExpenses } from '@/app/utilities/fetch/expenses';
import React, { useEffect } from 'react';

const ExpenseHistory = () => {
  useEffect(() => {
    // Fetch expense history from the server
    const fetchHistory = async () => {
      const expenses = await fetchAllExpenses('co');
      // const data = await response.json();
      console.log(expenses);
    };
    void fetchHistory();
  }, []);

  return <section></section>;
};

export default ExpenseHistory;
