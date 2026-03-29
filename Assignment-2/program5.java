import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCountDemo {

    public static void main(String[] args) {
        String filePath = "sample_text.txt";

        // 1. Create a sample file to read from
        createSampleFile(filePath);

        // 2. Read the file and count word occurrences
        Map<String, Integer> wordCounts = countWordsInFile(filePath);

        // 3. Display the results
        System.out.println("=== Word Frequencies ===");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.printf("%-10s : %d%n", entry.getKey(), entry.getValue());
        }
        
        // Clean up the temporary file (optional)
        new File(filePath).delete();
    }

    /**
     * Core Logic: Reads a file line by line and counts word occurrences.
     */
    private static Map<String, Integer> countWordsInFile(String filePath) {
        Map<String, Integer> wordMap = new HashMap<>();

        // Using try-with-resources to automatically close the file after reading
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Read until the end of the file (readLine returns null)
            while ((line = reader.readLine()) != null) {
                
                // Split the line into words based on non-word characters (spaces, punctuation)
                // The regex "\\W+" means "one or more non-word characters"
                String[] words = line.toLowerCase().split("\\W+");

                for (String word : words) {
                    // Ignore empty strings that can happen during splitting
                    if (!word.isEmpty()) {
                        // If the word is already in the map, increment its count by 1.
                        // Otherwise, add it with a count of 1.
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return wordMap;
    }

    /**
     * Helper Method: Generates a sample file so the program can run immediately.
     */
    private static void createSampleFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Hello world!\n");
            writer.write("Java is a programming language. Java is widely used.\n");
            writer.write("Hello again, world.");
            System.out.println("Successfully created sample file.\n");
        } catch (IOException e) {
            System.err.println("Could not create sample file: " + e.getMessage());
        }
    }
}
