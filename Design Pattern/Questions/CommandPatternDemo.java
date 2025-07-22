// CommandPatternDemo.java

// Step 1: Command interface
interface Command {
    void execute();
    void undo();
}

// Step 2: Receiver
class Light {
    public void on() {
        System.out.println("💡 Light is ON");
    }

    public void off() {
        System.out.println("💡 Light is OFF");
    }
}

// Step 3: Concrete Commands
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}

// Step 4: Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// Step 5: Client code
public class CommandPatternDemo {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();    // 💡 Light is ON
        remote.pressUndo();      // 💡 Light is OFF

        remote.setCommand(lightOff);
        remote.pressButton();    // 💡 Light is OFF
        remote.pressUndo();      // 💡 Light is ON
    }
}
