package framework;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    private static String configPath = "src/test/resources/%s.properties";
    private static String file;
    private BufferedReader bufferedReader;
    private Properties properties;
    
    public PropertyReader(String fileName) {

        try {
            file = String.format(configPath, fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file).getCanonicalPath()), "UTF-8"));
            properties = new Properties();
            properties.load(bufferedReader);
        } catch (IOException e) {
            System.out.println("File does not exist");
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Could not close configuration file");
                }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}


