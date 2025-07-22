import java.util.*;

// Observer Interface
interface Observer {
    void update(String eventType, String message);
}

// Concrete Observer: User
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String eventType, String message) {
        System.out.println("🔔 [" + name + "] received " + eventType + " notification: " + message);
    }
}

// Subject Interface
interface NotificationService {
    void subscribe(String eventType, Observer observer);
    void unsubscribe(String eventType, Observer observer);
    void notify(String eventType, String message);
}

// Concrete Subject
class SocialMediaNotificationService implements NotificationService {
    private Map<String, List<Observer>> observers = new HashMap<>();

    @Override
    public void subscribe(String eventType, Observer observer) {
        observers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(observer);
    }

    @Override
    public void unsubscribe(String eventType, Observer observer) {
        List<Observer> users = observers.get(eventType);
        if (users != null) {
            users.remove(observer);
        }
    }

    @Override
    public void notify(String eventType, String message) {
        List<Observer> users = observers.get(eventType);
        if (users != null) {
            for (Observer observer : users) {
                observer.update(eventType, message);
            }
        }
    }

    // Simulated event trigger
    public void post(String content) {
        notify("post", content);
    }

    public void live(String host) {
        notify("live", "🔴 " + host + " is live now!");
    }

    public void story(String storyTitle) {
        notify("story", "📸 New story uploaded: " + storyTitle);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        // Create service (publisher)
        SocialMediaNotificationService service = new SocialMediaNotificationService();

        // Create users (observers)
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // Subscriptions
        service.subscribe("post", alice);
        service.subscribe("live", alice);
        service.subscribe("story", bob);
        service.subscribe("post", charlie);

        // Triggering Events
        service.post("New blog post: Observer Pattern Explained!");
        service.live("DevTips");
        service.story("Beach Vlog 🌴");
    }
}
