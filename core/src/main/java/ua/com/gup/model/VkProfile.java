package ua.com.gup.model;

import java.util.Map;

public interface VkProfile {

    /** The user's VK ID. */
    public String getId();
    /** The user's full name. */
    public String getName();
    /** The user's first name. */
    public String getFirstName();
    /** The user's last name. */
    public String getLastName();
    /** The URL of the profile for the user on VK. */
    public String getLink();
    /** The user's VK username. */
    public String getUsername();
    /** The user's gender. Possible values: {female|male}. */
    public String getGender();
    /** The user's locale. String containing the ISO Language Code and ISO Country Code. */
    public String getLocale();
    /** Account type. For a user, value "user." */
    public String getType();
    /** The representation of the person's profile photo. */
    public Map<String,String> getImage();
    /** The user's VK email. */
    public String getEmail();

}
