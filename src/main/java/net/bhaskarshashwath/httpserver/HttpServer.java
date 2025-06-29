package net.bhaskarshashwath.httpserver;


import net.bhaskarshashwath.httpserver.config.ConfigurationManager;

import java.io.IOException;

/**
 * Driver class of the http server
 * **/

public class HttpServer {

    private static final String CONFIGURATION_FILE_PATH = "src/main/resources/http.json";

    public static void main(String[] args) throws IOException {

        System.out.println("Hello World!!!");
        ConfigurationManager.getInstance().loadConfigurationFile(CONFIGURATION_FILE_PATH);
    }
}