import java.util.*;

// ✅ Common Component Interface
interface TaskComponent {
    void execute();
}

// ✅ Leaf: Simple Task
class SimpleTask implements TaskComponent {
    private String name;

    public SimpleTask(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("✅ Executing Task: " + name);
    }
}

// ✅ Composite: Task Group (can have sub-tasks)
class TaskGroup implements TaskComponent {
    private String name;
    private List<TaskComponent> tasks = new ArrayList<>();

    public TaskGroup(String name) {
        this.name = name;
    }

    public void addTask(TaskComponent task) {
        tasks.add(task);
    }

    public void removeTask(TaskComponent task) {
        tasks.remove(task);
    }

    @Override
    public void execute() {
        System.out.println("🔁 Starting Workflow Group: " + name);
        for (TaskComponent task : tasks) {
            task.execute();
        }
        System.out.println("✅ Finished Workflow Group: " + name);
    }
}

// ✅ Client: Workflow Engine

public class CompositePattern {
   public static void main(String[] args) {
        // Leaf Tasks
        TaskComponent t1 = new SimpleTask("Load Data");
        TaskComponent t2 = new SimpleTask("Validate Input");
        TaskComponent t3 = new SimpleTask("Clean Data");
        TaskComponent t4 = new SimpleTask("Train Model");
        TaskComponent t5 = new SimpleTask("Send Notification");

        // Group: Preprocessing
        TaskGroup preprocessGroup = new TaskGroup("Preprocessing");
        preprocessGroup.addTask(t1);
        preprocessGroup.addTask(t2);
        preprocessGroup.addTask(t3);

        // Group: Full Workflow
        TaskGroup fullWorkflow = new TaskGroup("ML Pipeline Workflow");
        fullWorkflow.addTask(preprocessGroup);   // nested group
        fullWorkflow.addTask(t4);
        fullWorkflow.addTask(t5);

        // Execute the full workflow
        fullWorkflow.execute();
    } 
}