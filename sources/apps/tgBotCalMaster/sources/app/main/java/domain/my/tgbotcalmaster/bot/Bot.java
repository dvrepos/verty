package domain.my.tgbotcalmaster.bot;


import domain.my.tgbotcalmaster.bot.handlers.GeneralMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.IncomingMessage;

@Slf4j
public class Bot {

  public void process(Exchange exchange) {
    String input = exchange.getIn().getBody().toString();
    log.trace("input is {}", input);
    IncomingMessage inMsg = (IncomingMessage) exchange.getIn().getBody();
    exchange.getIn().setBody(GeneralMessageHandler.oTMsg(inMsg));
  }


}
