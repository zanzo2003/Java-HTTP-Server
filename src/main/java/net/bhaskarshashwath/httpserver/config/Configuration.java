package net.bhaskarshashwath.httpserver.config;

public class Configuration {

    private int port;
    private String webroot;

    // getters and setters
    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getWebroot(){
        return webroot;
    }

    public void setWebroot(String webroot){
        this.webroot = webroot;
    }

    // all args constructor
    public Configuration(int portNumber, String webrootUrl){
        this.webroot = webrootUrl;
        this.port = portNumber;
    }

}
