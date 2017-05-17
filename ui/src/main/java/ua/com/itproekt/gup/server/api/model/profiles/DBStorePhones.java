package ua.com.itproekt.gup.server.api.model.profiles;


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
    public String toString() {
        return "DBStorePhones{" +
                "idUser='" + idUser + '\'' +
                ", mainPhones=" + mainPhones +
                ", contactPhones=" + contactPhones +
                '}';
    }
}
