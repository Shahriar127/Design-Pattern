import java.util.*;

// Common interface
interface FileSystemItem {
    void showDetails();
}

// Leaf class
class File implements FileSystemItem {
    private String name;
    private int size; // KB

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name + " (" + size + "KB)");
    }
}

// Composite class
class Folder implements FileSystemItem {
    private String name;
    private List<FileSystemItem> items = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addItem(FileSystemItem item) {
        items.add(item);
    }

    public void removeItem(FileSystemItem item) {
        items.remove(item);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemItem item : items) {
            item.showDetails();
        }
    }
}

// Main class
public class CompositeDemo {
    public static void main(String[] args) {
        File file1 = new File("file1.txt", 10);
        File file2 = new File("file2.jpg", 500);
        File file3 = new File("file3.pdf", 200);

        Folder folder1 = new Folder("Documents");
        folder1.addItem(file1);
        folder1.addItem(file2);

        Folder folder2 = new Folder("Downloads");
        folder2.addItem(file3);

        Folder rootFolder = new Folder("Root");
        rootFolder.addItem(folder1);
        rootFolder.addItem(folder2);

        rootFolder.showDetails();
    }
}
