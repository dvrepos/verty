package domain.my.doSports_Bot.bot.menubuilders;

import domain.my.doSports_Bot.bot.handlers.GeneralMessageHandler;
import domain.my.doSports_Bot.bot.utils.InfoSaver;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Slf4j
@UtilityClass
public class GeneralMenuBuilder {

  public ReplyKeyboardMarkup startMenu() {

    KeyboardButton buttonPushups = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_PUSHUPS).build();
    KeyboardButton buttonSquats = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_SQUATS).build();
    KeyboardButton buttonPlank = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_PLANK).build();
    KeyboardButton buttonSwimming = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_SWIMMING).build();
    KeyboardButton buttonCrunches = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_CRUNCHES).build();
    KeyboardButton buttonBurpee = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_BURPEE).build();
    KeyboardButton buttonStats = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_STATS).build();
    KeyboardButton buttonYesterdayStats = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_YESTERDAY_STATS).build();
    KeyboardButton button7DaysStat = KeyboardButton.builder()
        .text(GeneralMessageHandler.COMMAND_7DAYS_STATS).build();

    KeyboardRow keyboardRowSports1 = new KeyboardRow(List.of(buttonPushups, buttonSquats, buttonCrunches));
    KeyboardRow keyboardRowSports2 = new KeyboardRow(List.of(buttonPlank, buttonBurpee));
    KeyboardRow keyboardRowSports3 = new KeyboardRow(List.of(buttonStats));
    KeyboardRow keyboardRowSports4 = new KeyboardRow(List.of(buttonYesterdayStats, button7DaysStat));

    return ReplyKeyboardMarkup.builder()
        .keyboard(List.of(keyboardRowSports1, keyboardRowSports2, keyboardRowSports3, keyboardRowSports4))
        .oneTimeKeyboard(true)
        .build();
  }

  public void processPushUps(long tgUserId) {
    InfoSaver.addCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_PUSHUPS);
  }

  public void processPlank(long tgUserId) {
    InfoSaver.addCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_PLANK);
  }
  public void processSquats(long tgUserId) {
    InfoSaver.addCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_SQUATS);
  }

  public void processBurpee(long tgUserId) {
    InfoSaver.addCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_BURPEE);
  }

  public void processSwimming(long tgUserId) {
    InfoSaver.addCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_SWIMMING);
  }

  public void processCrunches(long tgUserId) {
    InfoSaver.addCommandForTgUserId(tgUserId, GeneralMessageHandler.COMMAND_CRUNCHES);
  }

}
