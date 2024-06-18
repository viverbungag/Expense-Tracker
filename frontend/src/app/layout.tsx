import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import './globals.css';
import Providers from './Providers';
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';
import { Slide, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Head from 'next/head';

const inter = Inter({ subsets: ['latin'] });

// export const metadata: Metadata = {
//   title: 'Expense Tracker',
//   description: 'An expense tracker built with Next.js and TypeScript',
// };

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>
      <head>
        <title>Expense Tracker</title>
        <meta
          name="description"
          content="An expense tracker built with Next.js and TypeScript"
        />
        <meta
          http-equiv="Content-Security-Policy"
          content="upgrade-insecure-requests"
        />
      </head>
      <html lang="en">
        <Providers>
          <body className={`${inter.className} h-full`}>
            {children}
            <ReactQueryDevtools initialIsOpen={false} />
            <ToastContainer
              position="bottom-right"
              autoClose={2000}
              hideProgressBar
              newestOnTop={false}
              closeOnClick
              rtl={false}
              pauseOnFocusLoss={false}
              draggable
              pauseOnHover
              theme="dark"
              transition={Slide}
            />
          </body>
        </Providers>
      </html>
    </>
  );
}
