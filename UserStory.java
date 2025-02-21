import java.util.Scanner;

public class UserStory {
    
    private String[] countries;
    private String[] incomes;
    private double[] internetPercents;
    private int[] populations;
    private double[] unemployment;

    private FileOperator countriesFile; 
    private FileOperator incomeFile;
    private FileOperator internetpercentFile;
    private FileOperator populationsFile;
    private FileOperator unemploymentFile;

    public UserStory(){
        countriesFile = new FileOperator("countries.txt");
        incomeFile = new FileOperator("incomes.txt");
        internetpercentFile = new FileOperator("internetpercent.txt");
        populationsFile = new FileOperator("populations.txt");
        unemploymentFile = new FileOperator("unemployment.txt");

        countries = countriesFile.toStringArray(215);
        incomes = incomeFile.toStringArray(215);
        internetPercents = internetpercentFile.toDoubleArray(215);
        populations = populationsFile.toIntArray(215);
        unemployment = unemploymentFile.toDoubleArray(215);
    }

    public String getMinInternetCountry(){
        String country = countries[0];
        double minInternet = internetPercents[0];

        for (int i = 0; i < internetPercents.length; i++){
            if (internetPercents[i] < minInternet){
                minInternet = internetPercents[i];
                country = countries[i];
            }
        }

        return country;
    }

    public String getMaxInternetCountry(){
        String country = countries[0];
        double maxInternet = internetPercents[0];

        for (int i = 0; i < internetPercents.length; i++){
            if (internetPercents[i] > maxInternet){
                maxInternet = internetPercents[i];
                country = countries[i];
            }
        }

        return country;
    }

    public double getAverageInternet(){
        double total = 0.0;
        for (double internet : internetPercents){
            total += internet;
        }

        double average = total/internetPercents.length;

        return average;
    }

    public double getModeInternet(){
        double mode = internetPercents[0];
        int maxCount = 1;
        for (int i = 0; i < internetPercents.length; i++){
            int count = 0;
            for (int j = 0; j < internetPercents.length; j++){
                if (internetPercents[j] == internetPercents[i]){
                    count++;
                }
            }

            if (count > maxCount){
                maxCount = count;
                mode = internetPercents[i];
            }
        }

        return mode;
    }

    public double getMedianInternet(){
        double[] sortedInternet = ascendingDoubleSort(internetPercents);

        double median;
        if (sortedInternet.length%2 == 0){
            median = (sortedInternet[sortedInternet.length/2] + sortedInternet[sortedInternet.length/2 - 1]) / 2;
        }
        else {
            median = sortedInternet[sortedInternet.length/2];
        }

        return median;
    }

    public String[] findLowestInternetCountries(int numCountries){
        String[] finalCountries = new String[numCountries];
        int[] finalCountriesIndexes = new int[numCountries];

        for (int i = 0; i < numCountries; i++){
            finalCountriesIndexes[i] = i;
        }

        for (int i = numCountries; i < internetPercents.length; i++){
            for (int j = 0; j < numCountries; j++){
                if (internetPercents[i] < internetPercents[finalCountriesIndexes[j]]){
                    finalCountriesIndexes[j] = i;
                    break;
                }
            }
        }

        for (int i = 0; i < numCountries; i++){
            finalCountries[i] = countries[finalCountriesIndexes[i]];
        }

        return finalCountries;
    }

    private int[] ascendingIntSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }

        return arr;
    }

    private double[] ascendingDoubleSort(double[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            double tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }

        return arr;
    }

    public static void main(String[] args){
        UserStory user = new UserStory();

        String[] arr = user.findLowestInternetCountries(20);
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

        System.out.println(user.getMinInternetCountry());
        System.out.println(user.getMaxInternetCountry());
        System.out.println(user.getAverageInternet());
        System.out.println(user.getModeInternet());
        System.out.println(user.getMedianInternet());
    }
}