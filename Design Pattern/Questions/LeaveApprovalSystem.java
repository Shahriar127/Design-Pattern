// LeaveApprovalSystem.java

// Step 1: Request class
class LeaveRequest {
    private String employeeName;
    private int numberOfDays;

    public LeaveRequest(String employeeName, int numberOfDays) {
        this.employeeName = employeeName;
        this.numberOfDays = numberOfDays;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }
}

// Step 2: Handler interface
abstract class LeaveApprover {
    protected LeaveApprover nextApprover;

    public void setNextApprover(LeaveApprover nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract void approveLeave(LeaveRequest request);
}

// Step 3: Concrete Handlers
class Manager extends LeaveApprover {
    public void approveLeave(LeaveRequest request) {
        if (request.getNumberOfDays() <= 5) {
            System.out.println("Manager approved leave for " + request.getEmployeeName() + " for " + request.getNumberOfDays() + " days.");
        } else if (nextApprover != null) {
            nextApprover.approveLeave(request);
        }
    }
}

class Director extends LeaveApprover {
    public void approveLeave(LeaveRequest request) {
        if (request.getNumberOfDays() <= 10) {
            System.out.println("Director approved leave for " + request.getEmployeeName() + " for " + request.getNumberOfDays() + " days.");
        } else if (nextApprover != null) {
            nextApprover.approveLeave(request);
        }
    }
}

class CEO extends LeaveApprover {
    public void approveLeave(LeaveRequest request) {
        System.out.println("CEO approved leave for " + request.getEmployeeName() + " for " + request.getNumberOfDays() + " days.");
    }
}

// Step 4: Client code
public class LeaveApprovalSystem {
    public static void main(String[] args) {
        // Create the chain: Manager → Director → CEO
        LeaveApprover manager = new Manager();
        LeaveApprover director = new Director();
        LeaveApprover ceo = new CEO();

        manager.setNextApprover(director);
        director.setNextApprover(ceo);

        // Sample leave requests
        LeaveRequest req1 = new LeaveRequest("Alice", 3);
        LeaveRequest req2 = new LeaveRequest("Bob", 7);
        LeaveRequest req3 = new LeaveRequest("Charlie", 12);

        // Start from the first approver
        manager.approveLeave(req1);   // Handled by Manager
        manager.approveLeave(req2);   // Handled by Director
        manager.approveLeave(req3);   // Handled by CEO
    }
}
