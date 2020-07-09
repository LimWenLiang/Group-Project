package my.example.TheMavericksBot;

import java.io.Serializable;

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

    public Object() {
    }

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
