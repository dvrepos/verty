package domain.my.tgbotcalmaster.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class PropertyLoader {

  public Properties getPropertiesFromFile(String pathToPropertyFile) {
    Properties props = new Properties();
    try (InputStream input = new FileInputStream(pathToPropertyFile)) {
      log.debug("loading properties from {}:", pathToPropertyFile);
      props.load(input);
    } catch (IOException ex) {
      log.error(ex.getMessage(), ex);
    }
    return props;
  }

  public Properties getPropertiesFromClasspath(String fileName) {
    Properties props = new Properties();
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String filePath = rootPath + fileName;
    Properties appProps = new Properties();
    try {
      log.debug("loading properties from {}:", filePath);
      appProps.load(new FileInputStream(filePath));
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return props;
  }
}
