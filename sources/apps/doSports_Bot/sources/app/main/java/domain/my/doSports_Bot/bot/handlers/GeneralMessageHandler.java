package domain.my.doSports_Bot.bot.handlers;

import domain.my.doSports_Bot.bot.menubuilders.GeneralMenuBuilder;
import domain.my.doSports_Bot.entities.UserStats;
import domain.my.doSports_Bot.utils.EntityProcessor;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.knowm.yank.Yank;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Slf4j
@UtilityClass
public class GeneralMessageHandler {

  public static final String COMMAND_HELP = "/help";
  public static final String COMMAND_START = "/start";
  public static final String COMMAND_ABOUT = "/about";
  public static final String COMMAND_PUSHUPS = "/pushUps";
  public static final String COMMAND_SQUATS = "/squats";
  public static final String COMMAND_PLANK = "/plank";
  public static final String COMMAND_SWIMMING = "/swimming";
  public static final String COMMAND_STATS = "/dailyStatistics";
  public static final String SAVED = "saved ;) ";
  public static final String WRONG_INPUT_VALUE = "wrong entered value";

  public SendMessage createOutgoingMessage(Message inMsg) {
    String inMsgText = inMsg.getText();
    long tgUserId = inMsg.getFrom().getId();

    log.trace("received text is {}", inMsgText);
    long chatId = inMsg.getChat().getId();
    log.trace("chat {} and its id is {}", inMsg.getChat().toString(), chatId);
    log.trace("user {}", inMsg.getFrom().toString());

    String outTMsg = " ";
    String empty = "!";
    SendMessage resSendMessage = new SendMessage();
//    builder for common/initial commands
    log.trace("received command is: {}", inMsgText);
    switch (inMsgText) {
      case COMMAND_HELP -> {
        log.trace("help!");
        outTMsg = """
            === help ===
            I'm simple sport tracker bot .""";
        resSendMessage.setReplyMarkup(GeneralMenuBuilder.startMenu());
        resSendMessage.setText(empty);
      }
      case COMMAND_PUSHUPS -> {
        log.trace("adding push-ups...");
        outTMsg = "=== number of push-ups required ===";
        outTMsg = outTMsg + "\n\n  send me quantity of your pus-ups which you done";
        GeneralMenuBuilder.processPushUps(tgUserId);
        resSendMessage.setText(empty);
      }
      case COMMAND_PLANK -> {
        log.trace("adding plank...");
        outTMsg = "=== duration !!! in seconds !!! required ===";
        outTMsg = outTMsg + "\n\n  send me duration (secs) of your plank which you done";
        GeneralMenuBuilder.processPlank(tgUserId);
        resSendMessage.setText(empty);
      }
      case COMMAND_SQUATS -> {
        log.trace("adding squats...");
        outTMsg = "=== number of squats required ===";
        outTMsg = outTMsg + "\n\n  send me quantity of your squats which you done";
        GeneralMenuBuilder.processSquats(tgUserId);
        resSendMessage.setText(empty);
      }
      case COMMAND_SWIMMING -> {
        log.trace("adding swimming...");
        outTMsg = "=== distance !!! in meters !!! of squats required ===";
        outTMsg = outTMsg + "\n\n  send me distance (meters) which you done";
        GeneralMenuBuilder.processSwimming(tgUserId);
        resSendMessage.setText(empty);
      }
      case COMMAND_STATS -> {
        Long customerId = Yank.queryScalarSQLKey("findCustomerIdByTgUserId", Long.class, new Long[]{tgUserId});
        log.trace("daily statistics...");
        List<UserStats> userStatsList = EntityProcessor.showUserDailyStats(customerId);
        StringBuilder userStats = new StringBuilder();
        for (UserStats userStat : userStatsList) {
          userStats.append("\n\n").append(userStat.getSportName()).append(" > ")
              .append(userStat.getSumSportValue());
        }
        outTMsg = "=== daily statistics ===";
        outTMsg = outTMsg + "\n" + userStats;
        resSendMessage.setText(empty);
      }
      case COMMAND_START -> {
        resSendMessage.setReplyMarkup(GeneralMenuBuilder.startMenu());
        resSendMessage.setText(empty);
      }
      case COMMAND_ABOUT -> {
        log.trace("about");
        outTMsg = "I'm simple sport exercise tracker bot";
        resSendMessage.setReplyMarkup(GeneralMenuBuilder.startMenu());
        resSendMessage.setText(empty);
      }
      default -> {
        log.trace("unknown command: {}", inMsgText);
        outTMsg = "not implemented feature: " + inMsgText;
        GeneralMenuBuilder.startMenu();
        resSendMessage.setText(empty);
      }
    }
    log.trace("outTMsg: {}", outTMsg);
    log.trace("resSendMessage.getText(): {}", resSendMessage.getText());
    resSendMessage.setText(outTMsg + "\n" + resSendMessage.getText());
    return resSendMessage;
  }

  public SendMessage createOutgoingInfoMessage(Message inMsg) {
    String inMsgText = inMsg.getText();
    log.trace("received inMsgText: {}", inMsgText);
    SendMessage resSendMessage = new SendMessage();
    String resText;
    if (SAVED.equals(inMsgText)) {
      log.trace("sport has been saved");
      resText = "saved";
      ReplyKeyboardMarkup startMenu = GeneralMenuBuilder.startMenu();
//      fixme: resSendMessage.getText returns null
//      resSendMessage.setText(resText + "\n" + resSendMessage.getText());
      resSendMessage.setText(resText + "\n!!!");
      resSendMessage.setReplyMarkup(startMenu);
    } else {
      resText = WRONG_INPUT_VALUE;
      resSendMessage.setText(resText);
    }
    return resSendMessage;
  }


}
