package ua.com.gup.common.model.mongo.profile.phone;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DBStorePhones {
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


}
