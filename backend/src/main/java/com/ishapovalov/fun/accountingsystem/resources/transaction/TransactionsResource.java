package com.ishapovalov.fun.accountingsystem.resources.transaction;

import com.ishapovalov.fun.accountingsystem.domain.account.RegularAccount;
import com.ishapovalov.fun.accountingsystem.domain.transaction.TransactionService;
import com.ishapovalov.fun.accountingsystem.domain.transaction.exceptions.*;
import com.ishapovalov.fun.accountingsystem.domain.transaction.model.Transaction;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("transactions")
public class TransactionsResource {

    private final RegularAccount account;
    private final TransactionService transactionService;

    public TransactionsResource(RegularAccount account, TransactionService transactionService) {
        this.account = account;
        this.transactionService = transactionService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionHistory() {
        Collection<Transaction> transactionHistory = transactionService.getTransactionHistory();
        List<TransactionDto> transactionDtos = transactionHistory
                .stream()
                .map(TransactionDto::fromTransaction)
                .collect(Collectors.toList());
        return Response.ok(transactionDtos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response commitTransaction(CommitTransactionParams commitTransactionParams) {
        try {
            Transaction transaction = this.transactionService.commitTransaction(
                    this.account,
                    commitTransactionParams.getAmount(),
                    commitTransactionParams.getType()
            );
            return Response.status(Response.Status.ACCEPTED)
                    .entity(TransactionDto.fromTransaction(transaction)).build();

        } catch (InvalidOperationAmountException | UnknownTransactionTypeException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (InsufficientFundsException exception) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
    }

    @GET
    @Path("/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionById(@PathParam("transactionId") String id) {
        Transaction transaction = transactionService.getTransaction(id);
        if (Objects.isNull(transaction)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(TransactionDto.fromTransaction(transaction)).build();
    }
}
