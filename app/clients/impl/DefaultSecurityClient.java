package clients.impl;

import clients.AccountClient;
import clients.SecurityClient;
import com.google.inject.Inject;
import data.SecurityStatus;

public class DefaultSecurityClient implements SecurityClient {
    @Inject
    private AccountClient accountClient;

    @Override
    public SecurityStatus getMemberSecurityStatus(Long accountId) {
        return null;
    }
}