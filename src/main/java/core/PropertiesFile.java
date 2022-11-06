package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFile {
    private static Properties properties;
    private static FileInputStream filein;
    private static FileOutputStream fileout;

    static String projectPath = System.getProperty("user.dir") + "/"; // xac dinh noi chay lenh
    private static String propertiesPath = "src/main/resources/data01.properties";

    public static void setPropertiesFile() {
        properties = new Properties();
        try {
            filein = new FileInputStream(projectPath + propertiesPath);
            properties.load(filein);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static String getPropValue(String keyProp) {
        String value = null;

        try {
            value = properties.getProperty(keyProp);
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return value;
    }

    public static void setPropValue(String key, String value) {
        try {
            fileout = new FileOutputStream(projectPath + propertiesPath);
            properties.setProperty(key, value);
            properties.store(fileout , "Set new value");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }


}
