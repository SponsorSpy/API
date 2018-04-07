package models;

import com.avaje.ebean.Model;
import data.AuthenticationProvider;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Authentication Token mapping (stores an oauth token -> id mapping).
 * Manages a login session.
 */
@Entity
@Table(name = "authentication_token")
public class AuthenticationToken extends Model {

    private Long tokenId;

    private Long accountId;

    private AuthenticationProvider provider;

    private String oauthToken;

    public AuthenticationToken(Long accountId, String oauthToken, AuthenticationProvider provider) {
        this.provider = provider;
        this.oauthToken = oauthToken;
        this.accountId = accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public void setProvider(AuthenticationProvider provider) {
        this.provider = provider;
    }

    public AuthenticationProvider getProvider() {
        return provider;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getOauthToken() {
        return oauthToken;
    }
}
