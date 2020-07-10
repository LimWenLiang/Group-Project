package my.example.TheMavericksBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * This class is for manipulating Telegram Bot
 *
 * @author The Mavericks
 */

public class MyBot extends TelegramLongPollingBot {

    static ArrayList<Object> objectList = new ArrayList<>();

    /**
     * This method is receive input form user and output result to user
     * @param update output of result
     */

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        CoronaCasesUpdate coronaCasesUpdate = new CoronaCasesUpdate();

        message.setChatId(update.getMessage().getChatId());
        String input = update.getMessage().getText();
        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": " + input);
        long start = 0;
        long end = 0;

        try {
            if (input.equals("/start")) {
                message.setText("Hello " + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + "."
                        + "\n\nThis is a robot to print COVID-19 info based on input."
                        + " You may enter any country you'd like to, then this robot will work for it.");

                try {
                    execute(message);

                } catch (TelegramApiException e) {
                    message.setText("Error.");
                }

                message.setText("For example:"
                        + "\n\"Message: Malaysia\""
                        + "\n\nThe output will be:"
                        + "\n\nCountry: Malaysia" + "\nTotal Cases: xxx" + "\nTotal New Cases: xxx" + "\nTotal Death: xxx" + "\nTotal New Death: xxx"
                        + "\nHighest Cases: xxx" + "\nHighest New Cases:  xxx" + "\nHighest Death Cases: xxx" + "\nHighest New Death Cases:  xxx"
                        + "\n\nLet's have a try!");

                try {
                    execute(message);

                } catch (TelegramApiException e) {
                    message.setText("Error.");
                }

            } else {
                //The time when the process start.
                start = System.currentTimeMillis();
                int temp = objectList.size();
                coronaCasesUpdate.searchCountry(input);
                coronaCasesUpdate.writeObj();
                readObj();

                if (objectList.size() > temp) {
                    for (int i = temp; i < objectList.size(); i++) {
                        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": Executed.");
                        message.setText(String.valueOf(objectList.get(i)));
                        temp++;
                        try {
                            execute(message);

                        } catch (TelegramApiException e) {
                            message.setText("Error.");
                        }
                    }
                } else {
                    System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": Invalid input.");
                    message.setText("Invalid country name. Please insert again.");
                    try {
                        execute(message);

                    } catch (TelegramApiException e) {
                        message.setText("Error.");
                    }
                }
                // The time when the process finish.
                end = System.currentTimeMillis();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        //The time taken for the process.
        long timeElaspsed = (end - start)/1000;
        //if time taken by the process exceed 60s.
        if (timeElaspsed > 60 ) {
            System.out.println("Current process took "+ timeElaspsed + " seconds.");
            message.setText( "Current process took "+ timeElaspsed + " seconds.");
            try {
                execute(message);

            } catch (TelegramApiException e) {
                message.setText("Error.");
            }

            message.setText("Current process which took more than 60s was terminated. " + "\nPlease try again.");
            try {
                execute(message);

            } catch (TelegramApiException e) {
                message.setText("Error.");
            }

            System.exit(0);
        }

        else{
            System.out.println("Current process took "+ timeElaspsed + " seconds.");
            message.setText( "Current process took "+ timeElaspsed + " seconds.");

            try {
                execute(message);

            } catch (TelegramApiException e) {
                message.setText("Error.");
            }
        }
    }

    /**
     * This method is to read an object
     *
     * @throws IOException            when an input or output operation is failed or interpreted
     * @throws ClassNotFoundException when class is not found
     */

    public static void readObj() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("Object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        objectList = (ArrayList<Object>) ois.readObject();
        ois.close();
        fis.close();
    }

    /**
     * This method is to insert bot name
     *
     * @return bot name
     */

    @Override
    public String getBotUsername() {
        return "STIW3054_TheMavericks_bot";
    }

    /**
     * This method is to insert bot token
     *
     * @return bot token
     */

    @Override
    public String getBotToken() {
        return "1107729598:AAG4A6KFDLhWvIS4NYk6IScmc9-jZth_h70";
    }
}
