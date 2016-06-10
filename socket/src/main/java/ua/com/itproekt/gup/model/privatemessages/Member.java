package ua.com.itproekt.gup.model.privatemessages;


public class Member {
    String id;
    String name;
    String userPicId;

    public Member() {
    }

    public Member(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPicId() {
        return userPicId;
    }

    public void setUserPicId(String userPicId) {
        this.userPicId = userPicId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userPicId='" + userPicId + '\'' +
                '}';
    }
}
