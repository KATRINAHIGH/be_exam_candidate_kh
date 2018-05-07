package com.scoir.parser.services;

import com.scoir.parser.Globals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigService {

    /**
     *Load data from config file and set paths as system/environment/global variables
     * @param configPath config file containing key value pairs variableName:path
     */
    public void load(String configPath){
        // https://www.mkyong.com/java/java-properties-file-examples/

        Properties prop = new Properties(); //creates special hashmap of string to string key:value from a properties file
        InputStream input = null; //abstract class represent input stream of bytes, using fileinputstream which obtains
        //input bytes from a file in a file system

        try {
            input = new FileInputStream(configPath);
            prop.load(input);

            /**Don't use System.setProperties (note the 's') here cus it overwrites everything whereas loose
             * the s overwrites only a single property. Set each property individually. otherwise you will break
             * stuff again
             * uses System.setProperties to install the new Properties objects as the current set of system properties.
             * */
            System.setProperty(Globals.InputPath, prop.getProperty(Globals.InputPath));
            System.setProperty(Globals.OutputPath, prop.getProperty(Globals.OutputPath));
            System.setProperty(Globals.ErrorPath, prop.getProperty(Globals.ErrorPath));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
