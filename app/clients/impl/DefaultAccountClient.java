package clients.impl;

import clients.AccountClient;
import models.UserAccount;

import java.util.Optional;

public class DefaultAccountClient implements AccountClient {
    @Override
    public Optional<UserAccount> findById(Long accountId) {
        return Optional.ofNullable(UserAccount.find.byId(accountId));
    }
}