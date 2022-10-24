package domain.my.doSports_Bot.bot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.my.doSports_Bot.bot.handlers.MainTgBotHandler;
import domain.my.doSports_Bot.configs.ProjectProperties;
import domain.my.doSports_Bot.utils.GuiceCustomModule;
import domain.my.doSports_Bot.utils.Vars;
import java.nio.file.Paths;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
public class Runner {

  public static void main(String[] args) {

    Injector injector = Guice.createInjector(new GuiceCustomModule());

    ProjectProperties projectProperties = injector.getInstance(ProjectProperties.class);
    MainTgBotHandler mainTgBotHandler = injector.getInstance(MainTgBotHandler.class);

    String dbUserName = projectProperties.getDbAdminUsername();
    String dbAdminPassword = projectProperties.getDbAdminPassword();
    String dbUrl = projectProperties.getDbUrl();

    log.trace("dbUsername: {}, dbUrl: {}", dbUserName, dbUrl);
    log.trace("botName: {}", mainTgBotHandler.getBotUsername());

    Properties dbProps = new Properties();
    dbProps.setProperty("jdbcUrl", dbUrl);
    dbProps.setProperty("username", dbUserName);
    dbProps.setProperty("password", dbAdminPassword);

    // setup connection pool
    Properties props = PropertiesUtils.getPropertiesFromClasspath(Vars.PROJECT_SQL_PROPERTIES_FILE_NAME);
    Yank.setupDefaultConnectionPool(dbProps);
    Yank.setThrowWrappedExceptions(true);
    Yank.addSQLStatements(props);

    log.debug("working dir is {}", Paths.get("").toAbsolutePath());

    try {
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
      telegramBotsApi.registerBot(mainTgBotHandler);
    } catch (TelegramApiException e) {
      log.error(e.getMessage(), e);
    }

  }

}
