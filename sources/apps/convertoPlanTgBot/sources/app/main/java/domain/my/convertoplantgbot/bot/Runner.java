package domain.my.convertoplantgbot.bot;

import domain.my.convertoplantgbot.utils.PropertyLoader;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

@Slf4j
public class Runner {

  private static final String loggerName = "domain.my.tgbotcalmaster.bot.Runner";
  public String getInfo() {
    return "some info";
  }

  private static String getBotToken() {
//    return PropertyLoader.getPropertiesFromClasspath("propertiesToBe.ignored").getProperty("bot.token");
    String botToken = PropertyLoader.getPropertiesFromFile("./build/resources/main/propertiesToBe.ignored").getProperty("bot.token");
    log.trace("botToken: {}", botToken);
    return botToken;
  }

  public static void main(String[] args) {
    log.debug("the main runner");
    log.debug("working dir is {}", Paths.get("").toAbsolutePath().toString());
    String botToken = getBotToken();

    CamelContext context = new DefaultCamelContext();
    ProducerTemplate template = context.createProducerTemplate();
    try {
      context.addRoutes(new RouteBuilder() {
        public void configure() {
          from("telegram:bots?authorizationToken="+ getBotToken())
              .log(LoggingLevel.DEBUG, org.slf4j.LoggerFactory.getLogger(loggerName),
                  "Processing body: ${body} and headers: ${headers}")
              .bean(ProcessUserDataBot.class)
              .choice()
              .when(header("/gluserid").isNotNull())
              .log(LoggingLevel.DEBUG, org.slf4j.LoggerFactory.getLogger(loggerName),
                  "calling for user data processor with headers: ${headers}")
              .to("direct:startProcessUserData").endChoice()
              .otherwise()
              .bean(Bot.class)
              .to("direct:response");
          restConfiguration().host("localhost:8080").producerComponent("http");
          from("direct:startProcessUserData")
              .to("rest:get:/say/hello")
              .to("mock:result");
          from("direct:response")
              .to("telegram:bots?authorizationToken="+ getBotToken());
        }
      });
    } catch (Exception e) {
     log.error(e.getMessage(), e);
    }
    context.start();
  }
}
