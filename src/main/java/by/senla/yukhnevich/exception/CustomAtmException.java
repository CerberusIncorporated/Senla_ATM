package by.senla.yukhnevich.exception;

public class CustomAtmException extends Exception{
    public CustomAtmException() {
    }

    public CustomAtmException(String message) {
        super(message);
    }

    public CustomAtmException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomAtmException(Throwable cause) {
        super(cause);
    }
}
