package domain.my.convertoplantgbot.bot.utils;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class InfoSaver {
  private Map<String, String> lastCommandForChat = new HashMap<>();

  public void addCommandForChat(String chat, String command) {
    lastCommandForChat.put(chat, command);
    log.trace("stored the last command {} for chat {} !!!", command, chat);
  }

  public String getLastCommandForChat(String chat) {
    log.trace("getting the last command for chat {} ...", chat);
    String lastCommand = lastCommandForChat.get(chat);
    log.trace("the last command {} has been found for chat {}", lastCommand, chat);
    lastCommandForChat.remove(chat);
    log.trace("data for chat {} have been removed", chat);
    return lastCommand;
  }
}
