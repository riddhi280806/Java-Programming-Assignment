import java.util.ArrayList;
import java.util.List;

public class JavaConceptsDemo {

    public static void main(String[] args) {
        demonstrateWrapperClasses();
        System.out.println("\n--------------------------------------------------\n");
        demonstrateStringVsStringBuffer();
    }

    /**
     * Demonstrates Wrapper classes, Autoboxing, and Unboxing.
     */
    private static void demonstrateWrapperClasses() {
        System.out.println("=== 1. Wrapper Classes Demonstration ===");
        
        // Autoboxing: Automatic conversion of primitive type to its corresponding Wrapper object
        int primitiveInt = 42;
        Integer wrapperInt = primitiveInt; 
        
        // Unboxing: Automatic conversion of Wrapper object back to its primitive type
        Double wrapperDouble = 3.14159;
        double primitiveDouble = wrapperDouble;

        System.out.println("Autoboxing (int -> Integer): " + wrapperInt);
        System.out.println("Unboxing (Double -> double): " + primitiveDouble);

        // Why do we need them? 
        // Java Data Structures (Collections) cannot store primitive types, only Objects.
        List<Integer> numbersList = new ArrayList<>();
        numbersList.add(100); // The primitive '100' is autoboxed into an Integer object here
        numbersList.add(200);
        
        System.out.println("ArrayList containing Wrapper Objects: " + numbersList);
    }

    /**
     * Demonstrates the immutability of String vs the mutability of StringBuffer.
     */
    private static void demonstrateStringVsStringBuffer() {
        System.out.println("=== 2. String vs StringBuffer Demonstration ===");
        
        /* * STRING: Immutable (cannot be changed once created)
         */
        String str = "Hello";
        System.out.println("--- String ---");
        System.out.println("Original String: " + str);
        System.out.println("Memory Location (HashCode): " + System.identityHashCode(str));
        
        // When we "modify" a String, Java actually creates a brand new object in memory.
        str = str + " World"; 
        System.out.println("Modified String: " + str);
        System.out.println("New Memory Location (HashCode): " + System.identityHashCode(str));
        System.out.println("** Result: The hash code changed. A new object was created. **\n");

        /* * STRINGBUFFER: Mutable (can be changed)
         */
        StringBuffer stringBuffer = new StringBuffer("Hello");
        System.out.println("--- StringBuffer ---");
        System.out.println("Original StringBuffer: " + stringBuffer);
        System.out.println("Memory Location (HashCode): " + System.identityHashCode(stringBuffer));
        
        // When we append to a StringBuffer, it alters the existing object without creating a new one.
        stringBuffer.append(" World"); 
        System.out.println("Modified StringBuffer: " + stringBuffer);
        System.out.println("Same Memory Location (HashCode): " + System.identityHashCode(stringBuffer));
        System.out.println("** Result: The hash code remained the same. The original object was updated. **");
    }
}
