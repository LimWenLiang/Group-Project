package my.example.TheMavericksBot;

import java.io.Serializable;

/**
 * This class is to serializable all the object from the website
 *
 * @author The Mavericks
 */

public class Object implements Serializable {

    private String accessTime;
    private String country;
    private String totalCases;
    private String totalNewCases;
    private String totalDeath;
    private String totalNewDeath;
    private String highestCasesCountry;
    private String highestNewCasesCountry;
    private String highestDeathCasesCountry;
    private String highestNewDeathCasesCountry;
    private int highestCases;
    private int highestNewCases;
    private int highestDeathCases;
    private int highestNewDeathCases;

    /**
     * This method is to get object
     * @param accessTime the access time from user to website
     * @param country the selected country by user
     * @param totalCases the total cases of selected country by user
     * @param totalNewCases the total new cases of selected country by user
     * @param totalDeath the total death of selected country by user
     * @param totalNewDeath the total new death of selected country by user
     * @param highestCasesCountry country who get highest cases
     * @param highestNewCasesCountry country who get highest new cases
     * @param highestDeathCasesCountry country who get highest death cases
     * @param highestNewDeathCasesCountry country who get highest new death cases
     * @param highestCases country who get highest cases
     * @param highestNewCases country who get highest new cases
     * @param highestDeathCases country who get highest death cases
     * @param highestNewDeathCases country who get highest new death cases
     */

    public Object(String accessTime, String country, String totalCases, String totalNewCases, String totalDeath, String totalNewDeath, String highestCasesCountry, String highestNewCasesCountry, String highestDeathCasesCountry, String highestNewDeathCasesCountry, int highestCases, int highestNewCases, int highestDeathCases, int highestNewDeathCases) {
        this.accessTime = accessTime;
        this.country = country;
        this.totalCases = totalCases;
        this.totalNewCases = totalNewCases;
        this.totalDeath = totalDeath;
        this.totalNewDeath = totalNewDeath;
        this.highestCasesCountry = highestCasesCountry;
        this.highestNewCasesCountry = highestNewCasesCountry;
        this.highestDeathCasesCountry = highestDeathCasesCountry;
        this.highestNewDeathCasesCountry = highestNewDeathCasesCountry;
        this.highestCases = highestCases;
        this.highestNewCases = highestNewCases;
        this.highestDeathCases = highestDeathCases;
        this.highestNewDeathCases = highestNewDeathCases;
    }

    /**
     * This method is to insert object
     */

    public Object() {
    }

    /**
     * This method is need to updated after successfully get those highest data.
     *
     * @return display result
     */

    // need to updated after successfully get those highest data.
    @Override
    public String toString() {
        return "Country: " + country +
                "\n\nTotal Cases: " + totalCases +
                "\nTotal New Cases: " + totalNewCases +
                "\nTotal Death: " + totalDeath +
                "\nTotal New Death: " + totalNewDeath +
                "\nHighest Cases: " + highestCasesCountry + ", " + highestCases + " cases" +
                "\nHighest New Cases: " + highestNewCasesCountry + ", " + highestNewCases + " cases" +
                "\nHighest Death Cases: " + highestDeathCasesCountry + ", " + highestDeathCases + " cases" +
                "\nHighest New Death Cases: " + highestNewDeathCasesCountry + ", " + highestNewDeathCases + " cases" +
                "\n\nAccess Time: " + accessTime;
    }
}
