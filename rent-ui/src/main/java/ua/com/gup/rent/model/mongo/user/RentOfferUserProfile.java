package ua.com.gup.rent.model.mongo.user;

public class RentOfferUserProfile extends RentOfferProfile {

    private String manager;

    private String managerInfo;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(String managerInfo) {
        this.managerInfo = managerInfo;
    }
}
