package domain.my.tgbotcalmaster.bot.handlers;

import domain.my.tgbotcalmaster.bot.menubuilders.GeneralMenuBuilder;
import domain.my.tgbotcalmaster.bot.utils.IncomingTextParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

@Slf4j
public class GeneralMessageHandler {


  public static final String COMMAND_HELP = "/help";
  public static final String COMMAND_START = "/start";
  public static final String COMMAND_ABOUT = "/about";
  public static final String COMMAND_SETUP = "/setup";
  public static final String COMMAND_WHICH_IS_EXPECTED = "/customCommand";

  public static OutgoingTextMessage oTMsg(IncomingMessage inMsg) {
    String inMsgText = inMsg.getText();

    log.trace("received text is {}", inMsgText);
    String chatId = inMsg.getChat().getId();
    log.trace("chat {} and its id is {}", inMsg.getChat().toString(), chatId);
    log.trace("user {}", inMsg.getFrom().toString());

    OutgoingTextMessage outTMsg = new OutgoingTextMessage();

    if (!IncomingTextParser.isCommand(inMsgText)) {
      if (IncomingTextParser.isExpectedText(inMsgText, chatId)) {
        outTMsg.setText("processing your token...");
        outTMsg.setText(outTMsg.getText() + "\n\n not implemented yet :(");
      } else {
        outTMsg.setText("unknown text: " + inMsgText);
        GeneralMenuBuilder.startMenu(outTMsg);
      }
      return outTMsg;
    }

//    builder for common/initial commands
    log.trace("received command is: {}", inMsgText);
    switch (inMsgText) {
      case COMMAND_HELP -> {
        log.trace("help!");
        outTMsg.setText("HELP is empty :( ");
        GeneralMenuBuilder.startMenu(outTMsg);
      }
      case COMMAND_START -> {
        outTMsg.setText("going to start...");
        GeneralMenuBuilder.startMenu(outTMsg);
      }
      case COMMAND_ABOUT -> {
        log.trace("about");
        outTMsg.setText("I'm simple TG bot");
        GeneralMenuBuilder.startMenu(outTMsg);
      }
      case COMMAND_SETUP -> {
        log.trace("processing setup...");
        outTMsg.setText("=== setup ===");
        GeneralMenuBuilder.setupMenu(outTMsg);
      }
      default -> {
        log.trace("unknown command: {}", inMsgText);
        outTMsg.setText("not implemented feature: " + inMsgText);
        GeneralMenuBuilder.startMenu(outTMsg);
      }
    }
    return outTMsg;
  }

}
