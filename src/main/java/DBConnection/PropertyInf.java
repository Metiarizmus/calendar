package DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyInf {

    public Properties getDataForEmail() {
        InputStream fis;
        Properties properties = new Properties();

        try {
            fis = PropertyInf.class.getClassLoader().getResourceAsStream("email.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Properties getConnectData() {
        InputStream fis;
        Properties properties = new Properties();

        try {
            fis = PropertyInf.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Properties getSqlQuery() {
        InputStream fis;
        Properties property = new Properties();
        try {
            fis = PropertyInf.class.getClassLoader().getResourceAsStream("sql.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

}