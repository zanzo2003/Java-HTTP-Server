package net.bhaskarshashwath.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListnerThread extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerListnerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListnerThread(int port , String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run(){

        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = this.serverSocket.accept();
            LOGGER.info("Server connection accepted : {}", socket.getInetAddress());
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
