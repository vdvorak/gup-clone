package ua.com.itproekt.gup.model.tender.doer;

public class DoerClient {
    String id;
    boolean doerConfirm;
    boolean clientConfirm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDoerConfirm() {
        return doerConfirm;
    }

    public void setDoerConfirm(boolean doerConfirm) {
        this.doerConfirm = doerConfirm;
    }

    public boolean isClientConfirm() {
        return clientConfirm;
    }

    public void setClientConfirm(boolean clientConfirm) {
        this.clientConfirm = clientConfirm;
    }

    @Override
    public String toString() {
        return "DoerClient{" +
                "id='" + id + '\'' +
                ", doerConfirm=" + doerConfirm +
                ", clientConfirm=" + clientConfirm +
                '}';
    }
}
