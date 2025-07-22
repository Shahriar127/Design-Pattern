// ChatRoomMediatorDemo.java

import java.util.*;

// Step 1: Mediator Interface
interface ChatRoom {
    void sendMessage(String from, String to, String message);
    void broadcast(String from, String message);
    void registerUser(User user);
}

// Step 2: Concrete Mediator
class ChatRoomImpl implements ChatRoom {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getName(), user);
        System.out.println(user.getName() + " joined the chat room.");
    }

    public void sendMessage(String from, String to, String message) {
        User receiver = users.get(to);
        if (receiver != null) {
            receiver.receive(from, message);
        } else {
            System.out.println("User " + to + " not found.");
        }
    }

    public void broadcast(String from, String message) {
        for (User user : users.values()) {
            if (!user.getName().equals(from)) {
                user.receive(from, message);
            }
        }
    }
}

// Step 3: Colleague (User)
abstract class User {
    protected ChatRoom chatRoom;
    protected String name;

    public User(ChatRoom chatRoom, String name) {
        this.chatRoom = chatRoom;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String to, String message);
    public abstract void sendToAll(String message);
    public abstract void receive(String from, String message);
}

// Step 4: Concrete User
class ChatUser extends User {
    public ChatUser(ChatRoom chatRoom, String name) {
        super(chatRoom, name);
    }

    public void send(String to, String message) {
        System.out.println(name + " sends to " + to + ": " + message);
        chatRoom.sendMessage(name, to, message);
    }

    public void sendToAll(String message) {
        System.out.println(name + " broadcasts: " + message);
        chatRoom.broadcast(name, message);
    }

    public void receive(String from, String message) {
        System.out.println(name + " received from " + from + ": " + message);
    }
}

// Step 5: Client Code
public class ChatRoomMediatorDemo {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoomImpl();

        User alice = new ChatUser(chatRoom, "Alice");
        User bob = new ChatUser(chatRoom, "Bob");
        User charlie = new ChatUser(chatRoom, "Charlie");

        chatRoom.registerUser(alice);
        chatRoom.registerUser(bob);
        chatRoom.registerUser(charlie);

        alice.send("Bob", "Hi Bob!");
        bob.send("Alice", "Hey Alice!");
        charlie.sendToAll("Hello everyone!");
    }
}
