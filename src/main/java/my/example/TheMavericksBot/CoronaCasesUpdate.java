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

/**
 * This class is for manipulating Corona Cases Information
 *
 * @author The Mavericks
 */

public class CoronaCasesUpdate {

    private final String CoronaCasesLink = "https://www.worldometers.info/coronavirus/";
    static Document document;
    static Elements CoronaCases;
    static Object object = new Object();
    static ArrayList<Object> objectList = new ArrayList<>();


    /**
     * This method is calculate the Highest Cases,Highest New Cases,Highest Death Cases,Highest New Death Cases,
     * This method also calculate total Cases, Total New Cases, Total Death and Total New Death of selected country
     *
     * @param countryName the selected country insert by user
     * @throws Exception an input or output operation is failed or interpreted
     */

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

        // for Highest Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                // num1 and num2 is for fixing the data from table.
                final String num1 = row.select("td:nth-child(1)").text();
                final int num2 = Integer.parseInt(num1);

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

        // for Highest New Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                // num1 and num2 is for fixing the data from table.
                final String num1 = row.select("td:nth-child(1)").text();
                final int num2 = Integer.parseInt(num1);

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

        // for Highest Death Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                // num1 and num2 is for fixing the data from table.
                final String num1 = row.select("td:nth-child(1)").text();
                final int num2 = Integer.parseInt(num1);

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

        // for Highest New Death Cases
        for (Element row : CoronaCases.select("tr")) {
            try {
                // num1 and num2 is for fixing the data from table.
                final String num1 = row.select("td:nth-child(1)").text();
                final int num2 = Integer.parseInt(num1);

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

        // for Total Cases, Total New Cases, Total Death and Total New Death
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

    /**
     * This method is to write an object
     *
     * @throws IOException An input or output operation is failed or interpreted
     */

    public void writeObj() throws IOException {
        FileOutputStream fos = new FileOutputStream("Object.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(objectList);
        oos.close();
        fos.close();
    }
}
