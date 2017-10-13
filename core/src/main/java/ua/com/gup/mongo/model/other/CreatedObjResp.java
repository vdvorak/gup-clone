package ua.com.gup.mongo.model.other;


public class CreatedObjResp {
    private String id;

    public CreatedObjResp() {
    }

    public CreatedObjResp(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
