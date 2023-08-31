package br.com.uallace.controle.exception;

public class ObjectNotFound extends RuntimeException{

    public ObjectNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFound(String message) {
        super(message);
    }
}
