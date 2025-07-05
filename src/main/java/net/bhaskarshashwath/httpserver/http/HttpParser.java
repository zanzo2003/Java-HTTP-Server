package net.bhaskarshashwath.httpserver.http;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);


    private static final int SP = 0x20;  // Space (ASCII 32)
    private static final int CR = 0x0D;  // Carriage Return (ASCII 13)
    private static final int LF = 0x0A;  // Line Feed (ASCII 10)


    public HttpRequest parseHttpRequest(InputStream inputStream) throws IOException{

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

        StringBuffer processingDataBuffer = new StringBuffer();
        int _byte;

        Boolean isRequestTargetParsed = false;
        Boolean isMethodParsed = false;


        while( (_byte = reader.read() )>= 0){
            if(_byte == CR ){
                _byte = reader.read();
                if(_byte == LF){
                    LOGGER.info("Request VERSION to Process : {}", processingDataBuffer.toString());
                    return ;
                }
            }

            if( _byte == SP){
                if(!isMethodParsed){
                    isMethodParsed = true;
                    LOGGER.info("Request METHOD to Process : {}", processingDataBuffer.toString());
                }else if(!isRequestTargetParsed){
                    isRequestTargetParsed = true;
                    LOGGER.info("Request TARGET to Process : {}", processingDataBuffer.toString());
                }
                processingDataBuffer.delete(0, processingDataBuffer.length());
            }
            else{
                processingDataBuffer.append( (char)_byte);
            }
        }
    }

    private void parseHeader(InputStreamReader reader, HttpRequest request){}

    private void parseBody(InputStreamReader reader, HttpRequest request){}


}
