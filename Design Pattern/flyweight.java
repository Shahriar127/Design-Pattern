import java.util.*;

// Flyweight: Shared bullet properties
class BulletType {
    private final String sprite;
    private final int damage;
    private final double speed;

    public BulletType(String sprite, int damage, double speed) {
        this.sprite = sprite;
        this.damage = damage;
        this.speed = speed;
    }

    public void draw(double x, double y, double angle) {
        System.out.printf("Bullet [%s] at (%.1f, %.1f) angle %.1f° damage %d speed %.1f\n",
                sprite, x, y, angle, damage, speed);
    }
}

// Flyweight Factory
class BulletFactory {
    private static final Map<String, BulletType> bulletTypes = new HashMap<>();

    public static BulletType getBulletType(String sprite, int damage, double speed) {
        String key = sprite + "-" + damage + "-" + speed;
        bulletTypes.putIfAbsent(key, new BulletType(sprite, damage, speed));
        return bulletTypes.get(key);
    }
}

// Context class with extrinsic state
class Bullet {
    private final double x, y;
    private final double angle;
    private final BulletType type;

    public Bullet(double x, double y, double angle, BulletType type) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y, angle);
    }
}

// Game manager
class GameWorld {
    private final List<Bullet> bullets = new ArrayList<>();

    public void shoot(double x, double y, double angle, String sprite, int damage, double speed) {
        BulletType type = BulletFactory.getBulletType(sprite, damage, speed);
        bullets.add(new Bullet(x, y, angle, type));
    }

    public void render() {
        for (Bullet b : bullets) {
            b.draw();
        }
    }
}

// Main class
public class flyweight {
    public static void main(String[] args) {
        GameWorld world = new GameWorld();

        // Shoot 5 red bullets
        for (int i = 0; i < 5; i++) {
            world.shoot(100 + i * 10, 200, 0, "red", 10, 5.0);
        }

        // Shoot 3 green bullets
        for (int i = 0; i < 3; i++) {
            world.shoot(300, 100 + i * 15, 45, "green", 20, 4.5);
        }

        // Draw everything
        world.render();
    }
}
