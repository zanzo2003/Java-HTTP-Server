package net.bhaskarshashwath.httpserver.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private static final Logger logger = LoggerFactory.getLogger(HttpParser.class);


    private static final int SP = 0x20;  // 32
    private static final int CR = 0x8D;  // 13
    private static final int LF = 0x8A;  //10


    public HttpRequest parseHttpRequest(InputStream inputStream){

        //using InputStreamReader to read from byte stream to char stream (US_ASCII) format
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();

        //parsing all 3 parts of the HTTP request
        parseRequestLine(reader, request);
        parseHeader(reader, request);
        parseBody(reader, request);

        return request;
    }


    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {

        int _byte;

        while( (_byte = reader.read() )>= 0){
            if(_byte == CR ){
                _byte = reader.read();
                if(_byte == LF){
                    return ;
                }
            }
        }
    }

    private void parseHeader(InputStreamReader reader, HttpRequest request){}

    private void parseBody(InputStreamReader reader, HttpRequest request){}


}
