package ua.com.gup.common.model.mongo;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import ua.com.gup.common.annotation.Email;
import ua.com.gup.common.annotation.Password;

import javax.validation.constraints.Size;

@Getter
public class CommonProfile {

    @Id
    protected String id;
    @Indexed
    protected String publicId;
    @Indexed
    protected String idSeoWord;
    protected boolean active;
    @Indexed
    @Email
    protected String email;
    protected String socWendor = "gup.com.ua";
    protected String uid;
    @Password
    protected String password;
    protected String passwordRestore;
    protected Phone mainPhone;
    @Indexed
    @Size(min = 2, max = 70)
    protected String username;
    @Size(min = 2, max = 70)
    protected String firstname;
    @Size(min = 2, max = 70)
    protected String lastname;
    protected String contactPerson;
    protected Long createdDate;
    protected Long lastLoginDate;
    protected Boolean ban = false;

    public Phone getMainPhone() {
        if (mainPhone == null) {
            mainPhone = new Phone();
        }
        return mainPhone;
    }

}
