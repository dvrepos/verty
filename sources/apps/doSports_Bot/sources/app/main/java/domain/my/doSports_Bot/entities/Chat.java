package domain.my.doSports_Bot.entities;

import lombok.Data;

@Data
public class Chat {

  private Long tgChatId;
  private String chatId;
  private String localeAsLanguageTag;

}
