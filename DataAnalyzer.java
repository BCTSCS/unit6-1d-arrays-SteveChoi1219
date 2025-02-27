public class DataAnalyzer{

    public static String findCountryByInternetUsage(double internet, double[] internetList, String[] countryList){
        for (int i = 0; i < internetList.length; i++){
            if (internet == internetList[i]){
                return countryList[i];
            }
        }

        return "None";
    }

    public static String findCountryByIncomeLevel(String incomeLevel, String[] incomeList, String[] countryList){
        for (int i = 0; i < incomeList.length; i++){
            if (incomeLevel.equals(incomeList[i])){
                return countryList[i];
            }
        }

        return "None";
    }

    public static String findCountryByPopulation(int populationLevel, int[] populationList, String[] countryList){
        for (int i = 0; i < populationList.length; i++){
            if (populationLevel == populationList[i]){
                return countryList[i];
            }
        }

        return "None";
    }

    public static String findCountryByUnemployment(double unemploymentLevel, double[] unemploymentList, String[] countryList){
        for (int i = 0; i < unemploymentList.length; i++){
            if (unemploymentLevel == unemploymentList[i]){
                return countryList[i];
            }
        }

        return "None";
    }

    public static String getMinInternetCountry(String[] countries, double[] internetPercents){
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

    public static String getMaxInternetCountry(String[] countries, double[] internetPercents){
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

    public static double getAverageInternet(double[] internetPercents){
        double total = 0.0;
        for (double internet : internetPercents){
            total += internet;
        }

        double average = total/internetPercents.length;

        return average;
    }

    public static double getModeInternet(double[] internetPercents){
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

    public static double getMedianInternet(double[] internetPercents){
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

    public static String[] findLowestInternetCountries(int numCountries, double[] internetPercents, String[] countries){
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

    public static int[] ascendingIntSort(int[] arr){
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

    public static double[] ascendingDoubleSort(double[] arr){
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

    // Linear search
    public static int searchList(int[] numbers, int target){
        int index = 0;
        while (index <= numbers.length-1){
            if (numbers[index] == target){
                return index;
            }
            index++;
        }

        return -1;
    }

    // Binary search
    public static int binarySearch(int[] numbers, int target){
        int minIndex = 1;
        int maxIndex = numbers.length;
        while (minIndex <= maxIndex){
            int middleIndex = (int)Math.floor((minIndex+maxIndex)/2);
            if (target == numbers[middleIndex]){
                return middleIndex;
            }
            else {
                if (target > numbers[middleIndex]){
                    minIndex++;
                }
                else {
                    maxIndex--;
                }
            }
        }
        return -1;
    }

    // Reverse list
    public static int[] reverseList(int[] numbers){
        int[] newNumbers = new int[numbers.length];
        int index = 0;
        for (int i = numbers.length-1; i >= 0; i--){
            newNumbers[index] = numbers[i];
            index++;
        }

        return newNumbers;
    }

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

        String filteredCountry;
        filteredCountry = findCountryByInternetUsage(76.0, internetPercents, countries);
        filteredCountry = findCountryByIncomeLevel("Low Income", incomes, countries);

        System.out.println(filteredCountry);
    }
}