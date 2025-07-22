import java.util.*;

// 🎯 Flyweight class: shared data like type, price
class RoomType {
    private String typeName;
    private double price;
    private boolean hasWiFi;
    private String bedType;

    public RoomType(String typeName, double price, boolean hasWiFi, String bedType) {
        this.typeName = typeName;
        this.price = price;
        this.hasWiFi = hasWiFi;
        this.bedType = bedType;
    }

    public void showDetails(String roomNumber) {
        System.out.println("🏨 Room Number: " + roomNumber);
        System.out.println("Type: " + typeName);
        System.out.println("Bed: " + bedType);
        System.out.println("WiFi: " + (hasWiFi ? "Yes" : "No"));
        System.out.println("Price: $" + price + " per night");
        System.out.println("---------------------------");
    }
}

// 🎯 Flyweight Factory
class RoomTypeFactory {
    private static Map<String, RoomType> roomTypeMap = new HashMap<>();

    public static RoomType getRoomType(String type, double price, boolean wifi, String bed) {
        String key = type + "-" + price + "-" + wifi + "-" + bed;
        if (!roomTypeMap.containsKey(key)) {
            roomTypeMap.put(key, new RoomType(type, price, wifi, bed));
        }
        return roomTypeMap.get(key);
    }

    public static int totalRoomTypes() {
        return roomTypeMap.size();
    }
}

// 🎯 Context class: each room has unique number
class Room {
    private String roomNumber;
    private RoomType roomType; // shared (Flyweight)

    public Room(String roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public void showRoom() {
        roomType.showDetails(roomNumber);
    }
}

// ✅ Client Simulation



public class Flyweight {
       public static void main(String[] args) {
        List<Room> hotelRooms = new ArrayList<>();

        // Simulate creating 1000 rooms of different types
        for (int i = 1; i <= 1000; i++) {
            RoomType type;
            if (i % 3 == 0) {
                type = RoomTypeFactory.getRoomType("Suite", 300.0, true, "King");
            } else if (i % 3 == 1) {
                type = RoomTypeFactory.getRoomType("Deluxe", 200.0, true, "Queen");
            } else {
                type = RoomTypeFactory.getRoomType("Economy", 100.0, false, "Single");
            }

            Room room = new Room("R-" + i, type);
            hotelRooms.add(room);
        }

        // Show details of few rooms
        hotelRooms.get(0).showRoom();
        hotelRooms.get(1).showRoom();
        hotelRooms.get(2).showRoom();

        System.out.println("✅ Total distinct RoomTypes created: " + RoomTypeFactory.totalRoomTypes());
    }
}
