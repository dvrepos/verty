package domain.my.doSports_Bot.bot.handlers;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import domain.my.doSports_Bot.bot.utils.IncomingTextParser;
import domain.my.doSports_Bot.bot.utils.ProcessSport;
import domain.my.doSports_Bot.configs.ProjectProperties;
import domain.my.doSports_Bot.utils.CustomerProcessor;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class MainTgBotHandler extends TelegramLongPollingBot {

  @Inject
  @Named("bot.tg.name")
  private String botTgName;

  @Inject
  @Named("bot.tg.token")
  private String botTgToken;

  @Inject
  private CustomerProcessor customerProcessor;

  @Inject
  private ProcessSport processSport;

  @Inject
  private ProjectProperties projectProperties;

  @Override
  public void onUpdateReceived(Update update) {
    try {
      if (update.hasMessage()) {
        Message message = update.getMessage();
        if (message.hasText() || message.hasLocation()) {
          handleIncomingMessage(message);
        }
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  private void handleIncomingMessage(Message message) {
//    String receivedText = message.getText();
    String receivedText = Iterables.get(Splitter.on(CharMatcher.whitespace())
        .trimResults()
        .omitEmptyStrings()
        .split(message.getText()), 0);
    log.trace("received text: {}", receivedText);
    SendMessage sendMessageRequest = new SendMessage();

    long tgChatId = message.getChat().getId();
    long tgUserId = message.getFrom().getId();

    long custId = customerProcessor.createAndProvideId(tgUserId, tgChatId);

    if (!IncomingTextParser.isCommand(receivedText)) {
      if (IncomingTextParser.isItPushups(receivedText, tgUserId)) {
        sendMessageRequest = processSport.process(message, custId, GeneralMessageHandler.COMMAND_PUSHUPS);
      }
      if (IncomingTextParser.isItSquarts(receivedText, tgUserId)) {
        sendMessageRequest = processSport.process(message, custId, GeneralMessageHandler.COMMAND_SQUATS);
      }
      if (IncomingTextParser.isItBurpees(receivedText, tgUserId)) {
        sendMessageRequest = processSport.process(message, custId, GeneralMessageHandler.COMMAND_BURPEE);
      }
      if (IncomingTextParser.isItSwimming(receivedText, tgUserId)) {
        sendMessageRequest = processSport.process(message, custId, GeneralMessageHandler.COMMAND_SWIMMING);
      }
      if (IncomingTextParser.isItPlank(receivedText, tgUserId)) {
        sendMessageRequest = processSport.process(message, custId, GeneralMessageHandler.COMMAND_PLANK);
      }
    } else {
      sendMessageRequest = GeneralMessageHandler.createOutgoingMessage(message);
    }
    sendMessageRequest.setChatId(message.getChatId());
    sendAlert(sendMessageRequest);
  }

  private void sendAlert(SendMessage sendMessage) {
    try {
      log.trace("sending a message {} to chat [{}]", sendMessage.getText(), sendMessage.getChatId());
      execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error(e.getMessage(), e);
    }
  }

  public void sendAlert(String message, String tgChatId) {
    try {
      log.trace("sending a message {} to chat [{}]", message, tgChatId);
      SendMessage sendMessage = new SendMessage(tgChatId, message);
      execute(sendMessage);
    } catch (TelegramApiException e) {
      log.error(e.getMessage(), e);
    }
  }

  @Override
  public String getBotUsername() {
    return botTgName;
  }

  @Override
  public String getBotToken() {
    return botTgToken;
  }

}
