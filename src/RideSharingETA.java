import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DriverETA {
    Driver driver;
    int eta;

    DriverETA(Driver driver, int eta) {
        this.driver = driver;
        this.eta = eta;
    }
}

class Edge
{
    String destination;
    int travelTime;

    Edge(String destination, int travelTime
    {
        this.destination = destination;
        this.travelTime = travelTime;
    }
}

class Driver{
    String id;
    String currentLocation;

    Driver(String id, String currentLocation)
    {
        this.id = id;
        this.currentLocation = currentLocation;
    }
}

class User
{
    String id;
    String pickupLocation;

    User(String id, String pickupLocation)
    {
        this.id = id;
        this.pickupLocation = pickupLocation;
    }
}

class RideSharingETA
{
    public static void main(String[] args) throws InterruptedException
    {
        Map<String, List<Edge>> cityMap = new HashMap<>();
        cityMap.put("A", Arrays.asList(new Edge("B", 5), new Edge("C", 10)));
        cityMap.put("B", Arrays.asList(new Edge("A", 5), new Edge("C", 3), new Edge("D", 7)));
        cityMap.put("C", Arrays.asList(new Edge("A", 10), new Edge("B", 3), new Edge("D", 1)));
        cityMap.put("D", Arrays.asList(new Edge("B", 7), new Edge("C", 1)));

        List<Driver> drivers = Arrays.asList(
                new Driver("Driver1", "A"),
                new Driver("Driver2", "B"),
                new Driver("Driver3", "C")
        );

         List<User> users = Arrays.asList(
                new User("User1", "D"),
                new User("User2", "C")
        );

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (User user : users) {
            executor.submit(() -> {
                Driver nearestDriver = findNearestDriver(user, drivers, cityMap);
                int eta = computeDistance(nearestDriver.currentLocation, user.pickupLocation, cityMap);
                System.out.println("Nearest driver to " + user.id + " is " + nearestDriver.id + " with ETA " + eta + " mins");
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
