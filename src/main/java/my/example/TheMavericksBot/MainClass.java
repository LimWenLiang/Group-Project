package my.example.TheMavericksBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * This class is to register telegram bot in telegram
 *
 * @author The Mavericks
 */

public class MainClass {

    /**
     * This method is link with telegram
     * @param args syntax
     */

    public static void main(String[] args) {
        disableWarning();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is to display warning message
     */
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
