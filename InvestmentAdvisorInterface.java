import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The InvestmentAdvisorInterface class runs the GUI for the Investment Advisor program.
 * This class also instantiates the InvestmentAdvisorDatabase class, and passes information
 * from the user to that class to get and store information
 */
public class InvestmentAdvisorInterface {

    private JFrame window;
    private JPanel mainPanel;
    private JTextField buySignalField;
    private JTextField returnsField;
    private String tickerInputText = "";
    private InvestmentAdvisorDatabase db;

    /**
     * Class constructor, creates window, panels, layout for main content panels.
     */
    public InvestmentAdvisorInterface() {
        window = new JFrame();

        createPanels();

        mainPanel.add(createTitlePanel());
        mainPanel.add(createInputPanel());
        mainPanel.add(createSignalPanel());

        window.setContentPane(mainPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setTitle("Investment Advisor");
        window.setVisible(true);
    }

    /**
     * Creates main panel, sets layout for other panels added to main panel.
     */
    public void createPanels() {
        mainPanel = new JPanel();

        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);
    }

    /**
     * Creates the panel that contains the title of the program, sets padding and the
     * font type, size and color.
     *
     * @return JPanel created in this method
     */
    public JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel titleText = new JLabel("Investment Advisor Application");

        titleText.setBorder(new EmptyBorder(20, 0, 20, 0));
        titleText.setFont(new Font("Time New Roman", Font.PLAIN, 24));
        titleText.setForeground(Color.BLUE);

        titlePanel.add(titleText);

        return titlePanel;
    }

    /**
     * Creates three buttons, text field, label, and panel to contain the text field
     * and label.  Has listeners for the three buttons.  Listeners check if the text in
     * the text field is new, if it is the an instance of the InvestmentAdvisorDatabase class
     * is made.  If the input in the text field is not new, a getter for whichever button
     * the user pressed is called.  The buy signal and yearly returns are set based on
     * the information returned from calling the getter methods.  The listeners also handle
     * exceptions thrown from other parts of the program, and give the user notifications
     * if an error occurs.
     *
     * @return JPanel created
     */
    public JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();

        JPanel textFieldPanel = new JPanel();
        JTextField tickerInput = new JTextField();
        JLabel tickerInputLabel = new JLabel("Input ticker here:");
        JButton fiveYear = new JButton("5 Year");
        JButton tenYear = new JButton("10 year");
        JButton allTime = new JButton("All Time");

        tickerInput.setColumns(20);

        fiveYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tickerInputText.equals(tickerInput.getText())) {
                        returnsField.setText(db.getFiveYearReturn() + "% per year.");
                        if (String.valueOf(db.isBuySignal()).equals("true")) {
                            buySignalField.setForeground(Color.GREEN);
                            buySignalField.setText("Buy!");
                        } else {
                            buySignalField.setForeground(Color.RED);
                            buySignalField.setText("Don't buy!");
                        }
                    } else {
                        db = new InvestmentAdvisorDatabase(tickerInput.getText());
                        returnsField.setText(db.getFiveYearReturn() + "% per year.");
                        if (String.valueOf(db.isBuySignal()).equals("true")) {
                            buySignalField.setForeground(Color.GREEN);
                            buySignalField.setText("Buy!");
                        } else {
                            buySignalField.setForeground(Color.RED);
                            buySignalField.setText("Don't buy!");
                        }
                        tickerInputText = tickerInput.getText();
                    }
                } catch (SQLException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("Database Error");
                } catch (IOException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("No data for this ticker.");
                } catch (IndexOutOfBoundsException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("No data for this ticker.");
                } catch (Exception err) {
                    if (tickerInput.getText().equals("")) {
                        tickerInput.setForeground(Color.red);
                        tickerInput.setText("Please enter text");
                    } else {
                        tickerInput.setForeground(Color.red);
                        tickerInput.setText("Unknown error");
                    }
                }
            }
        });

        tenYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tickerInputText.equals(tickerInput.getText())) {
                        returnsField.setText(db.getTenYearReturn() + "% per year.");
                        if (String.valueOf(db.isBuySignal()).equals("true")) {
                            buySignalField.setForeground(Color.GREEN);
                            buySignalField.setText("Buy!");
                        } else {
                            buySignalField.setForeground(Color.RED);
                            buySignalField.setText("Don't buy!");
                        }
                    } else {
                        db = new InvestmentAdvisorDatabase(tickerInput.getText());
                        returnsField.setText(db.getTenYearReturn() + "% per year.");
                        if (String.valueOf(db.isBuySignal()).equals("true")) {
                            buySignalField.setForeground(Color.GREEN);
                            buySignalField.setText("Buy!");
                        } else {
                            buySignalField.setForeground(Color.RED);
                            buySignalField.setText("Don't buy!");
                        }
                        tickerInputText = tickerInput.getText();
                    }
                } catch (SQLException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("Database Error");
                } catch (IOException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("No data for this ticker.");
                } catch (IndexOutOfBoundsException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("No data for this ticker.");
                } catch (Exception err) {
                    if (tickerInput.getText().equals("")) {
                        tickerInput.setForeground(Color.red);
                        tickerInput.setText("Please enter text");
                    } else {
                        tickerInput.setForeground(Color.red);
                        tickerInput.setText("Unknown error");
                    }
                }
            }
        });

        allTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tickerInputText.equals(tickerInput.getText())) {
                        returnsField.setText(db.getAllTimeReturn() + "% per year.");
                        if (String.valueOf(db.isBuySignal()).equals("true")) {
                            buySignalField.setForeground(Color.GREEN);
                            buySignalField.setText("Buy!");
                        } else {
                            buySignalField.setForeground(Color.RED);
                            buySignalField.setText("Don't buy!");
                        }

                    } else {
                        db = new InvestmentAdvisorDatabase(tickerInput.getText());
                        returnsField.setText(db.getAllTimeReturn() + "% per year.");
                        if (String.valueOf(db.isBuySignal()).equals("true")) {
                            buySignalField.setForeground(Color.GREEN);
                            buySignalField.setText("Buy!");
                        } else {
                            buySignalField.setForeground(Color.RED);
                            buySignalField.setText("Don't buy!");
                        }
                        tickerInputText = tickerInput.getText();
                    }
                } catch (SQLException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("Database Error");
                } catch (IOException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("No data for this ticker.");
                } catch (IndexOutOfBoundsException err) {
                    tickerInput.setForeground(Color.red);
                    tickerInput.setText("No data for this ticker.");
                } catch (Exception err) {
                    if (tickerInput.getText().equals("")) {
                        tickerInput.setForeground(Color.red);
                        tickerInput.setText("Please enter text");
                    } else {
                        tickerInput.setForeground(Color.red);
                        tickerInput.setText("Unknown error");
                    }
                }
            }
        });

        fiveYear.setSize(10, 5);
        tenYear.setSize(10, 5);
        allTime.setSize(10, 5);

        textFieldPanel.add(tickerInputLabel);
        textFieldPanel.add(tickerInput);
        inputPanel.add(textFieldPanel);
        inputPanel.add(fiveYear);
        inputPanel.add(tenYear);
        inputPanel.add(allTime);

        return inputPanel;
    }

    /**
     * Creates signalPanel, warning label, and another JPanel to contain the two
     * JTextFields that the buy signal and yearly returns are put into.  Also creates
     * labels for the two JTextFields
     *
     * @return JPanel created.
     */
    public JPanel createSignalPanel() {

        JPanel signalPanel = new JPanel();
        signalPanel.setLayout(new BoxLayout(signalPanel, BoxLayout.Y_AXIS));


        JLabel warning = new JLabel("This is not financial advice.  Investing in the stock" +
                "market can incur losses.  Invest at your own risk.");
        warning.setSize(400, 200);
        warning.setForeground(Color.red);
        warning.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        warning.setBorder(new EmptyBorder(0, 40, 0, 0));

        JPanel returnsAndBuySignal = new JPanel(new FlowLayout());

        JLabel buySignal = new JLabel("Buy Signal:");
        JLabel returns = new JLabel("Returns for selected period:");
        buySignalField = new JTextField();
        returnsField = new JTextField();

        buySignalField.setColumns(10);
        returnsField.setColumns(10);

        buySignalField.setToolTipText("This will say true if the stock selected outperforms" +
                "the S&P 500 index over a ten year period. \n  It will say false if the " +
                "stock does not.");
        returnsField.setToolTipText("The returns per year for the time period selected.");

        returnsAndBuySignal.add(buySignal);
        returnsAndBuySignal.add(buySignalField);
        returnsAndBuySignal.add(returns);
        returnsAndBuySignal.add(returnsField);

        signalPanel.add(returnsAndBuySignal);
        signalPanel.add(warning);

        return signalPanel;
    }


    /**
     * Creates instance of InvestmentAdvisor class when program is run.
     *
     * @param args
     */
    public static void main (String[] args) {
        InvestmentAdvisorInterface investmentAdvisorInterface =
                new InvestmentAdvisorInterface();
    }
}
