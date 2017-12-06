package ua.com.gup.common.exeption;

public class StorageException extends RuntimeException {

    public StorageException() {        
    }

    public StorageException(String string) {
        super(string);
    }

    public StorageException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public StorageException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
