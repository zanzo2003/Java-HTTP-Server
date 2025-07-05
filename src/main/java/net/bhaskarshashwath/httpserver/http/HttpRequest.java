package net.bhaskarshashwath.httpserver.http;

public class HttpRequest extends HttpMessage{

    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest(){ // package constructor - only other classes of http package can use this
    }


    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(HttpMethod method) {
        this.method = method;
    }
}
