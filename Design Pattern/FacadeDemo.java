// Subsystem classes
class TV {
    public void on() { System.out.println("TV is ON"); }
    public void off() { System.out.println("TV is OFF"); }
    public void setInput(String input) { System.out.println("TV input set to " + input); }
}

class SoundSystem {
    public void on() { System.out.println("Sound system is ON"); }
    public void off() { System.out.println("Sound system is OFF"); }
    public void setVolume(int level) { System.out.println("Sound volume set to " + level); }
}

class DVDPlayer {
    public void on() { System.out.println("DVD Player is ON"); }
    public void off() { System.out.println("DVD Player is OFF"); }
    public void play(String movie) { System.out.println("Playing movie: " + movie); }
}

// Facade class
class HomeTheaterFacade {
    private TV tv;
    private SoundSystem sound;
    private DVDPlayer dvd;

    public HomeTheaterFacade() {
        tv = new TV();
        sound = new SoundSystem();
        dvd = new DVDPlayer();
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        tv.on();
        tv.setInput("DVD");
        sound.on();
        sound.setVolume(20);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        dvd.off();
        sound.off();
        tv.off();
    }
}

// Client code
public class FacadeDemo {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        homeTheater.watchMovie("Inception");
        System.out.println();
        homeTheater.endMovie();
    }
}
