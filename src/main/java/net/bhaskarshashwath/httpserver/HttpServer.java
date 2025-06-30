package net.bhaskarshashwath.httpserver;


import net.bhaskarshashwath.httpserver.config.Configuration;
import net.bhaskarshashwath.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Driver class of the http server
 * **/

public class HttpServer {

    private static final String CONFIGURATION_FILE_PATH = "src/main/resources/http.json";

    public static void main(String[] args) throws IOException {

        System.out.println("Starting server ....");
        ConfigurationManager.getInstance().loadConfigurationFile(CONFIGURATION_FILE_PATH);
        Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Port : " + configuration.getPort());
        System.out.println("Webroot : " + configuration.getWebroot());

        try{
            ServerSocket serverSocket = new ServerSocket(configuration.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html> <head> <title>Java HTTP Server</title> </head> <body> <h1>Simple JAVA HTTP server</h1> </body></html>";
            final String CRLF = "\r\n";
            byte[] content = html.getBytes("UTF-8");
            String response = "HTTP/1.1 200 OK" + CRLF +
                    "Content-Type: text/html; charset=UTF-8" + CRLF +
                    "Content-Length: " + content.length + CRLF +
                    "Connection: keep-alive" + CRLF +
                    CRLF;

            outputStream.write(response.getBytes("UTF-8"));
            outputStream.write(content);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        }catch(IOException e){
            throw new IOException();
        }
    }
}