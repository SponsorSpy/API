package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "account")
public class UserAccount extends Model {

    @Id
    @Column(name = "account_id")
    @SequenceGenerator(name="gen", sequenceName="account_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    private Long id;

    public static Finder<Long, UserAccount> find = new Finder<>(UserAccount.class);


    private String oauthToken;

    @Embedded
    private AccountFlags flags;

    @OneToOne(mappedBy = "account", cascade = CascadeType.REMOVE)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private AccountToken accountToken;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="account")
    private List<ProfileAcl> acls;

    @DbJsonB
    @Column(name = "account_settings")
    @JsonProperty("settings")
    public AccountSettings settings;

    @DbJsonB
    @Column(name = "account_attributes")
    @JsonProperty("attributes")
    private AccountAttributes attributes;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @JsonIgnore
    public boolean isSuperUser() {
        return this.getFlags().isSuperUser();
    }

}
