package domain.my.doSports_Bot.bot.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class InfoSaver {

  private final Map<Long, String> lastCommandForChat = new HashMap<>();

  public void addCommandForTgUserId(long tgUserId, String command) {
    lastCommandForChat.put(tgUserId, command);
    log.trace("stored the last command {} for tgUserId {} !!!", command, tgUserId);
  }

  public boolean isItExpectedCommandForTgUserId(long tgUserId, String checkForCommand) {
    boolean res = false;
    log.trace("getting the command for tgUserId {} ...", tgUserId);
    String command = lastCommandForChat.get(tgUserId);
    log.trace("the last command {} has been found for tgUserId {}", command, tgUserId);
    if (Objects.equals(command, checkForCommand)) {
      lastCommandForChat.remove(tgUserId);
      log.trace("command {} for tgUserId {} have been removed", command, tgUserId);
      res = true;
    }
    return res;
  }

}
