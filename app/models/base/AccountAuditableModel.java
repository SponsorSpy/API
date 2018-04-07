package models.base;

import clients.AccountClient;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.inject.Inject;
import constants.ModelConstants;
import models.UserAccount;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Base mappable model that has an attached Account ID for auditing purposes.
 * For GDPR compliance reasons, the account should be nullable on update but not create.
 */
@MappedSuperclass
public class AccountAuditableModel extends Model {

    @Inject
    private AccountClient accountClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ModelConstants.AUDIT_ACCOUNT_ID)
    protected UserAccount account;

    @JsonIgnore
    public UserAccount getAccount() {
        return this.account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    @JsonSetter
    public void setAccount(Long accountId) {
        this.account = accountClient.findById(accountId).orElse(null);
    }

}
