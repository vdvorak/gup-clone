package ua.com.itproekt.gup.model.tender;


public class Member {
    String id;
    String name;
    String userPic;

    public Member() {
    }

    public Member(String id) {
        this.id = id;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
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

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (getId() != null && !getId().equals(member.getId())) return false;
        if (getName() != null && !getName().equals(member.getName())) return false;
        return getUserPic().equals(member.getUserPic());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        if(getName() != null)
        result = 31 * result + getName().hashCode();
        if(getUserPic() != null)
        result = 31 * result + getUserPic().hashCode();
        return result;
    }
}
