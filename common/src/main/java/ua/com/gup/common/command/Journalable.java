package ua.com.gup.common.command;

public interface Journalable {

    Object getObject();

    String getObjectId();

    String getObjectType();

}
