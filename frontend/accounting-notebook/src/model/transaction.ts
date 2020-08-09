export const TransactionTypes = {
    DEBIT: 'DEBIT',
    CREDIT: 'CREDIT',
} as const;

type TransactionTypesT = typeof TransactionTypes;
export type TransactionTypesType = TransactionTypesT[keyof TransactionTypesT];

export const TransactionCompletionStatuses = {
    SUCCESSFUL: 'SUCCESSFUL',
    FAILED: 'FAILED',
} as const;

type TransactionCompletionStatusesT = typeof TransactionCompletionStatuses;
export type TransactionCompletionStatusesType = TransactionCompletionStatusesT[keyof TransactionCompletionStatusesT];

export type Transaction = {
    id: string;
    amount: number;
    type: TransactionTypesType;
    date: string;
    status: TransactionCompletionStatusesType;
};