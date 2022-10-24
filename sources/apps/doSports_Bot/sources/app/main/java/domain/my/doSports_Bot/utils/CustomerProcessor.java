package domain.my.doSports_Bot.utils;

import lombok.extern.slf4j.Slf4j;
import org.knowm.yank.Yank;

@Slf4j
public class CustomerProcessor {

  public long createAndProvideId(long tgUserId, long tgChatId) {
    Long customerId = Yank.queryScalarSQLKey("findCustomerIdByTgUserId", Long.class, new Long[]{tgUserId});

    if (customerId == null) {
      customerId = EntityProcessor.createCustomer(tgUserId);
      log.trace("customerId: {}", customerId);
      long userId = EntityProcessor.createTgUser(customerId, tgUserId);
      log.trace("tgUserId: {}", userId);
      long chatId = EntityProcessor.createTgChat(userId, tgChatId);
      log.trace("chatId: {}", chatId);
    }
    log.trace("customerId: {}", customerId);
    return customerId;
  }
}
