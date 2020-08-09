import React, {useState} from 'react';

import {Transaction} from '../../model/transaction';
import {TransactionListItem} from './transactions-list-item';

import './transactions-list.css';

type TransactionsListProps = {
    transactions?: Transaction[];
};

export const TransactionsList = ({transactions}: TransactionsListProps) => {
    const [expandedTransactionId, setExpandedTransactionId] = useState('');

    return (
        <>
            <div className="transactions-list-label">
                Transaction History:
            </div>
            {transactions && transactions.map(
                transaction => {
                    const { id } = transaction;
                    const isExpanded = id === expandedTransactionId;
                    const onHeaderClick = () => {
                        isExpanded ? setExpandedTransactionId('') : setExpandedTransactionId(id);
                    }

                    return (
                        <TransactionListItem
                            key={id}
                            transaction={transaction}
                            isExpanded={isExpanded}
                            onHeaderClick={onHeaderClick}
                        />
                    )
                }
            )}
        </>
    );
};