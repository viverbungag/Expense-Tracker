'use client';

import { fetchAllExpenses } from '@/app/utilities/fetch/expenses';
import moneyFormatter from '@/app/utilities/fetch/moneyFormatter';
import { useQuery } from '@tanstack/react-query';
import dayjs from 'dayjs';
import React, { useState } from 'react';

const ExpenseHistory = () => {
  const [searchDescription, setSearchDescription] = useState('');
  const [searchDiscriptionParamsValues, setSearchDiscriptionParamsValues] =
    useState('');

  const expensesQuery = useQuery<Expense[]>({
    queryKey: ['expenses', { searchDiscriptionParamsValues }],
    queryFn: () => fetchAllExpenses(searchDiscriptionParamsValues),
  });

  const expenses = expensesQuery.data;

  return (
    <section className="flex items-center justify-center">
      <div className="w-[50rem] flex flex-col gap-2">
        <label className="input input-bordered flex items-center gap-2 w-[20rem] sm:self-end self-center">
          <input
            type="text"
            className="grow"
            placeholder="Search"
            onChange={(e) => setSearchDescription(e.target.value)}
            onKeyUp={async (event: React.KeyboardEvent<HTMLInputElement>) => {
              if (event.key === 'Enter') {
                setSearchDiscriptionParamsValues(searchDescription);
              }
            }}
          />
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 16 16"
            fill="currentColor"
            className="w-4 h-4 opacity-70 cursor-pointer hover:opacity-100"
            onClick={async () => {
              setSearchDiscriptionParamsValues(searchDescription);
            }}
          >
            <path
              fill-rule="evenodd"
              d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z"
              clip-rule="evenodd"
            />
          </svg>
        </label>
        <div className="overflow-x-auto h-[20rem]">
          <table className="table">
            <thead>
              <tr>
                <th></th>
                <th>Description</th>
                <th>Date</th>
                <th>Amount</th>
              </tr>
            </thead>
            <tbody>
              {expenses?.map((expense) => (
                <tr key={expense.id}>
                  <th>{expense.id}</th>
                  <td>{expense.description}</td>
                  <td>{dayjs(String(expense.date)).format('MMM DD, YYYY')}</td>
                  <td>{moneyFormatter(Number(expense.amount))}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </section>
  );
};

export default ExpenseHistory;
