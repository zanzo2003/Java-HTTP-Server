package net.bhaskarshashwath.httpserver.config;


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

    public void loadConfigurationFile(String filePath){

    }

    public void getCurrentConfiguration(){}
}
