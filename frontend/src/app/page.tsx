import CreateExpenseForm from '@/components/CreateExpenseForm';
import ExpenseHistory from '@/components/ExpenseHistory';
import Image from 'next/image';

export default function Home() {
  return (
    <main className="">
      <h1 className="text-4xl font-bold text-center mt-16">Expense Tracker</h1>
      <div className="flex flex-col gap-8 mt-16">
        <CreateExpenseForm />
        <div className="divider w-[60rem] self-center" />
        <ExpenseHistory />
      </div>
    </main>
  );
}
