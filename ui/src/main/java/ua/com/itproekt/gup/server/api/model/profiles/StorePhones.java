package ua.com.itproekt.gup.server.api.model.profiles;


import java.util.Arrays;

public class StorePhones {

    public StorePhones(){
    }

    public StorePhones(String idUser, String[] mainPhones, String[] contactPhones){
        this.idUser = idUser;
        this.mainPhones = mainPhones;
        this.contactPhones = contactPhones;
    }


    private String idUser;
    private String[] mainPhones;
    private String[] contactPhones;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String[] getMainPhones() {
        return mainPhones;
    }

    public void setMainPhones(String[] mainPhones) {
        this.mainPhones = mainPhones;
    }

    public String[] getContactPhones() {
        return contactPhones;
    }

    public void setContactPhones(String[] contactPhones) {
        this.contactPhones = contactPhones;
    }

    @Override
    public String toString() {
        return "StorePhones{" +
                "idUser='" + idUser + '\'' +
                ", mainPhones=" + Arrays.toString(mainPhones) +
                ", contactPhones=" + Arrays.toString(contactPhones) +
                '}';
    }
}
