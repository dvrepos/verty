package domain.my.doSports_Bot.bot.utils;

import domain.my.doSports_Bot.bot.handlers.GeneralMessageHandler;
import domain.my.doSports_Bot.utils.EntityProcessor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Slf4j
public class ProcessSport {

  public SendMessage process(Message message, long customerId, String sportName) {
    String input = message.getText();
    log.trace("input: {}", input);
    String sportValue = message.getText();
    int sportVolume;
    byte sportValueType = 0; // undefined

    switch (sportName) {
      case GeneralMessageHandler.COMMAND_PLANK -> {
        log.debug("saving values for plank");
        sportValueType = 1; // duration
      }
      case GeneralMessageHandler.COMMAND_PUSHUPS -> {
        log.debug("saving values for Push-ups");
        sportValueType = 2; // executions
      }
      case GeneralMessageHandler.COMMAND_SQUATS -> {
        log.debug("saving values for squarts");
        sportValueType = 2; // executions
      }
      case GeneralMessageHandler.COMMAND_BURPEE -> {
        log.debug("saving values for burpees");
        sportValueType = 2; // executions
      }
      case GeneralMessageHandler.COMMAND_SWIMMING -> {
        log.debug("saving values for swimming");
        sportValueType = 3; // distance
      }
    }
    try {
      sportVolume = Integer.parseInt(sportValue);
      long sportId = EntityProcessor.saveSport(customerId, sportName, sportVolume, sportValueType);
      log.trace("sport saved: {}", sportId);
      message.setText(GeneralMessageHandler.SAVED);
    } catch (NumberFormatException e) {
      message.setText(GeneralMessageHandler.WRONG_INPUT_VALUE);
      log.warn(e.getMessage());
    }
    return GeneralMessageHandler.createOutgoingInfoMessage(message);
  }


}
