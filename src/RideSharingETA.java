class Edge
{
    final String to;
    final int time;

    Edge(String to, int time)
    {
        this.to = to;
        this.time = time;
    }
}

class Driver
{
    final String id;
    final String location;

    Driver(String id, String location)
    {
        this.id = id;
        this.location = location;
    }
}

class User
{
    final String id;
    final String pickup;

    User(String id, String pickup)
    {
        this.id = id;
        this.pickup = pickup;
    }
}











public class RideSharingETA
{

    public static void main(String[] args) throws InterruptedException
    {

        Map<String, List<Edge>> city = new HashMap<>();
        
        city.put("A", List.of(new Edge("B", 5), new Edge("C", 10)));
        city.put("B", List.of(new Edge("A", 5), new Edge("C", 3), new Edge("D", 7)));
        city.put("C", List.of(new Edge("A", 10), new Edge("B", 3), new Edge("D", 1)));
        city.put("D", List.of(new Edge("B", 7), new Edge("C", 1)));

        CityGraph graph = new CityGraph(city);
        RideSharingService service = new RideSharingService(graph);

        List<Driver> drivers = List.of(
                new Driver("D1", "A"),
                new Driver("D2", "B"),
                new Driver("D3", "C")
        );

        List<User> users = List.of(
                new User("U1", "D"),
                new User("U2", "C")
        );








        
    }
}



