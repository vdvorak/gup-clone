package ua.com.gup.common.model.mongo.profile.manager.event;

public enum ManagerActionType {
    TYPE_CALL(ManagerCallAction.class);

    private Class cls;

    ManagerActionType(Class cls){
        this.cls = cls;
    }


    public ManagerAction createInstance() {
        try {
            return (ManagerAction)cls.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

}
