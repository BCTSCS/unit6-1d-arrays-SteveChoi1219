public class DataAnalyzer {
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
        int[] r = {10, 20, 30, 40, 50};
        System.out.println(searchList(50, r));

        System.out.println(searchList(r, 50));
    }
}