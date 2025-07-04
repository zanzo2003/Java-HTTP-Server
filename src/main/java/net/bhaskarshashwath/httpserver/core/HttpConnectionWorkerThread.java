package net.bhaskarshashwath.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;

    public HttpConnectionWorkerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
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

            LOGGER.info("Connection processing finished");

        } catch (IOException e) {
            LOGGER.error("Problem with serving ", e);
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                this.socket.close();
            } catch (IOException e) {}
        }
    }



}
