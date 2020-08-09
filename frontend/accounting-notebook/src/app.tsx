import React, {useEffect, useState} from 'react';

import {TransactionsList} from './components/transactions-list/transactions-list';
import {Transaction} from './model/transaction';
import {retrieveTransactionHistory} from "./api/transactions-api";

import './app.css';

function App() {
    const [transactions, setTransactions] = useState<Transaction[]>();

    useEffect(() => {
        retrieveTransactionHistory()
            .then((response) => {
                setTransactions(response);
            });
    }, []);

    return (
        <div className="App">
            <TransactionsList transactions={transactions}/>
        </div>
    );
}

export default App;
