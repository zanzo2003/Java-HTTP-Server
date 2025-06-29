package net.bhaskarshashwath.httpserver.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import net.bhaskarshashwath.httpserver.exception.HttpConfigurationException;
import net.bhaskarshashwath.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * we will make this class a singleton class as we need only one instance of this class. We will make this class singleton by
 * making the constructor and creating a static method which return the instance we have created
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
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(filePath);
        }
        catch (FileNotFoundException e){
            throw new HttpConfigurationException("Configuration File not Found", e.getCause());
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            int i;
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
        }catch (IOException e){
            throw new HttpConfigurationException("Couldn't read the File", e.getCause());
        }
        JsonNode configNode = null;

        try{
            configNode = Json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Configuration File", e.getCause());
        }

        try{
            myCurrentConfiguration = Json.fromJson(configNode, Configuration.class);
        }catch(JsonProcessingException e){
            throw new HttpConfigurationException("Failed to set Current Configuration", e.getCause());
        }
        finally {
            fileReader.close();
        }

    }

    /*
    used to get the current configuration stored in myCurrentConfiguration property
     */
    public Configuration getCurrentConfiguration(){
        if(myCurrentConfiguration == null){
            throw new HttpConfigurationException("No Current Configuration set.");
        }
        return myCurrentConfiguration;
    }
}
