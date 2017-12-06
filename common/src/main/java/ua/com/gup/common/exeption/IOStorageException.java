package ua.com.gup.common.exeption;

public class IOStorageException extends RuntimeException {

    public IOStorageException() {        
    }

    public IOStorageException(String string) {
        super(string);
    }

    public IOStorageException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public IOStorageException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
