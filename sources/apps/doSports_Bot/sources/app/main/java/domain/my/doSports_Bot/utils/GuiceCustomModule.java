package domain.my.doSports_Bot.utils;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuiceCustomModule extends AbstractModule {

  @Override
  protected void configure() {
    Properties defaults = new Properties();
    try {
      Properties properties = new Properties(defaults);
      properties.load(this.getClass().getClassLoader().getResourceAsStream(Vars.PROJECT_PROPERTIES_FILE_NAME));
      Names.bindProperties(binder(), properties);

      properties.load(
          this.getClass().getClassLoader().getResourceAsStream(Vars.PROJECT_SENSITIVE_PROPERTIES_FILE_NAME));
      Names.bindProperties(binder(), properties);
    } catch (IOException e) {
      log.error("Could not load properties file", e);
    }
  }

}
