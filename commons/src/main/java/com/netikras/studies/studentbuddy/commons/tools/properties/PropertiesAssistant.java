package com.netikras.studies.studentbuddy.commons.tools.properties;

import com.netikras.studies.studentbuddy.commons.exception.StudBudException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import java.util.Properties;

/**
 * Created by netikras on 17.3.16.
 */
public final class PropertiesAssistant {


    public static final String DEFAULT_INIT_PROPERTIES_CLASSPATH_FILENAME = "init.properties";
    public static final String DEFAULT_INIT_FILE_PROPERTY_NAME = "inittab";


    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private PropertiesAssistant() { }

    /**
     * If prefix is non-null value, method will make sure its last symbol is '.' (a dot).<br/>
     * <br/>
     * <b>Load Order</b><br/>
     * <pre>
     *     1. If initialSource is not given, System properties will be used.
     *     2. Will look up for a property called '{@value #DEFAULT_INIT_FILE_PROPERTY_NAME}' (possibly prefixed
     *          if prefix is non-null)
     *     3. If (2) property value is not null, the following places will be scouted for that properties file:
     *       a) if value is preceded by 'file://' -- will only try to find the file in filesystem under given path
     *       b) if value is preceded by 'classpath://' -- will only try to find the file in classpath under given path
     *       c) if none of the above is the case -- will try to find that file automatically (whichever succeeds first):
     *         a.1. in classpath
     *         a.2. in filesystem
     *     4. if (2) property is not set or value is null, will try to load a file called
     *          '{@value #DEFAULT_INIT_PROPERTIES_CLASSPATH_FILENAME}' from classpath
     * </pre>
     *
     * @param initialSource
     * @param prefix
     * @return
     */
    public static Properties loadInitProperties(Properties initialSource, String prefix) throws StudBudException {
        Properties result;

        Properties sysProps = initialSource == null ? System.getProperties() : initialSource;
        String value;
        String inittabPropertyName = DEFAULT_INIT_FILE_PROPERTY_NAME;

        if (prefix != null) {
            String prefixValue = prefix;
            if (!prefix.endsWith(".")) prefixValue += ".";
            inittabPropertyName = prefixValue + inittabPropertyName;
        }

        value = sysProps.getProperty(inittabPropertyName); // the file all the configurations are stored in
        if (value != null) {
            if (value.startsWith("file://")) {
                value = value.substring("file://".length());
                result = loadPropertiesFromFile(value);
            } else if (value.startsWith("classpath://")) {
                result = loadPropertiesFromClasspath(value);
            } else { // Source type not specified. Trying to load from classpath first and then from filesystem.
                result = loadPropertiesFromClasspath(value);

                if (result == null) {
                    result = loadPropertiesFromFile(value);
                }
            }
        } else {
            result = loadPropertiesFromClasspath(DEFAULT_INIT_PROPERTIES_CLASSPATH_FILENAME);
        }

        if (result == null) {
            logger.error("init properties file not found.");
            value = sysProps.getProperty("init_manual");
            if (value == null || (!value.isEmpty() && !value.toLowerCase().equals("true"))) { // empty value ==> true
                throw new StudBudException()
                        .setMessage1("Cannot initialize startup properties")
                        .setProbableCause("INIT properties file not provided. init_manual is either not specified or is not 'true'")
                        ;
            }
        }

        return result;
    }


    public static Properties getPrefixedProperties(Properties properties, String prefix, boolean removePrefix) {
        Properties result = new Properties();
        String key;
        String value;
        String prefixValue = prefix;

        if (prefixValue != null && !prefixValue.endsWith(".")) {
            prefixValue += ".";
        }

        if (properties != null) {
            for (Map.Entry property : properties.entrySet()) {
                if (property != null) {
                    key = (String) property.getKey();
                    if (key != null && !key.isEmpty()) {
                        if (key.startsWith(prefixValue)) {
                            value = (String) property.getValue();
                            if (removePrefix) {
                                result.setProperty(key.substring(prefixValue.length()), value);
                            } else {
                                result.setProperty(key, value);
                            }
                        }
                    }
                }
            }
        } else {
            logger.warn("Properties is NULL -- cannot parse prefixed entries");
        }

        return result;
    }


    public static Properties loadPropertiesFromClasspath(String classPathFileName) {
        Properties properties = null;
        InputStream input = null;

        try {
            if (classPathFileName != null && !classPathFileName.isEmpty()) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                input = classLoader.getResourceAsStream(classPathFileName);

                properties = loadPropertiesFromStream(input);
            }
        } catch (Exception e) {
            properties = null;
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e2) {
                    logger.warn("Cannot close properties cp_file input stream: {}", e);
                }
            }
        }

        return properties;
    }


    public static Properties loadPropertiesFromFile(String filePath) {
        Properties properties = null;
        InputStream input = null;

        try {
            if (filePath != null && !filePath.isEmpty()) {
                File file = new File(filePath);

                if (file.canRead()) {
                    input = new FileInputStream(file);
                    properties = loadPropertiesFromStream(input);
                }

            }
        } catch (Exception e) {
            properties = null;
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e2) {
                    logger.warn("Cannot close properties file input stream: {}", e);
                }
            }
        }

        return properties;
    }


    public static Properties loadPropertiesFromStream(InputStream inputStream) throws Exception {
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }


    public static boolean hasAllProperties(Properties properties, String[] propertiesNames) {
        if (propertiesNames != null && propertiesNames.length > 0) {
            if (properties != null) {
                for (String prop : propertiesNames) {
                    if (!properties.containsKey(prop)) {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    public static void update(Properties that, Properties withThis) {
        if (that == null || withThis == null) {
            return;
        }

        for (Map.Entry<Object, Object> entry : withThis.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            if (key != null) {
                if (value == null) {
                    that.remove(key);
                } else {
                    that.setProperty(key, value);
                }
            }

        }

    }

    public static Properties merge(Properties... all) {
        Properties result = null;
        if (all != null) {
            result = new Properties();
            for (Properties properties : all) {
                if (properties != null) {
                    result.putAll(properties);
                }
            }
        }

        return result;
    }

    public static void copy(Properties from, Properties to) {
        if (from != null && to != null) {
            to.putAll(from);
        }
    }


}
