package clients;

import clients.impl.DefaultUgcSponsorClient;
import com.google.inject.ImplementedBy;

@ImplementedBy(DefaultUgcSponsorClient.class)
public interface UgcSponsorClient {

}