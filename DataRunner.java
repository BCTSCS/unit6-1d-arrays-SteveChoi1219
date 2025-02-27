public class DataRunner {
    public static void main(String[] args){
        fileOperator countriesFile = new fileOperator("countries.txt");
        fileOperator incomeFile = new fileOperator("incomes.txt");
        fileOperator internetpercentFile = new fileOperator("internetpercent.txt");
        fileOperator populationsFile = new fileOperator("populations.txt");
        fileOperator unemploymentFile = new fileOperator("unemployment.txt");

        String[] countries = countriesFile.toStringArray(215);
        String[] incomes = incomeFile.toStringArray(215);
        double[] internetPercents = internetpercentFile.toDoubleArray(215);
        int[] populations = populationsFile.toIntArray(215);
        double[] unemployment = unemploymentFile.toDoubleArray(215);

        String[] filteredCountries;
        filteredCountries = findCountriesByInternetUsage(76, internetPercents, countries);
        
    }
}