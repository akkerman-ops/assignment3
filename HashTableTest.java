import java.util.Random;

public class HashTableTest {
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
    
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        
        Random random = new Random(42);
        String[] kazakhFirstNames = {"Aibek", "Aidar", "Dinara", "Marat", "Saule", "Zhanar", "Nurlan", "Aisulu", 
                                    "Arman", "Aliya", "Damir", "Azamat", "Anara", "Timur", "Gulnaz"};
        String[] kazakhLastNames = {"Aliyev", "Nurmagambetov", "Bekmukhambetova", "Omarov", "Suleimenova",
                                   "Iskakov", "Dusembayeva", "Auezov", "Nazarbayeva", "Tokayev", "Zhumabayeva"};
        
        System.out.println("Adding 10000 elements to hash table...");
        
        for (int i = 0; i < 10000; i++) {
            int id = i;
            String name = "Qazaq" + i;
            MyTestingClass key = new MyTestingClass(id, name);
            
            String firstName = kazakhFirstNames[random.nextInt(kazakhFirstNames.length)];
            String lastName = kazakhLastNames[random.nextInt(kazakhLastNames.length)];
            double gpa = 2.0 + random.nextDouble() * 2.0;
            Student value = new Student(firstName, lastName, gpa);
            
            table.put(key, value);
        }
        
        System.out.println("Total elements in hash table: " + table.size());
        System.out.println("\nBucket distribution:");
        table.printBucketSizes();
        
        MyTestingClass testKey = new MyTestingClass(42, "Qazaq42");
        Student student = table.get(testKey);
        System.out.println("\nRetrieving student with key " + testKey + ": " + student);
        
        table.remove(testKey);
        System.out.println("After removing key " + testKey + ", get returns: " + table.get(testKey));
        
        testBST();
    }
    
    private static void testBST() {
        System.out.println("\n--- Testing BST Implementation ---");
        BST<Integer, String> tree = new BST<>();
        
        tree.put(50, "Almaty");
        tree.put(30, "Astana");
        tree.put(70, "Shymkent");
        tree.put(20, "Taraz");
        tree.put(40, "Aktau");
        tree.put(60, "Atyrau");
        tree.put(80, "Karaganda");
        
        System.out.println("Tree size: " + tree.size());
        
        System.out.println("Value for key 40: " + tree.get(40));
        System.out.println("Value for key 90 (should be null): " + tree.get(90));
        
        tree.delete(30);
        System.out.println("After deleting key 30, value: " + tree.get(30));
        System.out.println("New tree size: " + tree.size());
        
        System.out.println("\nIterating through all elements in order:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}