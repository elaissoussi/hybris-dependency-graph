package ma.elaissoussi.ygraph.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

    private static Property instance = null;

    static Properties prop = null;

    private Property() throws IOException {
        final InputStream input = ClassLoader.getSystemResourceAsStream("config.properties");
        prop = System.getProperties();
        prop.load(input);
    }

    public static Property getInstance() throws IOException {
        if (instance == null) {
            instance = new Property();
        }
        return instance;
    }

    public String getPropertyByKey(String key) {

        return Property.prop.getProperty(key);
    }
}
