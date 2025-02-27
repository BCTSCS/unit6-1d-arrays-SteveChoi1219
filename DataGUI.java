import java.awt.*;
import javax.swing.*;

public class DataGUI extends JFrame {
    private JTextField inputField;
    private JTextArea resultsArea;

    private fileOperator countriesFile = new fileOperator("countries.txt");
    private fileOperator incomeFile = new fileOperator("incomes.txt");
    private fileOperator internetpercentFile = new fileOperator("internetpercent.txt");
    private fileOperator populationsFile = new fileOperator("populations.txt");
    private fileOperator unemploymentFile = new fileOperator("unemployment.txt");

    private String[] countries = countriesFile.toStringArray(215);
    private String[] incomes = incomeFile.toStringArray(215);
    private double[] internetPercents = internetpercentFile.toDoubleArray(215);
    private int[] populations = populationsFile.toIntArray(215);
    private double[] unemployment = unemploymentFile.toDoubleArray(215);

    public DataGUI() {
        setTitle("Internet Data Analyzer");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputField = new JTextField(20);
        JButton findCountryByInternetButton = new JButton("Find Country by Internet Usage");
        JButton findCountryByIncomeButton = new JButton("Find Country by Income Level");
        JButton findCountryByPopulationButton = new JButton("Find Country by Population Size");
        JButton findCountryByUnemploymentButton = new JButton("Find Country by Unemployment");
        JButton getMinInternetButton = new JButton("Get Country with Lowest Internet Usage");

        resultsArea = new JTextArea(10, 40);
        resultsArea.setEditable(false);

        add(inputField);
        add(findCountryByInternetButton);
        add(findCountryByIncomeButton);
        add(findCountryByPopulationButton);
        add(findCountryByUnemploymentButton);
        add(getMinInternetButton);
        add(new JScrollPane(resultsArea));

        findCountryByInternetButton.addActionListener(e -> findByInternet());
        findCountryByIncomeButton.addActionListener(e -> findByIncome());
        findCountryByPopulationButton.addActionListener(e -> findByPopulation());
        findCountryByUnemploymentButton.addActionListener(e -> findByUnemployment());
        getMinInternetButton.addActionListener(e -> getMinInternet());
    }

    private void findByInternet() {
        try {
            double value = Double.parseDouble(inputField.getText());
            String country = DataAnalyzer.findCountryByInternetUsage(value, internetPercents, countries);
            resultsArea.setText("Country by Specified Internet Usage of " + value + ":\n" + country);
        } catch (NumberFormatException e) {
            resultsArea.setText("Error: Please enter a valid number for internet usage.");
        }
    }

    private void findByIncome() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            resultsArea.setText("Error: Please enter a valid income level.");
            return;
        }
        String country = DataAnalyzer.findCountryByIncomeLevel(input, incomes, countries);
        resultsArea.setText("Country by Specified Income Level of " + input + ":\n" + country);
    }

    private void findByPopulation() {
        try {
            int value = Integer.parseInt(inputField.getText());
            String country = DataAnalyzer.findCountryByPopulation(value, populations, countries);
            resultsArea.setText("Country by Specified Population of " + value + ":\n" + country);
        } catch (NumberFormatException e) {
            resultsArea.setText("Error: Please enter a valid integer for population.");
        }
    }

    private void findByUnemployment() {
        try {
            double value = Double.parseDouble(inputField.getText());
            String country = DataAnalyzer.findCountryByUnemployment(value, unemployment, countries);
            resultsArea.setText("Country by Specified Unemployment Rate of " + value + ":\n" + country);
        } catch (NumberFormatException e) {
            resultsArea.setText("Error: Please enter a valid number for unemployment rate.");
        }
    }

    private void getMinInternet() {
        String country = DataAnalyzer.getMinInternetCountry(countries, internetPercents);
        resultsArea.setText("Country with Lowest Internet Usage:\n" + country);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataGUI().setVisible(true));
    }
}
