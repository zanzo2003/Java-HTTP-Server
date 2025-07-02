package net.bhaskarshashwath.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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

        try {

            while(serverSocket.isBound() && !serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection accepted : {}", socket.getInetAddress());
                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
            }

            //serverSocket.close(); //TODO handle later

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
