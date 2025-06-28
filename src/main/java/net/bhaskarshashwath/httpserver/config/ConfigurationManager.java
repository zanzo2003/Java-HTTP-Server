package net.bhaskarshashwath.httpserver.config;


import com.fasterxml.jackson.databind.JsonNode;
import net.bhaskarshashwath.httpserver.util.Json;

import java.io.FileReader;
import java.io.IOException;

/**
 * we will make this class a singleton class as we need only one instance of this class. We will make this class singleton by
 * making the constructor and creating a static method which return the instance we have craeted
 */


public class ConfigurationManager {

    /*
    private constructor and a static object so that we can pass this from the instance getter
    which is also static. Making it static gives us the ability to pass object without creating class
     */
    private static ConfigurationManager configurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager(){
    }

    public static ConfigurationManager getInstance(){
        if(configurationManager == null){
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    /*
    used to load a configuration when path is provided
     */

    public void loadConfigurationFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        StringBuffer stringBuffer = new StringBuffer();

        int i;
        while( (i = fileReader.read()) != -1){
            stringBuffer.append((char)i);
        }
        JsonNode configNode = Json.parse(stringBuffer.toString());
        myCurrentConfiguration = Json.fromJson(configNode, Configuration.class);

    }

    /*
    used to get the current configuration stored in myCurrentConfiguration property
     */
    public Configuration getCurrentConfiguration(){
        if(myCurrentConfiguration == null){

        }
        return myCurrentConfiguration;
    }
}
