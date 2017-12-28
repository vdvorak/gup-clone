package ua.com.gup.common.model.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import ua.com.gup.common.annotation.Email;
import ua.com.gup.common.annotation.Password;
import ua.com.gup.common.model.ImageFileInfo;

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
    protected Long createdDate;
    protected Long lastLoginDate;
    protected Boolean ban = false;
    @Setter
    protected ImageFileInfo imageLarge;
    @Setter
    protected ImageFileInfo imageSmall;

    public Phone getMainPhone() {
        if (mainPhone == null) {
            mainPhone = new Phone();
        }
        return mainPhone;
    }

    public String imageLargeUrl() {
        return imageLarge == null ? null : imageLarge.getUrl();
    }

    public String imageSmallUrl() {
        return imageSmall == null ? null : imageSmall.getUrl();
    }

}
