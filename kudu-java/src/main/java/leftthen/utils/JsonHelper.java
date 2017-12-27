package leftthen.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class JsonHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonHelper.class);
    /**
     * 针对classpath的
     */
    public static final String CLASSPATH_SCHEMA = "classpath:";
    /**
     * 针对文件系统的.
     */
    public static final String FILE_SCHEMA = "file:///";

    public static <T> T parse(String confPath, Class<T> clazz) {
        T result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String content = JsonHelper.getFileContent(confPath);
            result = mapper.readValue(content, clazz);
        } catch (IOException e) {
            LOGGER.warn("error happened when parse, the confPath is :" + confPath + ", the typeReference is:" + clazz, e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String serialize(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (IOException e) {
            LOGGER.error("使用jackson序列化{}时报错", object);
            e.printStackTrace();
        }
        return json;
    }

    public static String getFileContent(String filePath) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            if (filePath.startsWith(CLASSPATH_SCHEMA)) {
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(filePath.substring(CLASSPATH_SCHEMA.length()));
            } else if (filePath.startsWith(FILE_SCHEMA)) {
                is = new FileInputStream(filePath.substring(FILE_SCHEMA.length()));
            } else {
                throw new RuntimeException("confPath:" + filePath + " must be start with" + CLASSPATH_SCHEMA + " or " + FILE_SCHEMA);
            }

            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
