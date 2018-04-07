package clients;

import clients.impl.DefaultAccountClient;
import com.google.inject.ImplementedBy;
import models.UserAccount;

import java.util.Optional;

@ImplementedBy(DefaultAccountClient.class)
public interface AccountClient {
    /**
     * Find a UserAccount by a given account ID if it exists.
     * @param accountId - account ID
     * @return optional of UserAccount (nullable)
     */
    Optional<UserAccount> findById(Long accountId);
}