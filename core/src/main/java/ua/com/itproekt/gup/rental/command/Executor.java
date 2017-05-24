package ua.com.itproekt.gup.rental.command;

public class Executor {

    public Executor() {
    }

    public void run(Command command) {
        command.execute();
    }
}
