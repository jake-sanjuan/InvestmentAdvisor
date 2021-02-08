import java.util.ArrayList;

/**
 * InvestmentAdvisor class does the calculations on the data received from the APICall class.
 * Has functionality to calculate 5 year, 10 year, all time returns.
 */
public class InvestmentAdvisor {

    private ArrayList <Double> monthlyPriceData;
    /**
     * Latest price, index 0.  A field because it is used in all methods of the class
     */
    double investmentLatestPrice;

    /**
     * Class constructor.  Sets monthlyPriceData ArrayList, investmentLatestPrice
     * @param monthlyPriceData              ArrayList passed from APICall class
     * @throws IndexOutOfBoundsException    Thrown to APICall class
     */
    InvestmentAdvisor(ArrayList monthlyPriceData) throws IndexOutOfBoundsException {
        this.monthlyPriceData = monthlyPriceData;
        investmentLatestPrice = this.monthlyPriceData.get(0);
    }

    /**
     * Calculates investment returns average for the past five years
     *
     * @return double yearlyReturn, return per year
     */
    double calculateFiveYearReturn() {
        double investmentPriceFiveYearsAgo;
        double yearlyReturn;

        try {
            investmentPriceFiveYearsAgo = monthlyPriceData.get(60);
            yearlyReturn = (investmentLatestPrice - investmentPriceFiveYearsAgo) /
                    investmentPriceFiveYearsAgo;
            yearlyReturn = (yearlyReturn / 5) * 100;
        } catch (IndexOutOfBoundsException e) {
            investmentPriceFiveYearsAgo = monthlyPriceData.get(monthlyPriceData.size() - 1);
            yearlyReturn = (investmentLatestPrice - investmentPriceFiveYearsAgo) /
                    investmentPriceFiveYearsAgo;
            yearlyReturn = (yearlyReturn / (monthlyPriceData.size() / 12)) * 100;
        }
        return yearlyReturn;
    }

    /**
     * Calculates investment returns average for the past ten years
     *
     * @return double yearlyReturn, return per year
     */
    double calculateTenYearReturn() {
        double investmentPriceTenYearsAgo;
        double yearlyReturn;

        try {
            investmentPriceTenYearsAgo = monthlyPriceData.get(120);
            yearlyReturn = (investmentLatestPrice - investmentPriceTenYearsAgo) /
                    investmentPriceTenYearsAgo;
            yearlyReturn = (yearlyReturn / 10) * 100;
        } catch (IndexOutOfBoundsException e) {
            investmentPriceTenYearsAgo = monthlyPriceData.get(monthlyPriceData.size() - 1);
            yearlyReturn = (investmentLatestPrice - investmentPriceTenYearsAgo) /
                    investmentPriceTenYearsAgo;
            yearlyReturn = (yearlyReturn / (monthlyPriceData.size() / 12)) * 100;
        }
        return yearlyReturn;
    }

    /**
     * Calculates investment returns average for the life of the stock.
     *
     * @return double yearlyReturn, return per year
     */
    double calculateAllTimeReturn() {
        double investmentPriceAllTime = monthlyPriceData.get(monthlyPriceData.size() - 1);
        double yearlyReturn = (investmentLatestPrice - investmentPriceAllTime) /
                investmentPriceAllTime;
        return (yearlyReturn / (monthlyPriceData.size() / 12)) * 100;
    }
}
