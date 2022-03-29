package domain.my.tgbotcalmaster.bot;


import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.component.telegram.model.IncomingMessage;

@Slf4j
public class ProcessUserDataBot {

  public void process(Exchange exchange) {
    IncomingMessage inMsg = (IncomingMessage) exchange.getMessage().getBody();
    String input = exchange.getIn().getBody().toString();
    String message = exchange.getMessage().getBody().toString();
    log.trace("input is {}, message: {}", input, message);
    String res = message;
    if (!res.isBlank()) {
      exchange.getIn().setHeader(res, inMsg.getText());
      exchange.getIn().setHeader("chatId", inMsg.getFrom().getId());
      log.trace(exchange.getIn().getHeaders().toString());
      log.trace("this command {} and text {} were for GL", res, inMsg.getText());
    }
  }

}
