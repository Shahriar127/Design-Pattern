package com.shajib.structural_flyweight;

import java.util.HashMap;
import java.util.Map;

interface Room {
    String getRoomType();
    int getPrice();
}

class RoomImpl implements Room {
    private final String roomType;
    private final int price;

    public RoomImpl(String roomType, int price) {
        this.roomType = roomType;
        this.price = price;
    }

    @Override
    public String getRoomType() {
        return roomType;
    }

    @Override
    public int getPrice() {
        return price;
    }
}

class RoomFactory {

    private static final Map<String, Room> rooms = new HashMap<>();

    public static Room getRoom(String roomType, int price) {
        String key = roomType + ":" + price;
        if (!rooms.containsKey(key)) {
          rooms.put(key, new RoomImpl(roomType, price));
        }
        return rooms.get(key);
    }
}


public class Structural_Flyweight {

    public static void main(String[] args) {
        Room room1 = RoomFactory.getRoom("Single", 100);
        Room room2 = RoomFactory.getRoom("Single", 100); // Will reuse existing object

        System.out.println(room1 == room2); // True, as they refer to the same object
    }
}
