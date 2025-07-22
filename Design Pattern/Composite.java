import java.util.ArrayList;
import java.util.List;

// Component
interface Employee {
    void showDetails();
}

// Leaf
class Developer implements Employee {
    private String name;

    public Developer(String name) {
        this.name = name;
    }

    public void showDetails() {

        System.out.println("Developer: " + name);
    }
}

// Leaf
class Designer implements Employee {
    private String name;

    public Designer(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("Designer: " + name);
    }
}

// Composite
class Manager implements Employee {
    private List<Employee> subordinates = new ArrayList<>();

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void showDetails() {
        for (Employee e : subordinates) {
            e.showDetails();
        }
    }
}

public class Composite {
    public static void Composite(String[] args) {
        Employee file1 = new Developer(".pdf");
        Employee file2 = new Designer("project.docx");

        Employee file3 = new Manager("Documents");
        folder1.add(file1);
        folder1.add(file2);

        Employee file3 = new File("photo.png");
        Folder rootFolder = new Folder("Root");
        rootFolder.add(folder1);
        rootFolder.add(file3);

        rootFolder.showDetails();
    }
}

