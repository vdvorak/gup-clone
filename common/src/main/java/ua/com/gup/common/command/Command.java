package ua.com.gup.common.command;

import ua.com.gup.common.model.mongo.operation.OperationType;

public interface Command<T> {

    void execute() throws Exception;

    OperationType getOperationType();

    Journalable getJournalable();
}
