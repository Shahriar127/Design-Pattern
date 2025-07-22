
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}


class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV is turned ON.");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is turned OFF.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("TV volume set to " + volume);
    }
}

class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Radio is turned ON.");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is turned OFF.");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Radio volume set to " + volume);
    }
}


abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void turnOn();
    public abstract void turnOff();
    public abstract void setVolume(int volume);
}


class BasicRemote extends RemoteControl {

    public BasicRemote(Device device) {
        super(device);
    }

    @Override
    public void turnOn() {
        device.turnOn();
    }

    @Override
    public void turnOff() {
        device.turnOff();
    }

    @Override
    public void setVolume(int volume) {
        device.setVolume(volume);
    }
}


public class BridgePatternDemo {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remote = new BasicRemote(tv);

        remote.turnOn();
        remote.setVolume(10);
        remote.turnOff();

        System.out.println("---");

        Device radio = new Radio();
        remote = new BasicRemote(radio);

        remote.turnOn();
        remote.setVolume(5);
        remote.turnOff();
    }
}