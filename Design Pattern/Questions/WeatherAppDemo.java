import java.util.*;

// ✅ Common Data Format
class WeatherData {
    private String location;
    private double temperature;
    private String condition;

    public WeatherData(String location, double temperature, String condition) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
    }

    public void display() {
        System.out.println("📍 Location: " + location);
        System.out.println("🌡 Temperature: " + temperature + "°C");
        System.out.println("☁ Condition: " + condition);
        System.out.println("---------------------------");
    }
}

// ✅ Target Interface
interface WeatherProvider {
    WeatherData getWeather(String location);
}

// ✅ Adaptee 1: OpenWeather API
class OpenWeatherAPI {
    public String getCityWeather(String city) {
        return city + ":15.5:Clear";  // Dummy response
    }
}

// ✅ Adaptee 2: AccuWeather API
class AccuWeatherAPI {
    public Map<String, Object> fetch(String region) {
        Map<String, Object> data = new HashMap<>();
        data.put("region", region);
        data.put("tempCelsius", 18.2);
        data.put("desc", "Cloudy");
        return data;
    }
}

// ✅ Adapter for OpenWeatherAPI
class OpenWeatherAdapter implements WeatherProvider {
    private OpenWeatherAPI openWeatherAPI;

    public OpenWeatherAdapter(OpenWeatherAPI api) {
        this.openWeatherAPI = api;
    }

    @Override
    public WeatherData getWeather(String location) {
        String raw = openWeatherAPI.getCityWeather(location);
        String[] parts = raw.split(":");
        return new WeatherData(parts[0], Double.parseDouble(parts[1]), parts[2]);
    }
}

// ✅ Adapter for AccuWeatherAPI
class AccuWeatherAdapter implements WeatherProvider {
    private AccuWeatherAPI accuWeatherAPI;

    public AccuWeatherAdapter(AccuWeatherAPI api) {
        this.accuWeatherAPI = api;
    }

    @Override
    public WeatherData getWeather(String location) {
        Map<String, Object> data = accuWeatherAPI.fetch(location);
        return new WeatherData(
                (String) data.get("region"),
                (Double) data.get("tempCelsius"),
                (String) data.get("desc")
        );
    }
}

// ✅ Client Class
class WeatherApp {
    private WeatherProvider provider;

    public WeatherApp(WeatherProvider provider) {
        this.provider = provider;
    }

    public void showWeather(String location) {
        WeatherData data = provider.getWeather(location);
        data.display();
    }
}

// ✅ Main Method (Run everything)
public class WeatherAppDemo {
    public static void main(String[] args) {
        // Using OpenWeather API
        WeatherProvider openProvider = new OpenWeatherAdapter(new OpenWeatherAPI());
        WeatherApp openWeatherApp = new WeatherApp(openProvider);
        openWeatherApp.showWeather("London");

        // Using AccuWeather API
        WeatherProvider accuProvider = new AccuWeatherAdapter(new AccuWeatherAPI());
        WeatherApp accuWeatherApp = new WeatherApp(accuProvider);
        accuWeatherApp.showWeather("New York");
    }
}
