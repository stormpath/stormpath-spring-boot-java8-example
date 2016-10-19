package com.stormpath.spring.boot.examples;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.account.AccountList;
import com.stormpath.sdk.account.Accounts;
import com.stormpath.sdk.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountsController {

    @Autowired
    Application application;

    private static final int LIMIT = 50;

    @RequestMapping("/accounts")
    public List<AccountDetails> accounts() {
        List<AccountDetails> accountDetails = new ArrayList<>();
        AccountList accounts = application.getAccounts(Accounts.criteria().limitTo(LIMIT));

        for (Account a : accounts) {
            if (!a.getEmail().contains("@stormpath.com")) {
                accountDetails.add(new AccountDetails(
                    a.getGivenName(), a.getSurname(), a.getFullName(), a.getEmail(), a.getUsername()
                ));
            }
        }

        return accountDetails;
    }

    @RequestMapping("/accounts-lambda")
    public List<AccountDetails> accountsLambda() {
        AccountList accounts = application.getAccounts(Accounts.criteria().limitTo(LIMIT));

        List<Account> accountList = new ArrayList<>();
        accounts.forEach(accountList::add);

        return accountList.parallelStream()
            .filter(a -> !a.getEmail().contains("@stormpath.com"))
            .map(a -> new AccountDetails(
                a.getGivenName(), a.getSurname(), a.getFullName(), a.getEmail(), a.getUsername()
            ))
            .collect(Collectors.toList());
    }
}
