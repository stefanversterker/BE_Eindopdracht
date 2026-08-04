package nl.novi.eindopdracht.exceptions;

public class RecordInUseException extends RuntimeException {

    public RecordInUseException(String message) {
        super(message);
    }
}