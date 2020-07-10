package my.example.TheMavericksBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MyBot extends TelegramLongPollingBot {

    static ArrayList<Object> objectList = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        CoronaCasesUpdate coronaCasesUpdate = new CoronaCasesUpdate();

        message.setChatId(update.getMessage().getChatId());
        String input = update.getMessage().getText();
        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": " + input);
        long start = 0;

        try {
            if (input.equals("/start")) {
                message.setText("Halo " + update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + "."
                        + "\n\nThis is a robot to print COVID-19 info based on input."
                        + " You may enter any country you'd like to, then this robot will work for it.");

                try {
                    //execute print Thread Name
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
                    //execute print Thread Name
                    execute(message);

                } catch (TelegramApiException e) {
                    message.setText("Error.");
                }
            } else {
                start = System.currentTimeMillis(); //The time when the process start.
                int temp = objectList.size();
                coronaCasesUpdate.searchCountry(input);
                coronaCasesUpdate.writeObj();
                readObj();

                if (objectList.size() > temp) {
                    for (int i = temp; i < objectList.size(); i++) {
                        System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": Executed.");
                        execute(message.setText(String.valueOf(objectList.get(i))));
                        temp++;
                    }
                } else {
                    System.out.println(update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName() + ": Invalid input.");
                    execute(message.setText("Invalid country name. Please insert again."));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        // The time when the process finish.
        long end = System.currentTimeMillis();
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

    public static void readObj() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("Object.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        objectList = (ArrayList<Object>) ois.readObject();
        ois.close();
        fis.close();
    }

    //currently below is my bot, not the mavericks bot
    @Override
    public String getBotUsername() {
        return "STIW3054_TheMavericks_bot";
    }

    //currently below is my bot, not the mavericks bot
    @Override
    public String getBotToken() {
        return "1107729598:AAG4A6KFDLhWvIS4NYk6IScmc9-jZth_h70";
    }
}
