import React from 'react';
import classNames from 'classnames';

import {Transaction, TransactionTypes} from '../../model/transaction';

import './transactions-list.css';

type TransactionListItemProps = {
    transaction: Transaction;
    onHeaderClick: () => void;
    isExpanded?: boolean;
}

export const TransactionListItem = ({transaction, isExpanded, onHeaderClick}: TransactionListItemProps) => {

    const headerClassNames = classNames("transactions-list-item-header", {
        ["transactions-list-item-header-debit"]: transaction.type === TransactionTypes.DEBIT,
        ["transactions-list-item-header-credit"]: transaction.type === TransactionTypes.CREDIT,
    });

    return (
        <>
            <div className={headerClassNames} onClick={onHeaderClick}>
                {isExpanded ? '-' : '+'} {transaction.type} ({transaction.amount})
            </div>
            {
                isExpanded && (
                    <div className="transactions-list-item-body">
                        <b>when:</b> {transaction.date} <br/>
                        <b>what:</b> {transaction.type} <br/>
                        <b>how much:</b> {transaction.amount} <br/>
                        <b>status:</b> {transaction.status}
                    </div>
                )
            }
        </>
    );
};
