package com.ishapovalov.fun.accountingsystem;

import com.ishapovalov.fun.accountingsystem.domain.account.RegularAccount;
import com.ishapovalov.fun.accountingsystem.domain.transaction.TransactionService;
import com.ishapovalov.fun.accountingsystem.filters.CorsFilter;
import com.ishapovalov.fun.accountingsystem.resources.account.UserAccountResource;
import com.ishapovalov.fun.accountingsystem.resources.transaction.TransactionsResource;
import com.ishapovalov.fun.accountingsystem.servlets.StaticContentServlet;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class BackendServiceApplication extends Application<BackendServiceConfiguration> {

    public static void main(String... args) {
        try {
            new BackendServiceApplication().run(args);
        } catch (Exception e) {
            // would be nice to log error here
            System.exit(1);
        }
    }

    public void run(
            BackendServiceConfiguration backendServiceConfiguration,
            Environment environment
    ) {

        // NOTE!
        // This is a good candidate to be injected by di framework directly into resources.
        RegularAccount account = new RegularAccount();
        TransactionService transactionService = new TransactionService();

        environment.jersey().register(new UserAccountResource(account));
        environment.jersey().register(new TransactionsResource(account, transactionService));

        // this one is for debug
        environment.servlets().addFilter("cors", new CorsFilter())
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "*");
        environment.servlets()
                .addServlet("static-content", StaticContentServlet.class)
                .addMapping("/");
    }

    @Override
    public void initialize(Bootstrap<BackendServiceConfiguration> bootstrap) {
        super.initialize(bootstrap);
    }
}
