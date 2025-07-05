package net.bhaskarshashwath.httpserver.http;

public class HttpRequest extends HttpMessage{

    private String method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest(){ // package constructor - only other classes of http package can use this

    }
}
