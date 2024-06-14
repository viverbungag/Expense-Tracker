'use client';

import {
  fetchAllExpenseYears,
  fetchTotalExpensesPerWeek,
} from '@/app/utilities/fetch/expenses';
import moneyFormatter from '@/app/utilities/fetch/moneyFormatter';
import { useMutation, useQuery } from '@tanstack/react-query';
import dayjs from 'dayjs';
import React, { useEffect, useState } from 'react';
import { toast } from 'react-toastify';

const TotalExpensesPerWeekSection = () => {
  const [yearSelected, setYearSelected] = useState<Number>(0);

  const expenseYearsQuery = useQuery<Number[]>({
    queryKey: ['years'],
    queryFn: async () => await fetchAllExpenseYears(),
  });
  const expenseYears = expenseYearsQuery.data;

  useEffect(() => {
    if (expenseYears) setYearSelected(expenseYears[0]);
  }, [expenseYears]);

  const getTotalExpensesPerWeekMutation = useMutation({
    mutationFn: async (yearSelected: Number) =>
      await fetchTotalExpensesPerWeek(yearSelected),
    onSuccess: () => {
      toast.success('Total expenses per week calculated successfully');
    },
  });

  const getTotalExpensesPerWeek = getTotalExpensesPerWeekMutation.data;

  const yearOnChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setYearSelected(Number(event.target.value));
  };

  const calculateExpenseButtonOnClick = () => {
    getTotalExpensesPerWeekMutation.mutate(yearSelected);
  };

  return (
    <section className="flex flex-col gap-8">
      <div className="flex gap-4 justify-center">
        <select
          className="select select-bordered w-full max-w-xs"
          onChange={yearOnChange}
        >
          {expenseYears?.map((year, idx) => (
            <option key={String(year) + idx} value={Number(year)}>
              {String(year)}
            </option>
          ))}
        </select>
        <button
          onClick={calculateExpenseButtonOnClick}
          className="btn btn-active w-fit"
        >
          Calculate weekly expenses for the selected year
        </button>
      </div>
      <div className="flex justify-center">
        <div className="overflow-x-auto h-[20rem] w-[50rem]">
          {getTotalExpensesPerWeek && getTotalExpensesPerWeek?.length > 0 ? (
            <table className="table">
              <thead>
                <tr>
                  <th>Start Date</th>
                  <th>End Date</th>
                  <th>Total Amount</th>
                </tr>
              </thead>
              <tbody>
                {getTotalExpensesPerWeek?.map(
                  (expensePerWeek: TotalPerWeekExpenseDto) => (
                    <tr key={expensePerWeek.startDate}>
                      <td>
                        {dayjs(String(expensePerWeek.startDate)).format(
                          'MMM DD, YYYY'
                        )}
                      </td>
                      <td>
                        {dayjs(String(expensePerWeek.endDate)).format(
                          'MMM DD, YYYY'
                        )}
                      </td>
                      <td>
                        {moneyFormatter(Number(expensePerWeek.totalAmount))}
                      </td>
                    </tr>
                  )
                )}
              </tbody>
            </table>
          ) : (
            <h2 className="text-3xl font-bold text-center">No data</h2>
          )}
        </div>
      </div>
    </section>
  );
};

export default TotalExpensesPerWeekSection;
