package domain.my.tgbotcalmaster.bot.menubuilders;

import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.telegram.model.InlineKeyboardButton;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;
import org.apache.camel.component.telegram.model.ReplyKeyboardMarkup;

@Slf4j
@UtilityClass
public class GeneralMenuBuilder {

  public List <InlineKeyboardButton> getMainButtons() {
    var buttonStart = InlineKeyboardButton.builder()
        .text("/start").build();
    return List.of(buttonStart);
  }

  public OutgoingTextMessage startMenu(OutgoingTextMessage oTMsg) {
    InlineKeyboardButton buttonSetup = InlineKeyboardButton.builder()
        .text("/setup").build();

    InlineKeyboardButton buttonAbout = InlineKeyboardButton.builder()
        .text("/help").build();

    ReplyKeyboardMarkup replyMarkup = ReplyKeyboardMarkup.builder()
        .keyboard()
        .addRow(Arrays.asList(buttonSetup, buttonAbout))
        .close()
        .oneTimeKeyboard(true)
        .build();

    oTMsg.setReplyMarkup(replyMarkup);
    return oTMsg;
  }

  public OutgoingTextMessage setupMenu(OutgoingTextMessage oTMsg) {
    InlineKeyboardButton buttonFreeMode = InlineKeyboardButton.builder()
        .text("/adminMode").build();

    ReplyKeyboardMarkup replyMarkup = ReplyKeyboardMarkup.builder()
        .keyboard()
        .addRow(Arrays.asList(buttonFreeMode))
        .addRow(getMainButtons())
        .close()
        .oneTimeKeyboard(true)
        .build();

    oTMsg.setReplyMarkup(replyMarkup);
    return oTMsg;
  }


}
