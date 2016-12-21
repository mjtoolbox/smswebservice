package com.workshop.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mijo on 2016-05-19.
 */
public class Utility {


    /**
     * This method will load properties file
     * @return
     */
    public static Properties loadProperties(){

        Properties properties = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("/workshop_spring.properties");
        if ( in == null) {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream("workshop_spring.properties");
        }
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            RuntimeException exception = new RuntimeException(e.getMessage());
            e.printStackTrace();
            throw exception;
        }
        return properties;
    }
}
