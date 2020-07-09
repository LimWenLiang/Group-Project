package my.example.TheMavericksBot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CoronaCasesUpdate {

    private final String CoronaCasesLink = "https://www.worldometers.info/coronavirus/";
    static Document document;
    static Elements CoronaCases;
    static Object object = new Object();
    static ArrayList<Object> objectList = new ArrayList<>();


    public void searchCountry(String countryName) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        document = Jsoup.connect(CoronaCasesLink).get();
        CoronaCases = document.select("table#main_table_countries_today");

        int temp = 0;
        int tempTotalCases = 0;
        int tempTotalNewCases = 0;
        int tempTotalDeathCases = 0;
        int tempTotalNewDeathCases = 0;
        String country1 = null;
        String country2 = null;
        String country3 = null;
        String country4 = null;

        // Highest Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                final String totalCasesS = row.select("td:nth-child(3)").text();
                final String tempTotalCasesS = totalCasesS.replace(",", "");
                final int totalCases = Integer.parseInt(tempTotalCasesS);
                temp = totalCases;

                if (temp > tempTotalCases) {
                    tempTotalCases = temp;

                    country1 = row.select("td:nth-child(2)").text();
                }

            } catch (NumberFormatException e) {
            }
        }
        temp = 0;

        // Highest New Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                final String totalNewCasesS = row.select("td:nth-child(4)").text();
                final String tempTotalNewCasesS = totalNewCasesS.replace(",", "").replace("+", "");
                final int totalNewCases = Integer.parseInt(tempTotalNewCasesS);
                temp = totalNewCases;

                if (temp > tempTotalNewCases) {
                    tempTotalNewCases = temp;
                    country2 = row.select("td:nth-child(2)").text();
                }

            } catch (NumberFormatException e) {
            }
        }
        temp = 0;

        // Highest Death Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                final String totalDeathS = row.select("td:nth-child(5)").text();
                final String tempTotalDeathS = totalDeathS.replace(",", "");
                final int totalDeath = Integer.parseInt(tempTotalDeathS);
                temp = totalDeath;

                if (temp > tempTotalDeathCases) {
                    tempTotalDeathCases = temp;

                    country3 = row.select("td:nth-child(2)").text();
                }

            } catch (NumberFormatException e) {
            }
        }
        temp = 0;

        //Highest New Death Cases
        for (Element row : CoronaCases.select("tr")) {
            try {

                final String totalNewDeathS = row.select("td:nth-child(6)").text();
                final String tempTotalNewDeathS = totalNewDeathS.replace(",", "").replace("+", "");
                final int totalNewDeath = Integer.parseInt(tempTotalNewDeathS);
                temp = totalNewDeath;

                if (temp > tempTotalNewDeathCases) {
                    tempTotalNewDeathCases = temp;
                    country4 = row.select("td:nth-child(2)").text();
                }

            } catch (NumberFormatException e) {
            }
        }

        for (Element row : CoronaCases.select("tr")) {
            if (row.select("td:nth-of-type(2)").text().equals("")) {
                continue;
            } else {

                final String country = row.select("td:nth-child(2)").text();
                final String totalCases = row.select("td:nth-child(3)").text();
                final String totalNewCases = row.select("td:nth-child(4)").text();
                final String totalDeath = row.select("td:nth-child(5)").text();
                final String totalNewDeath = row.select("td:nth-child(6)").text();

                if (countryName.equalsIgnoreCase(country) == true) {
                    object = new Object(dateFormat.format(date), country, totalCases, totalNewCases, totalDeath, totalNewDeath, country1, country2, country3, country4, tempTotalCases, tempTotalNewCases, tempTotalDeathCases, tempTotalNewDeathCases);
                    objectList.add(object);
                }
            }
        }
    }

    public void writeObj() throws IOException {
        FileOutputStream fos = new FileOutputStream("Object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
        fos.close();
    }

}
