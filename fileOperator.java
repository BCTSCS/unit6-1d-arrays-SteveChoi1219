import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.*;

public class fileOperator {
    private Scanner fileReader;
    private File myFile;

    private String[] countries;
    private String[] incomes;
    private double[] internetPercents;
    private int[] populations;
    private double[] unemployment;

    private fileOperator countriesFile;
    private fileOperator incomeFile;
    private fileOperator internetPercentFile;
    private fileOperator populationsFile;
    private fileOperator unemploymentFile;

    public fileOperator(String filename) {
        setFile(filename);
    }

    public ArrayList<Integer> toIntList() {
        ArrayList<Integer> result = new ArrayList<>();
        while(fileReader.hasNextInt()) {
            result.add(fileReader.nextInt());
        }
        return result;
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> result = new ArrayList<>();
        while(fileReader.hasNextLine()) {
            result.add(fileReader.nextLine());
        }
        return result;
    }

    public ArrayList<Double> toDoubleList() {
        ArrayList<Double> result = new ArrayList<>();
        while(fileReader.hasNextDouble()) {
            result.add(fileReader.nextDouble());
        }
        return result;
    }

    private void setFile(String filename) {
        myFile = new File(filename);
        try {
            fileReader = new Scanner(myFile);
        } catch (FileNotFoundException error) {
            System.out.println("File not found: " + filename);
        }
    }

    public int[] toIntArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = fileReader.nextInt();
        }
        return arr;
    }

    public String[] toStringArray(int size) {
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = fileReader.nextLine();
        }
        return arr;
    }

    public double[] toDoubleArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = fileReader.nextDouble();
        }
        return arr;
    }

    public void loadData() {
        countriesFile = new fileOperator("countries.txt");
        incomeFile = new fileOperator("incomes.txt");
        internetPercentFile = new fileOperator("internetpercent.txt");
        populationsFile = new fileOperator("populations.txt");
        unemploymentFile = new fileOperator("unemployment.txt");

        countries = countriesFile.toStringArray(215);
        incomes = incomeFile.toStringArray(215);
        internetPercents = internetPercentFile.toDoubleArray(215);
        populations = populationsFile.toIntArray(215);
        unemployment = unemploymentFile.toDoubleArray(215);
    }

    public String getMinInternetCountry() {
        String country = countries[0];
        double minInternet = internetPercents[0];

        for (int i = 1; i < internetPercents.length; i++) {
            if (internetPercents[i] < minInternet) {
                minInternet = internetPercents[i];
                country = countries[i];
            }
        }
        return country;
    }

    public String getMaxInternetCountry() {
        String country = countries[0];
        double maxInternet = internetPercents[0];

        for (int i = 1; i < internetPercents.length; i++) {
            if (internetPercents[i] > maxInternet) {
                maxInternet = internetPercents[i];
                country = countries[i];
            }
        }
        return country;
    }

    public double getAverageInternet() {
        double total = 0.0;
        for (double internet : internetPercents) {
            total += internet;
        }
        return total / internetPercents.length;
    }

    public double getModeInternet() {
        double mode = internetPercents[0];
        int maxCount = 0;

        for (double value : internetPercents) {
            int count = 0;
            for (double compare : internetPercents) {
                if (compare == value) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mode = value;
            }
        }
        return mode;
    }

    public double getMedianInternet() {
        double[] sortedInternet = ascendingDoubleSort(internetPercents);
        int length = sortedInternet.length;
        return (length % 2 == 0) ? 
            (sortedInternet[length / 2] + sortedInternet[length / 2 - 1]) / 2 : 
            sortedInternet[length / 2];
    }

    public String[] findLowestInternetCountries(int numCountries) {
        String[] finalCountries = new String[numCountries];
        int[] indexes = new int[numCountries];

        for (int i = 0; i < numCountries; i++) {
            indexes[i] = i;
        }

        for (int i = numCountries; i < internetPercents.length; i++) {
            for (int j = 0; j < numCountries; j++) {
                if (internetPercents[i] < internetPercents[indexes[j]]) {
                    indexes[j] = i;
                    break;
                }
            }
        }

        for (int i = 0; i < numCountries; i++) {
            finalCountries[i] = countries[indexes[i]];
        }
        return finalCountries;
    }

    private double[] ascendingDoubleSort(double[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            double temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        fileOperator user = new fileOperator("data.txt");
        user.loadData();

        String[] lowestCountries = user.findLowestInternetCountries(20);
        for (String country : lowestCountries) {
            System.out.println(country);
        }

        System.out.println("Min Internet Country: " + user.getMinInternetCountry());
        System.out.println("Max Internet Country: " + user.getMaxInternetCountry());
        System.out.println("Average Internet: " + user.getAverageInternet());
        System.out.println("Mode Internet: " + user.getModeInternet());
        System.out.println("Median Internet: " + user.getMedianInternet());
    }
}
