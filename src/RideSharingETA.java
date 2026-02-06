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



class CityGraph
{

    private final Map<String, List<Edge>> graph;

    CityGraph(Map<String, List<Edge>> graph)
    {
        this.graph = graph;
    }

    int shortestDistance(String src, String dest)
    {

        if (src.equals(dest))
		    return 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
        Map<String, Integer> dist = new HashMap<>();

        pq.offer(new Node(src, 0));
        dist.put(src, 0);

        while (!pq.isEmpty())
        {
            Node cur = pq.poll();

            if (cur.name.equals(dest))
                return cur.dist;
            

            if (cur.dist > dist.get(cur.name))
                continue;

            for (Edge e : graph.getOrDefault(cur.name, List.of()))
            {
                int nextDist = cur.dist + e.time;

                if (nextDist < dist.getOrDefault(e.to, Integer.MAX_VALUE))
                {
                    dist.put(e.to, nextDist);
                    pq.offer(new Node(e.to, nextDist));
                }
            }
        }
		
        return Integer.MAX_VALUE;
    }

	private static class Node
	{
		String name;
	    int dist;
	
	    Node(String name, int dist)
		{
	        this.name = name;
	        this.dist = dist;
        }
    }
	
}


class DistanceCache
{

    private static final String SEP = "->";
	
    private final ConcurrentMap<String, Integer> cache = new ConcurrentHashMap<>();
	
    int getOrCompute(String from, String to, CityGraph graph)
	{
        String key = from + SEP + to;
        return cache.computeIfAbsent(key, k -> graph.shortestDistance(from, to));
    }
}


class RideSharingService
{

    private final CityGraph graph;
    private final DistanceCache cache = new DistanceCache();

    RideSharingService(CityGraph graph)
	{
        this.graph = graph;
    }

    Optional<Driver> findNearestDriver(User user, List<Driver> drivers)
	{
        return drivers.stream().min(Comparator.comparingInt( d -> cache.getOrCompute( d.location, user.pickup, graph) 
															)
								   );
		
    }

	int getETA(Driver driver, User user)
	{
        return cache.getOrCompute( driver.location, user.pickup, graph);
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



