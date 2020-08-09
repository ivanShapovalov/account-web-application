package com.ishapovalov.fun.accountingsystem.resources.account;

import com.ishapovalov.fun.accountingsystem.domain.account.RegularAccount;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class UserAccountResource {

    private final RegularAccount accountService;

    public UserAccountResource(RegularAccount accountService) {
        this.accountService = accountService;
    }

    @GET
    public Response getBalance() {
        return Response.ok(accountService.getBalance()).build();
    }
}
