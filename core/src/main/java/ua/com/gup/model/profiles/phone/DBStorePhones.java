package ua.com.gup.model.profiles.phone;


import java.util.List;

public class DBStorePhones {

    public DBStorePhones(){
    }

    public DBStorePhones(String idUser, List<Long> mainPhones, List<Long> contactPhones){
        this.idUser = idUser;
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }

    private String idUser;
    private List<Long> mainPhones;
    private List<Long> contactPhones;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<Long> getMainPhones() {
        return mainPhones;
    }

    public void setMainPhones(List<Long> mainPhones) {
        this.mainPhones = mainPhones;
    }

    public List<Long> getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(List<Long> contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBStorePhones that = (DBStorePhones) o;

        if (!contactPhones.equals(that.contactPhones)) return false;
        if (!idUser.equals(that.idUser)) return false;
        if (!mainPhones.equals(that.mainPhones)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser.hashCode();
        result = 31 * result + mainPhones.hashCode();
        result = 31 * result + contactPhones.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DBStorePhones{" +
                "idUser='" + idUser + '\'' +
                ", mainPhones=" + mainPhones +
                ", contactPhones=" + contactPhones +
                '}';
    }
}
