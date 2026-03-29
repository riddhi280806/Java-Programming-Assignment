import java.util.Arrays;

class DataProcessor {

    // --- Array Operations ---

    public double findAverage(int[] arr) {
        if (arr.length == 0) return 0;
        int sum = 0;
        for (int num : arr) sum += num;
        return (double) sum / arr.length;
    }

    public int findMaximum(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    public int searchArray(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i; // Returns index
        }
        return -1;
    }

    public void sort(int[] arr) {
        Arrays.sort(arr);
    }

    public void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // --- String Operations ---

    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public String sort(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}

public class Program1 {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        
        // Example Data
        int[] numbers = {45, 12, 89, 7, 33};
        String text = "Gemini";

        System.out.println("--- Array Operations ---");
        System.out.println("Average: " + processor.findAverage(numbers));
        System.out.println("Maximum: " + processor.findMaximum(numbers));
        
        processor.sort(numbers);
        System.out.println("Sorted Array: " + Arrays.toString(numbers));
        
        processor.reverse(numbers);
        System.out.println("Reversed Array: " + Arrays.toString(numbers));

        System.out.println("\n--- String Operations ---");
        System.out.println("Original String: " + text);
        System.out.println("Reversed String: " + processor.reverse(text));
        System.out.println("Sorted String: " + processor.sort(text));
    }
}
