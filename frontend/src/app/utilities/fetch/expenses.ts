import axios from 'axios';

const backendUrl = process.env.NEXT_PUBLIC_BACKEND_URL as string;

export const fetchAllExpenses = async (
  searchDescription: string
): Promise<Expense[]> => {
  const response = await axios({
    method: 'GET',
    url: `${backendUrl}/api/v1/expenses`,
    params: {
      searchDescription,
    },
    headers: {
      'Access-Control-Allow-Origin': backendUrl,
    },
  });
  return response.data;
};

export const createExpense = async (
  description: string,
  date: string,
  amount: number
) => {
  await axios({
    method: 'POST',
    url: `${backendUrl}/api/v1/expenses`,
    data: {
      description,
      date,
      amount,
    },
    headers: {
      'Access-Control-Allow-Origin': backendUrl,
    },
  });
};

export const fetchAllExpenseYears = async (): Promise<number[]> => {
  const response = await axios({
    method: 'GET',
    url: `${backendUrl}/api/v1/expenses/years`,
    headers: {
      'Access-Control-Allow-Origin': backendUrl,
    },
  });
  return response.data;
};

export const fetchTotalExpensesPerWeek = async (
  year: Number
): Promise<TotalPerWeekExpenseDto[]> => {
  const response = await axios({
    method: 'GET',
    url: `${backendUrl}/api/v1/expenses/totalAmountPerWeek/${year}`,
    headers: {
      'Access-Control-Allow-Origin': backendUrl,
    },
  });
  return response.data;
};
