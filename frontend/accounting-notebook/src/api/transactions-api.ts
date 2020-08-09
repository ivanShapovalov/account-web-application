// This should be in .env
const API_ENDPOINT_BASE_URL = 'http://localhost:8080/api/';
const TRANSACTIONS_URL = API_ENDPOINT_BASE_URL + 'transactions'

export const retrieveTransactionHistory = async () => {
    const response = await fetch(TRANSACTIONS_URL, {
        method: 'GET'
    });
    return response.json();
};