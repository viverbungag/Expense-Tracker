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

  // const data = await response.json();
  return response.data;
};
