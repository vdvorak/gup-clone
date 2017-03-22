package ua.com.itproekt.gup.rule_logiс;


public class Contract implements Status {

    private Status status;

    public void setState(Status status) {
        this.status = status;
    }

    public Status getState() {
        return this.status;
    }

    @Override
    public void doAction() {
        this.status.doAction();
    }

}
