package clients;

import clients.impl.DefaultUgcCreatorClient;
import com.google.inject.ImplementedBy;

@ImplementedBy(DefaultUgcCreatorClient.class)
public interface UgcCreatorClient {

}