package domain.my.doSports_Bot.bot.utils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import domain.my.doSports_Bot.bot.handlers.GeneralMessageHandler;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class IncomingTextParser {

  public static boolean isCommand(String inText) {
    String receivedText = Iterables.get(Splitter.on(CharMatcher.whitespace())
        .trimResults()
        .omitEmptyStrings()
        .split(inText), 0);
    return receivedText.startsWith("/");
  }

  public boolean isItPushups(String inMsgText, long tgUserId) {
    log.debug("checking input text {} as value for push-ups...", inMsgText);
    return InfoSaver.isItExpectedCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_PUSHUPS);
  }

  public boolean isItPlank(String inMsgText, long tgUserId) {
    log.debug("checking input text {} as value for plank ...", inMsgText);
    return InfoSaver.isItExpectedCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_PLANK);
  }
  public boolean isItSquarts(String inMsgText, long tgUserId) {
    log.debug("checking input text {} as value for squarts...", inMsgText);
    return InfoSaver.isItExpectedCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_SQUATS);
  }
  public boolean isItSwimming(String inMsgText, long tgUserId) {
    log.debug("checking input text {} as value for swiiming...", inMsgText);
    return InfoSaver.isItExpectedCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_SWIMMING);
  }

  public static boolean isItBurpees(String inMsgText, long tgUserId) {
    log.debug("checking input text {} as value for squarts...", inMsgText);
    return InfoSaver.isItExpectedCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_BURPEE);
  }
}
