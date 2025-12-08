import java.io.*;
import java.util.Random;

public class LinkedList{
    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("        STUDENT LINKED LIST IMPLEMENTATION & ANALYSIS");
        System.out.println("        CCC2133: Data Structure and Algorithm Analysis");
        System.out.println("================================================================");
        
        StudentLinkedList studentList = new StudentLinkedList();
        
        System.out.println("\n=== TEST CASE 1: Basic Operations with File I/O ===");
        
        String csvFile = "students.csv";
        
        try {
            File file = new File(csvFile);
            if (file.exists()) {
                studentList.loadFromCSV(csvFile);
                System.out.println("Current list size: " + studentList.getSize());
            } else {
                System.out.println("CSV file not found. Creating sample data for testing...");
                studentList.addLast("Olivia Chen", "CS101", 3, 92.5, 3.85);
                studentList.addLast("Liam Johnson", "CS102", 4, 87.0, 3.60);
                studentList.addLast("Sophia Williams", "CS103", 2, 78.5, 3.15);
                System.out.println("Created 3 sample students.");
            }
            
            studentList.display();
            
            System.out.println("\n--- Testing addFirst() ---");
            studentList.addFirst("Test First", "TEST101", 3, 95.0, 4.00);
            System.out.println("After addFirst, list size: " + studentList.getSize());
            
            System.out.println("\n--- Testing insertAt() ---");
            studentList.insertAt(2, "Bob Brown", "CS104", 3, 85.0, 3.50);
            System.out.println("After inserting Bob Brown at index 2, size: " + studentList.getSize());
            
            System.out.println("\n--- Testing search() ---");
            String searchName = "Bob Brown";
            int index = studentList.search(searchName);
            if (index != -1) {
                System.out.println("Found '" + searchName + "' at index: " + index);
            } else {
                System.out.println("'" + searchName + "' not found in the list.");
            }
            
            System.out.println("\n--- Testing delete operations ---");
            System.out.println("Deleting first student...");
            studentList.deleteFirst();
            System.out.println("List size after deleteFirst: " + studentList.getSize());
            
            System.out.println("Deleting last student...");
            studentList.deleteLast();
            System.out.println("List size after deleteLast: " + studentList.getSize());
            
            System.out.println("Deleting student at index 1...");
            studentList.deleteAt(1);
            System.out.println("List size after deleteAt(1): " + studentList.getSize());
            
            studentList.display();
            
            System.out.println("\n=== TEST CASE 2: Edge Cases ===");
            
            StudentLinkedList emptyList = new StudentLinkedList();
            System.out.println("\n--- Testing empty list operations ---");
            emptyList.deleteFirst();
            emptyList.deleteLast();
            emptyList.display();
            
            System.out.println("\n--- Testing invalid index operations ---");
            studentList.insertAt(100, "Invalid Student", "CS999", 3, 50.0, 2.0);
            studentList.deleteAt(100);
            
            System.out.println("\n--- Testing search for non-existent student ---");
            int result = studentList.search("Non Existent Student");
            if (result == -1) {
                System.out.println("Correctly returned -1 for non-existent student.");
            }
            
            System.out.println("\n--- Testing single node list ---");
            StudentLinkedList singleList = new StudentLinkedList();
            singleList.addFirst("Single Student", "CS001", 3, 90.0, 3.8);
            System.out.println("Single list size: " + singleList.getSize());
            singleList.deleteFirst();
            System.out.println("After deleteFirst, size: " + singleList.getSize());
            
            System.out.println("\n=== TEST CASE 3: Performance Testing ===");
            System.out.println("Creating large list for performance testing...");
            
            StudentLinkedList performanceList = new StudentLinkedList();
            
            // Create 1000 students manually (no java.util.LinkedList used)
            Random random = new Random();
            String[] firstNames = {"John", "Jane", "Bob", "Alice", "Charlie"};
            String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones"};
            String[] courses = {"CS101", "CS102", "CS201", "CS202"};
            
            long startTime = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                String name = firstNames[random.nextInt(firstNames.length)] + " " + 
                             lastNames[random.nextInt(lastNames.length)];
                String course = courses[random.nextInt(courses.length)];
                int credit = random.nextInt(4) + 1;
                double grade = 60 + random.nextDouble() * 40;
                double cgpa = 2.0 + random.nextDouble() * 2.0;
                
                performanceList.addLast(name, course, credit, grade, cgpa);
            }
            long endTime = System.nanoTime();
            System.out.println("Generated 1000 students in " + (endTime - startTime) + " ns");
            
            System.out.println("\n--- Performance Measurements (using System.nanoTime()) ---");
            
            // Measure addFirst
            startTime = System.nanoTime();
            performanceList.addFirst("Performance Test", "PERF101", 3, 90.0, 3.7);
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "addFirst", (endTime - startTime), performanceList.getSize());
            
            // Measure addLast
            startTime = System.nanoTime();
            performanceList.addLast("Performance Test", "PERF102", 4, 85.0, 3.5);
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "addLast", (endTime - startTime), performanceList.getSize());
            
            // Measure insertAt middle
            startTime = System.nanoTime();
            performanceList.insertAt(500, "Middle Insert", "MID101", 2, 80.0, 3.2);
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "insertAt(500)", (endTime - startTime), performanceList.getSize());
            
            // Measure search (worst case - not found)
            startTime = System.nanoTime();
            performanceList.search("NonExistentStudentXYZ");
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "search (not found)", (endTime - startTime), performanceList.getSize());
            
            // Measure deleteFirst
            startTime = System.nanoTime();
            performanceList.deleteFirst();
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "deleteFirst", (endTime - startTime), performanceList.getSize());
            
            // Measure deleteLast
            startTime = System.nanoTime();
            performanceList.deleteLast();
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "deleteLast", (endTime - startTime), performanceList.getSize());
            
            // Measure deleteAt middle
            startTime = System.nanoTime();
            performanceList.deleteAt(250);
            endTime = System.nanoTime();
            System.out.printf("Operation: %-20s | Time: %-10d ns | Size: %d\n", 
                "deleteAt(250)", (endTime - startTime), performanceList.getSize());
            
            System.out.println("\n--- End of Performance Tests ---");
            
            System.out.println("\n=== Saving modified data to CSV ===");
            studentList.saveToCSV("students_modified.csv");
            
            System.out.println("\n=== Final State of Original List ===");
            studentList.display();
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n================================================================");
        System.out.println("           PROGRAM EXECUTION COMPLETED SUCCESSFULLY");
        System.out.println("================================================================");
    }
}

class StudentNode {
    private String name;
    private String course;
    private int credit;
    private double grade;
    private double cgpa;
    private StudentNode next;
    
    public StudentNode(String name, String course, int credit, double grade, double cgpa) {
        this.name = name;
        this.course = course;
        this.credit = credit;
        this.grade = grade;
        this.cgpa = cgpa;
        this.next = null;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public int getCredit() { return credit; }
    public void setCredit(int credit) { this.credit = credit; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
    public double getCgpa() { return cgpa; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }
    public StudentNode getNext() { return next; }
    public void setNext(StudentNode next) { this.next = next; }
    
    @Override
    public String toString() {
        return String.format("%-20s %-10s %-4d %-6.1f %-5.2f", 
            name, course, credit, grade, cgpa);
    }
}

class StudentLinkedList {
    private StudentNode head;
    private StudentNode tail;
    private int size;
    
    public StudentLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void addFirst(String name, String course, int credit, double grade, double cgpa) {
        StudentNode newNode = new StudentNode(name, course, credit, grade, cgpa);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }
    
    public void addLast(String name, String course, int credit, double grade, double cgpa) {
        StudentNode newNode = new StudentNode(name, course, credit, grade, cgpa);
        
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }
    
    public boolean insertAt(int index, String name, String course, int credit, double grade, double cgpa) {
        if (index < 0 || index > size) {
            System.out.println("Error: Index " + index + " out of bounds. List size: " + size);
            return false;
        }
        
        if (index == 0) {
            addFirst(name, course, credit, grade, cgpa);
            return true;
        }
        
        if (index == size) {
            addLast(name, course, credit, grade, cgpa);
            return true;
        }
        
        StudentNode newNode = new StudentNode(name, course, credit, grade, cgpa);
        StudentNode current = head;
        
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
        return true;
    }
    
    public StudentNode deleteFirst() {
        if (isEmpty()) {
            System.out.println("Error: Cannot delete from an empty list.");
            return null;
        }
        
        StudentNode deletedNode = head;
        head = head.getNext();
        size--;
        
        if (head == null) {
            tail = null;
        }
        
        return deletedNode;
    }
    
    public StudentNode deleteLast() {
        if (isEmpty()) {
            System.out.println("Error: Cannot delete from an empty list.");
            return null;
        }
        
        if (size == 1) {
            return deleteFirst();
        }
        
        StudentNode current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        
        StudentNode deletedNode = tail;
        tail = current;
        tail.setNext(null);
        size--;
        
        return deletedNode;
    }
    
    public StudentNode deleteAt(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error: Index " + index + " out of bounds. List size: " + size);
            return null;
        }
        
        if (index == 0) {
            return deleteFirst();
        }
        
        if (index == size - 1) {
            return deleteLast();
        }
        
        StudentNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        
        StudentNode deletedNode = current.getNext();
        current.setNext(deletedNode.getNext());
        size--;
        
        return deletedNode;
    }
    
    public int search(String name) {
        if (isEmpty()) {
            return -1;
        }
        
        StudentNode current = head;
        int index = 0;
        
        while (current != null) {
            if (current.getName().equalsIgnoreCase(name)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        
        return -1;
    }
    
    public void display() {
        if (isEmpty()) {
            System.out.println("The student list is empty.");
            return;
        }
        
        System.out.println("\n==================================================================");
        System.out.println("                         STUDENT LIST");
        System.out.println("==================================================================");
        System.out.printf("%-20s %-10s %-6s %-6s %-5s\n", 
            "Name", "Course", "Cred", "Grade", "CGPA");
        System.out.println("------------------------------------------------------------------");
        
        StudentNode current = head;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
        System.out.println("==================================================================\n");
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String name = data[0].trim();
                    String course = data[1].trim();
                    int credit = Integer.parseInt(data[2].trim());
                    double grade = Double.parseDouble(data[3].trim());
                    double cgpa = Double.parseDouble(data[4].trim());
                    
                    addLast(name, course, credit, grade, cgpa);
                }
            }
            System.out.println("Successfully loaded " + size + " students from " + filename);
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: File '" + filename + "' not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number in CSV: " + e.getMessage());
        }
    }
    
    public void saveToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("name,course,credit,grade,cgpa");
            
            StudentNode current = head;
            while (current != null) {
                writer.printf("%s,%s,%d,%.1f,%.2f\n",
                    current.getName(),
                    current.getCourse(),
                    current.getCredit(),
                    current.getGrade(),
                    current.getCgpa());
                current = current.getNext();
            }
            System.out.println("Successfully saved " + size + " students to " + filename);
            
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}

