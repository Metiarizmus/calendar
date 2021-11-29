package db;

import enums.StateProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyInf {

    public Properties getDataFromProperties(StateProperties stateProperties) {
        InputStream fis = null;
        Properties properties = new Properties();

        try {
            if (stateProperties.equals(StateProperties.EMAIL)) {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("email.properties");
            } else if (stateProperties.equals(StateProperties.CONNECTION)) {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("config.properties");
            } else if (stateProperties.equals(StateProperties.SQL)) {
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("sql.properties");
            } else if (stateProperties.equals(StateProperties.SOME_TEXT)){
                fis = PropertyInf.class.getClassLoader().getResourceAsStream("someTexts.properties");

            }
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


}