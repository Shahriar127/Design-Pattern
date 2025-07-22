package com.shajib.structural_proxy;

import java.util.HashMap;
import java.util.Map;

// Interface for Data Source
interface DataSource {
    public String fetchData(String key);
}

// Real Data Source class that fetches data from the actual source
class RealDataSource implements DataSource {
    @Override
    public String fetchData(String key) {
        // Simulate fetching data from a real data source (e.g., database, web service)
        return "Data for " + key;
    }
}

// Proxy class that implements caching
class CachedDataSourceProxy implements DataSource {
    private final RealDataSource realDataSource;
    private final Map<String, String> cache;

    public CachedDataSourceProxy() {
        this.realDataSource = new RealDataSource();
        this.cache = new HashMap<>();
    }

    @Override
    public String fetchData(String key) {
        if (cache.containsKey(key)) {
            System.out.println("Cache hit for key: " + key);
            return cache.get(key);
        }
        System.out.println("Cache miss for key: " + key);
        String data = realDataSource.fetchData(key);
        cache.put(key, data);
        return data;
    }
}


public class Structural_Proxy {

    public static void main(String[] args) {
        DataSource dataSource = new CachedDataSourceProxy();

        // Fetch data for the first time (cache miss)
        System.out.println(dataSource.fetchData("key1"));

        // Fetch data for the second time (cache hit)
        System.out.println(dataSource.fetchData("key1"));
    }
}
