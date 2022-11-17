package domain.my.doSports_Bot.utils;

import domain.my.doSports_Bot.entities.Chat;
import domain.my.doSports_Bot.entities.UserStats;
import java.util.Date;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.knowm.yank.Yank;

@UtilityClass
@Slf4j
public class EntityProcessor {

  public long createCustomer(long tgUserId) {
    Long id;
    log.debug("customer for tgUserId {} does not exist", tgUserId);
    log.trace("trying to create a new customer...");
    id = Yank.insertSQLKey("createNewCustomer", new String[]{"tgUserId: " + tgUserId});
    log.debug("customer has been created with id: {}", id);
    return id;
  }

  public long createTgUser(long customerId, long tgIdOfUser) {
    Long id;
    log.trace("trying to create a new tgUser...");
    id = Yank.insertSQLKey("createNewTgUser", new Long[]{tgIdOfUser, customerId});
    log.debug("tgUser has been created with id: {}", id);
    return id;
  }

  public long createTgChat(long tgUserId, Long tgChatId) {
    Long id;
    log.trace("trying to create a new TgChat for the tgUser...");
    id = Yank.insertSQLKey("createNewTgChat",
        new String[]{String.valueOf(tgChatId), Vars.DEFAULT_TGCHAT_LOCALE, String.valueOf(tgUserId)});
    log.debug("tgChat has been created with id: {}", id);
    return id;
  }

  public Chat loadChatById(long chatId) {
    return Yank.queryBeanSQLKey("loadChatByChatId", Chat.class, new Long[]{chatId});
  }

  public long saveSport(long tgUserId, String sportName, int sportValue, byte sportValueType) {
    long id;
    log.trace("creating sport for customer {} ...", tgUserId);
    log.trace("sport is {} / {} / {}", sportName, sportValue, sportValueType);
    id = Yank.insertSQLKey("saveSportForCustomer",
        new Object[]{new Date(), sportName, sportValue, sportValueType, tgUserId});
    log.debug("sport has been saved with id: {}", id);
    return id;
  }

  public List<UserStats> showUserDailyStats(long customerId) {
    log.trace("querying customer {} daily statistics ...", customerId);
    return Yank.queryBeanListSQLKey("showCustomerDailyStats", UserStats.class, new Long[]{customerId});
  }

  public List<UserStats> showUserStats(long customerId, Date startDate, Date endDate) {
    log.trace("querying customer {} statistics for period from {} to {} ...", customerId, startDate, endDate);
    return Yank.queryBeanListSQLKey("showCustomerStatsForPeriod", UserStats.class,
        new Object[]{startDate, endDate, customerId});
  }
}
