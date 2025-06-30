package net.bhaskarshashwath.httpserver;


import net.bhaskarshashwath.httpserver.config.Configuration;
import net.bhaskarshashwath.httpserver.config.ConfigurationManager;
import net.bhaskarshashwath.httpserver.core.ServerListnerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Driver class of the http server
 * **/



public class HttpServer {

    private static final String CONFIGURATION_FILE_PATH = "src/main/resources/http.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) throws IOException {

        LOGGER.info("Starting the server....");
        ConfigurationManager.getInstance().loadConfigurationFile(CONFIGURATION_FILE_PATH);
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("PORT : {}", configuration.getPort());
        LOGGER.info("Webroot : {}", configuration.getWebroot());

        try{
            ServerListnerThread thread = new ServerListnerThread(configuration.getPort(), configuration.getWebroot());
            thread.start();
        }catch(IOException e){
            LOGGER.info(e.getStackTrace().toString());
            throw new IOException();
        }
    }
}