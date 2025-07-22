import java.util.*;

// ✅ Subject interface
interface DataSource {
    String getData(String key);
}

// ✅ Real Subject (e.g., slow DB/API)
class RealDataSource implements DataSource {
    @Override
    public String getData(String key) {
        System.out.println("📡 Fetching from Real Data Source for key: " + key);
        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Data for " + key;
    }
}

// ✅ Proxy with caching
class CacheProxy implements DataSource {
    private RealDataSource realDataSource = new RealDataSource();
    private Map<String, String> cache = new HashMap<>();

    @Override
    public String getData(String key) {
        if (cache.containsKey(key)) {
            System.out.println("⚡ Returning from cache for key: " + key);
            return cache.get(key);
        }

        String data = realDataSource.getData(key);
        cache.put(key, data);
        return data;
    }
}

// ✅ Client code

public class Proxy {
      public static void main(String[] args) {
        DataSource dataSource = new CacheProxy();

        System.out.println(dataSource.getData("user123")); // From real source
        System.out.println(dataSource.getData("product45")); // From real source
        System.out.println(dataSource.getData("user123")); // From cache
        System.out.println(dataSource.getData("user123")); // From cache
    }
}
