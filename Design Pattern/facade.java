class CPU {
    public void start() {
        System.out.println("CPU start");
    }
}

class Memory {
    public void load() {
        System.out.println("Memory Load");
    }
}

class HardDrive {
    public void read() {
        System.out.println("Read from hard Drive");
    }
}

class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        cpu = new CPU();
        memory = new Memory();
        hardDrive = new HardDrive();
    }

    public void startComputer() {
        System.out.println("Starting the computer...");
        cpu.start();
        memory.load();
        hardDrive.read();
    }
}

public class facade {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}

