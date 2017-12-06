package ua.com.gup.common.exeption;

public class UnsupportedFileStorageException extends RuntimeException {

    public UnsupportedFileStorageException() {        
    }

    public UnsupportedFileStorageException(String string) {
        super(string);
    }

    public UnsupportedFileStorageException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UnsupportedFileStorageException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
