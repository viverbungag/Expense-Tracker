'use client';

import { createExpense } from '@/app/utilities/fetch/expenses';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import React, { useState } from 'react';
import { toast } from 'react-toastify';

const CreateExpenseForm = () => {
  const queryClient = useQueryClient();

  const [textInputs, setTextInputs] = useState({
    description: '',
    date: '',
    amount: '',
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTextInputs({
      ...textInputs,
      [e.target.name]: e.target.value,
    });
  };

  const createExpenseMutation = useMutation({
    mutationFn: async () => {
      await createExpense(
        textInputs.description,
        textInputs.date,
        Number(textInputs.amount)
      );
    },
    onSuccess: async () => {
      setTextInputs({
        description: '',
        date: '',
        amount: '',
      });
      await queryClient.invalidateQueries({
        queryKey: ['expenses'],
      });
      toast.success('Expense added successfully');
    },
    onError: () => {
      toast.error('Failed to add expense. Please check your inputs.');
    },
  });

  const createExpenseSubmitForm = (event: any) => {
    event.preventDefault();
    createExpenseMutation.mutate();
  };

  return (
    <div className="flex items-center justify-center">
      <form
        onSubmit={createExpenseSubmitForm}
        className="flex flex-col gap-4 w-[40rem] items-center"
      >
        <input
          type="text"
          placeholder="Description"
          name="description"
          className="input input-bordered w-full max-w-xs"
          onChange={handleChange}
          value={textInputs.description}
          required
        />
        <input
          type="date"
          placeholder="Date"
          name="date"
          className="input input-bordered w-full max-w-xs"
          onChange={handleChange}
          value={textInputs.date}
          required
        />
        <input
          type="text"
          placeholder="Amount"
          name="amount"
          className="input input-bordered w-full max-w-xs"
          onChange={handleChange}
          value={textInputs.amount}
          required
        />
        <button type="submit" className="btn btn-active w-fit">
          Add Expense
        </button>
      </form>
    </div>
  );
};

export default CreateExpenseForm;
