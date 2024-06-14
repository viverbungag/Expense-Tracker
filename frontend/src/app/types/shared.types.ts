type Expense = {
  id: number;
  description: string;
  amount: number;
  date: String;
};

type TotalPerWeekExpenseDto = {
  startDate: string;
  endDate: string;
  totalAmount: number;
};
