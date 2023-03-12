package legacy.commons.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class IniReader {
    private Properties propertyFile;

    public IniReader(String newFileName) throws FileNotFoundException, IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(newFileName));
        propertyFile = p;
    }

    public String get(String propName) {
        return propertyFile.getProperty(propName);
    }
}
