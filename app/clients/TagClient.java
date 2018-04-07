package clients;

import clients.impl.DefaultTagClient;
import com.google.inject.ImplementedBy;

@ImplementedBy(DefaultTagClient.class)
public interface TagClient {

}