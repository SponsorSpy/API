import com.avaje.ebean.Model;
import models.UserAccount;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Authentication Token mapping (stores an oauth token -> id mapping)
 */
@Entity
@Table(name = "ugc_tag")
public class UgcTag extends Model {
    private Long tagId;

    private UserAccount account;
}