import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// 1. The Data Object
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

public class CollectionsCRUDDemo {

    public static void main(String[] args) {
        demoArrayList();
        System.out.println("\n--------------------------------------------------\n");
        demoHashMap();
        System.out.println("\n--------------------------------------------------\n");
        demoTreeMap();
    }

    /**
     * ArrayList: An ordered, index-based, resizable array.
     * Best for: Scenarios where you need fast iteration and frequent read operations.
     */
    private static void demoArrayList() {
        System.out.println("=== 1. ArrayList CRUD Operations ===");
        List<Student> studentList = new ArrayList<>();

        // CREATE
        studentList.add(new Student(101, "Alice"));
        studentList.add(new Student(102, "Bob"));
        studentList.add(new Student(103, "Charlie"));
        System.out.println("After Create: " + studentList);

        // READ
        Student firstStudent = studentList.get(0); // Index-based retrieval
        System.out.println("Read Index 0: " + firstStudent);

        // UPDATE
        // Option A: Update the object directly if you have the reference
        firstStudent.setName("Alice Smith");
        // Option B: Replace the object at a specific index
        studentList.set(1, new Student(102, "Robert")); 
        System.out.println("After Update: " + studentList);

        // DELETE
        studentList.remove(2); // Remove by index (Charlie)
        // studentList.remove(firstStudent); // Could also remove by object reference
        System.out.println("After Delete: " + studentList);
    }

    /**
     * HashMap: A key-value store that uses hashing. Unordered.
     * Best for: Extremely fast (O(1)) lookups, insertions, and deletions using a unique key.
     */
    private static void demoHashMap() {
        System.out.println("=== 2. HashMap CRUD Operations ===");
        // Key: Student ID (Integer), Value: Student Object
        Map<Integer, Student> studentMap = new HashMap<>();

        // CREATE
        studentMap.put(101, new Student(101, "David"));
        studentMap.put(103, new Student(103, "Fiona"));
        studentMap.put(102, new Student(102, "Eve")); // Notice the out-of-order insertion
        System.out.println("After Create (Notice it is unordered): " + studentMap);

        // READ
        Student student102 = studentMap.get(102); // Retrieve by Key
        System.out.println("Read Key 102: " + student102);

        // UPDATE
        // Using put() with an existing key overwrites the old value
        studentMap.put(102, new Student(102, "Eve Adams"));
        // Alternatively, use replace() which only updates if the key already exists
        studentMap.replace(103, new Student(103, "Fiona Gallagher"));
        System.out.println("After Update: " + studentMap);

        // DELETE
        studentMap.remove(101); // Remove by Key
        System.out.println("After Delete: " + studentMap);
    }

    /**
     * TreeMap: A key-value store that implements Red-Black tree structure.
     * Best for: Scenarios where you need your key-value pairs sorted automatically by the key.
     */
    private static void demoTreeMap() {
        System.out.println("=== 3. TreeMap CRUD Operations ===");
        // Key: Student ID (Integer), Value: Student Object
        // TreeMap automatically sorts entries by their keys in ascending order.
        Map<Integer, Student> studentTreeMap = new TreeMap<>();

        // CREATE
        studentTreeMap.put(300, new Student(300, "George"));
        studentTreeMap.put(100, new Student(100, "Hannah"));
        studentTreeMap.put(200, new Student(200, "Ian"));
        System.out.println("After Create (Notice it is automatically sorted by Key): " + studentTreeMap);

        // READ
        Student student200 = studentTreeMap.get(200);
        System.out.println("Read Key 200: " + student200);

        // UPDATE
        studentTreeMap.replace(300, new Student(300, "George Washington"));
        System.out.println("After Update: " + studentTreeMap);

        // DELETE
        studentTreeMap.remove(100);
        System.out.println("After Delete: " + studentTreeMap);
    }
}
