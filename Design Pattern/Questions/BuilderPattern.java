// Car.java
class Car {
    String engine;
    String wheels;
    String gps;
    String transmission;
    String body;

    public void showSpecifications() {
        System.out.println("🚗 Car Configuration:");
        System.out.println("Engine       : " + engine);
        System.out.println("Wheels       : " + wheels);
        System.out.println("Transmission : " + transmission);
        System.out.println("GPS          : " + gps);
        System.out.println("Body         : " + body);
        System.out.println("---------------------------");
    }
}

// Builder Interface
interface CarBuilder {
    void buildEngine();
    void buildWheels();
    void buildTransmission();
    void buildGPS();
    void buildBody();
    Car getCar();
}

// Concrete Builder 1: Sports Car
class SportsCarBuilder implements CarBuilder {
    private Car car = new Car();

    public void buildEngine() {
        car.engine = "3.0L Turbocharged V6";
    }

    public void buildWheels() {
        car.wheels = "18\" Alloy Racing Wheels";
    }

    public void buildTransmission() {
        car.transmission = "7-Speed Dual-Clutch";
    }

    public void buildGPS() {
        car.gps = "High-Performance GPS";
    }

    public void buildBody() {
        car.body = "Aerodynamic Carbon Fiber";
    }

    public Car getCar() {
        return car;
    }
}

// Concrete Builder 2: SUV
class SUVCarBuilder implements CarBuilder {
    private Car car = new Car();

    public void buildEngine() {
        car.engine = "2.5L Hybrid Engine";
    }

    public void buildWheels() {
        car.wheels = "20\" Off-Road Wheels";
    }

    public void buildTransmission() {
        car.transmission = "6-Speed Automatic";
    }

    public void buildGPS() {
        car.gps = "Touchscreen GPS with Voice Assist";
    }

    public void buildBody() {
        car.body = "Reinforced Steel SUV Frame";
    }

    public Car getCar() {
        return car;
    }
}

// Director: Orchestrates building process
class CarDirector {
    private CarBuilder builder;

    public CarDirector(CarBuilder builder) {
        this.builder = builder;
    }

    public Car constructCar() {
        builder.buildEngine();
        builder.buildWheels();
        builder.buildTransmission();
        builder.buildGPS();
        builder.buildBody();
        return builder.getCar();
    }
}

// Client Code



public class BuilderPattern {
        public static void main(String[] args) {
        // Build Sports Car
        CarBuilder sportsBuilder = new SportsCarBuilder();
        CarDirector sportsDirector = new CarDirector(sportsBuilder);
        Car sportsCar = sportsDirector.constructCar();
        sportsCar.showSpecifications();

        // Build SUV
        CarBuilder suvBuilder = new SUVCarBuilder();
        CarDirector suvDirector = new CarDirector(suvBuilder);
        Car suv = suvDirector.constructCar();
        suv.showSpecifications();
    }
}
