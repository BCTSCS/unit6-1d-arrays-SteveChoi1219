
public class DataAnalyzer {
    public static int[] reverseList(int[] numbers) {
        for(int i = 0; i < numbers.length / 2; i++) {
            int temp = numbers[i];
            numbers[i] = numbers[numbers.length - 1 - i];
            temp = numbers[numbers.length - 1 - i];
        }
        return numbers;
    }
    //binary
    public static int searchList(int target, int[] numbers) {
        int minIndex = 0;
        int maxIndex = numbers.length - 1;
        while(minIndex <= maxIndex) {
            int middle = minIndex + (maxIndex - minIndex) / 2;
            if(target == numbers[middle]) {
                return middle;
            } else {
                if(target > numbers[middle]) {
                    minIndex = middle + 1;
                } else {
                    maxIndex = middle - 1;
                }
            }
        }
        return -1;
    }


    //linear
    public static int searchList(int[] numbers, int target) {
        int index = 0;
        while(index <= numbers.length - 1) {
            if(numbers[index] == target) {
                return index;
            }
            index++;

        }
        return -1;
    }

    public static void main(String[] args) {
        // int[] arr = new int[100];
        // try {
        //     File f = new File("./numbers.txt");
        //     Scanner input = new Scanner(f);
        //     for(int i = 0; i < 100; i++) {
        //         arr[i] = input.nextInt();
        //     }
        // } catch (IOException e) {
        //     System.out.println("file not found");
        //     return;
        // }
        // System.out.println(searchList(arr, 1));
    }
}