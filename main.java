public class main {
    public static void main(String[] args) {
        System.out.println("=== Testing MyHashTable Implementation ===");
        testHashTable();

        System.out.println("\n=== Testing BST Implementation ===");
        testBST();
    }

    private static void testHashTable() {
        MyHashTable<Integer, String> hashTable = new MyHashTable<>();
        
        // Insert elements
        System.out.println("Adding elements to hash table...");
        hashTable.put(1, "Almaty");
        hashTable.put(12, "Astana");
        hashTable.put(23, "Shymkent");
        hashTable.put(34, "Taraz");
        hashTable.put(45, "Aktau");
        hashTable.put(56, "Atyrau");
        hashTable.put(67, "Karaganda");
        hashTable.put(78, "Semey");
        hashTable.put(89, "Aktobe");
        hashTable.put(90, "Kokshetau");

        // Display size
        System.out.println("Hash table size: " + hashTable.size());
        
        // Get elements
        System.out.println("Value for key 23: " + hashTable.get(23));
        System.out.println("Value for key 99 (should be null): " + hashTable.get(99));
        
        // Update element
        hashTable.put(45, "Updated Aktau");
        System.out.println("Updated value for key 45: " + hashTable.get(45));
        
        // Remove element
        System.out.println("Removing key 67...");
        String removed = hashTable.remove(67);
        System.out.println("Removed value: " + removed);
        System.out.println("Value for key 67 after removal (should be null): " + hashTable.get(67));
        
        // Contains check
        System.out.println("Contains 'Astana'? " + hashTable.contains("Astana"));
        System.out.println("Contains 'Oral'? " + hashTable.contains("Oral"));
        
        // Get key by value
        System.out.println("Key for value 'Astana': " + hashTable.getKey("Astana"));
        
        // Print bucket distribution
        System.out.println("\nBucket distribution:");
        hashTable.printBucketSizes();
        
        // Test with MyTestingClass
        System.out.println("\nTesting with MyTestingClass...");
        testWithCustomClass();
    }
    
    private static void testWithCustomClass() {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        
        // Create some test data
        MyTestingClass key1 = new MyTestingClass(1, "Qazaq1");
        MyTestingClass key2 = new MyTestingClass(2, "Qazaq2");
        MyTestingClass key3 = new MyTestingClass(3, "Qazaq3");
        
        Student student1 = new Student("Aibek", "Aliyev", 3.8);
        Student student2 = new Student("Dinara", "Nurmagambetov", 3.9);
        Student student3 = new Student("Marat", "Bekmukhambetova", 3.5);
        
        // Add to table
        table.put(key1, student1);
        table.put(key2, student2);
        table.put(key3, student3);
        
        // Retrieve and display
        System.out.println("Student with key Qazaq2: " + table.get(key2));
        
        // Remove and verify
        table.remove(key1);
        System.out.println("After removing key Qazaq1, value: " + table.get(key1));
        System.out.println("Table size: " + table.size());
    }
    
    private static void testBST() {
        BST<Integer, String> tree = new BST<>();
        
        // Insert elements
        System.out.println("Adding elements to BST...");
        tree.put(50, "Almaty");
        tree.put(30, "Astana");
        tree.put(70, "Shymkent");
        tree.put(20, "Taraz");
        tree.put(40, "Aktau");
        tree.put(60, "Atyrau");
        tree.put(80, "Karaganda");
        
        // Display size
        System.out.println("BST size: " + tree.size());
        
        // Get elements
        System.out.println("Value for key 40: " + tree.get(40));
        System.out.println("Value for key 90 (should be null): " + tree.get(90));
        
        // Update element
        tree.put(40, "Updated Aktau");
        System.out.println("Updated value for key 40: " + tree.get(40));
        
        // Delete element
        System.out.println("Deleting key 30...");
        tree.delete(30);
        System.out.println("Value for key 30 after deletion (should be null): " + tree.get(30));
        System.out.println("BST size after deletion: " + tree.size());
        
        // Iterate through all elements
        System.out.println("\nIterating through all elements in BST:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
    
    // Testing classes
    static class MyTestingClass {
        private int id;
        private String name;
        
        public MyTestingClass(int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + id;
            hash = 31 * hash + (name == null ? 0 : name.hashCode());
            return hash;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MyTestingClass that = (MyTestingClass) obj;
            return id == that.id && 
                   (name == null ? that.name == null : name.equals(that.name));
        }
        
        @Override
        public String toString() {
            return "MyTestingClass{id=" + id + ", name='" + name + "'}";
        }
    }
    
    static class Student {
        private String firstName;
        private String lastName;
        private double gpa;
        
        public Student(String firstName, String lastName, double gpa) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gpa = gpa;
        }
        
        @Override
        public String toString() {
            return "Student{" + firstName + " " + lastName + ", GPA=" + gpa + "}";
        }
    }
}