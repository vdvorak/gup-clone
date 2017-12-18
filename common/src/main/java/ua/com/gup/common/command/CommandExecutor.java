package ua.com.gup.common.command;

import java.io.Serializable;

public interface CommandExecutor extends Serializable {

    void doCommand(Command command) throws CommandException;
}
