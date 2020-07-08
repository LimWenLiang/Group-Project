package my.example.TheMavericksBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());

        System.out.println(update.getMessage().getText());
        try {
            execute(message.setText(update.getMessage().getText()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "STIW3054_TheMavericks_bot";
    }

    @Override
    public String getBotToken() {
        return "1107729598:AAG4A6KFDLhWvIS4NYk6IScmc9-jZth_h70";
    }
}
