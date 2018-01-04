package ua.com.gup.rent.model.rent.complaint;

import ua.com.gup.common.model.ImageFileInfo;


public class RentComplaintInitiator {

    private String id;

    private String firstName;

    private String lastName;

    private ImageFileInfo image;

    private String imageUrl;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ImageFileInfo getImage() {
        return image;
    }

    public void setImage(ImageFileInfo image) {
        this.image = image;
    }
   
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ComplaintInitiator{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
