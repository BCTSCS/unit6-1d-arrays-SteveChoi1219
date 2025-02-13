import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileOperator {
    private Scanner fileReader;
    private File myFile;
    public fileOperator(String filename) {
        setFile(filename);
    }
    private void setFile(String filename) {
        myFile = new File(filename);
        try {
            fileReader = new Scanner(myFile);
        } catch (FileNotFoundException error) {
            System.out.println("File not found");
        }
    }
    public int[] toIntArray(int size) {
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = fileReader.nextInt();
        }
        return arr;
    }

    public String[] toStringArray(int size) {
        String[] arr = new String[size];
        for(int i = 0; i < size; i++) {
            arr[i] = fileReader.next();
        }
        return arr;
    }

    public double[] toDoubleArray(int size) {
        double[] arr = new double[size];
        for(int i = 0; i < size; i++) {
            arr[i] = fileReader.nextDouble();
        }
        return arr;
    }

}
