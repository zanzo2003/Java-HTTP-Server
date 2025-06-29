package net.bhaskarshashwath.httpserver.exception;

public class HttpConfigurationException extends RuntimeException{

    public HttpConfigurationException(String message){
        super(message);
    }

    public HttpConfigurationException(String message, Throwable cause){
        super(message, cause);
    }
}
