import java.io.IOException;
import java.sql.*;

/**
 * The InvestmentAdvisorDatabase class connects to and updates information received from the
 * APICall class.
 */
public class InvestmentAdvisorDatabase {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/investmentAdvisor";
    /**
     * Username for database, ENTER CREDENTIALS
     */
    private static final String DB_USER = "";
    /**
     * Password for database, ENTER CREDENTIALS
     */
    private static final String DB_PASS = "";

    private Connection connection;

    private String ticker;
    private String fiveYearReturn;
    private String tenYearReturn;
    private String allTimeReturn;
    private boolean buySignal;

    /**
     * Adds single quotes to left and right side of ticker to fit SQL syntax.  Calls
     * getConn(0 method to establish connection and then setData() to put data into
     * database.
     *
     * @param ticker                    Passed in from InvestmentAdvisorInterface class
     * @throws SQLException             Thrown to InvestmentAdvisorInterface class
     * @throws IOException              Thrown to InvestmentAdvisorInterface class
     * @throws ClassNotFoundException   Thrown to InvestmentAdvisorInterface class
     * @throws InstantiationException   Thrown to InvestmentAdvisorInterface class
     * @throws IllegalAccessException   Thrown to InvestmentAdvisorInterface class
     */
    public InvestmentAdvisorDatabase(String ticker) throws SQLException, IOException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.ticker = "'" + ticker + "'";

        getConn();
        setData();
    }

    public String getFiveYearReturn() {
        return fiveYearReturn;
    }

    public String getTenYearReturn() {
        return tenYearReturn;
    }

    public String getAllTimeReturn() {
        return allTimeReturn;
    }

    public boolean isBuySignal() {
        return buySignal;
    }

    /**
     * Establishes connection to MySQL database.
     *
     * @throws SQLException             Thrown to constructor
     * @throws ClassNotFoundException   Thrown to constructor
     * @throws InstantiationException   Thrown to constructor
     * @throws IllegalAccessException   Thrown to constructor
     */
    public void getConn() throws SQLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        Class.forName(JDBC_DRIVER).newInstance();
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    /**
     * Instantiates APICall class with ticker, uses getter methods with instance of
     * PreparedStatement class and query string in order to set values in row for user
     * input ticker.  Calls getData() method.
     *
     * @throws SQLException Thrown to constructor
     * @throws IOException  Thrown to constructor
     */
    public void setData() throws SQLException, IOException {

        APICall apiCall = new APICall(ticker);
        String query =  "INSERT INTO investment_table (stock_ticker, five_year, ten_year," +
                " all_time, buy_signal) values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, ticker);
        preparedStatement.setString(2, String.valueOf(apiCall.getFiveYearAvg()));
        preparedStatement.setString(3, String.valueOf(apiCall.getTenYearAvg()));
        preparedStatement.setString(4, String.valueOf(apiCall.getAllTimeAvg()));
        preparedStatement.setBoolean(5, apiCall.getBuySignal());

        preparedStatement.executeUpdate();

        getData();
    }

    /**
     * Searches database for stock ticker user requested, sets fields to results from
     * database search.  This allows InvestmentAdvisorInterface class to get search
     * results via getter methods.  Closes connection to database at end.
     *
     * @throws SQLException Thrown to setData() method
     */
    public void getData() throws SQLException {

        String query = "SELECT * FROM investment_table WHERE stock_ticker=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ticker);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            ticker = resultSet.getString("stock_ticker");
            fiveYearReturn = resultSet.getString("five_year");
            tenYearReturn = resultSet.getString("ten_year");
            allTimeReturn = resultSet.getString("all_time");
            buySignal = resultSet.getBoolean("buy_signal");
        }

        connection.close();
    }
}
