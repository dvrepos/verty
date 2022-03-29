package domain.my.tgbotcalmaster.bot.utils;

import domain.my.tgbotcalmaster.bot.handlers.GeneralMessageHandler;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.Objects;
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

  public boolean isExpectedText(String inMsgText, String chatId) {
    log.debug("checking input text {} for expectation...", inMsgText);
    return Objects.equals(InfoSaver.getLastCommandForChat(chatId), GeneralMessageHandler.COMMAND_WHICH_IS_EXPECTED);
  }
}
