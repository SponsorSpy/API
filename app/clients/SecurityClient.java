package clients;

import clients.impl.DefaultSecurityClient;
import com.google.inject.ImplementedBy;
import data.SecurityStatus;

@ImplementedBy(DefaultSecurityClient.class)
public interface SecurityClient {
    SecurityStatus getMemberSecurityStatus(Long accountId);
}