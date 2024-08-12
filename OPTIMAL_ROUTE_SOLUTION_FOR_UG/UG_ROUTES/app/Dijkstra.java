package app;

import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra {
    private static final ArrayList<Destination> NOT_VISITED = new ArrayList<>();

    private static final HashMap<Destination, Integer> DISTANCE_MAP = new HashMap<>();

    private static final HashMap<Destination, Destination> PREVIOUS_VERTEX = new HashMap<>();

    public static void findShortestPath(Digraph graph, Destination source, Destination destination) {
        if (source == destination) {
            System.out.print(source.getName());
        }

        for (Destination vertex : graph.getNodes()) {
            DISTANCE_MAP.put(vertex, Integer.MAX_VALUE);
            PREVIOUS_VERTEX.put(vertex, null);
            NOT_VISITED.add(vertex);
        }

        DISTANCE_MAP.put(source, 0);

        Destination minNode = findVertexWithMinDist();
        while (NOT_VISITED.size() > 0 && minNode != null) {
            minNode = findVertexWithMinDist();

            NOT_VISITED.remove(minNode);

            ArrayList<RouteDuration> edges = graph.getDestinationEdges(minNode);
            for (RouteDuration edge : edges) {
                // Checking for cycles: i.e., if we've not already visited this vertex.
                if (NOT_VISITED.contains(edge.getDestination())) {

                    // Calculate alternative cost
                    int alt = DISTANCE_MAP.get(minNode) + edge.getDistance();

                    if (alt < DISTANCE_MAP.get(edge.getDestination())) { // If the alternative cost is smaller than the
                                                                         // current cost.
                        // Update the min cost to reach this vertex.
                        DISTANCE_MAP.put(edge.getDestination(), alt);

                        // Update the previous vertex to reach this current vertex.
                        PREVIOUS_VERTEX.put(edge.getDestination(), minNode);
                    }
                }
            }
        }
        printShortestPath(source, destination);
        printDistances(destination);
    }

    private static void printDistances(Destination destination) {
        System.out.println("Approximate Distance: " +
                String.format("%.3f", DISTANCE_MAP.get(destination) / 1000F) + "km \n");
    }

    public static float getTotalDistance(Destination destination) {
        return DISTANCE_MAP.get(destination) / 1000F;
    }

    /*
     * private static void printPrevious() {
     * // Print each vertex and the previous vertex to reach it.
     * System.out.println("*****************************");
     * for (HashMap.Entry<Locations, Locations> entry : PREVIOUS_VERTEX.entrySet())
     * {
     * Locations vertex = entry.getKey();
     * Locations PREVIOUS_VERTEX = entry.getValue();
     * if (PREVIOUS_VERTEX != null)
     * System.out.println(vertex.getName() + " --> " + PREVIOUS_VERTEX.getName());
     * else {
     * System.out.println(vertex.getName() + " --> " + "naa");
     * }
     * }
     * }
     */

    public static String getShortestPath(Destination source, Destination destination) {
        ArrayList<Destination> path = new ArrayList<>();

        while (PREVIOUS_VERTEX.get(destination) != null) {
            path.add(destination);
            destination = PREVIOUS_VERTEX.get(destination);
        }
        path.add(source);

        StringBuilder builder = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            Destination vertex = path.get(i);
            builder.append(vertex.getName());
            builder.append(" to ");
        }
        return builder.toString();
    }

    private static void printShortestPath(Destination source, Destination destination) {
        // Using the preceding Vertex, re-create the path to your target/destination.
        System.out.println("\nShortest path from '" + source.getName() + "' to '" +
                destination.getName() + "'");
        ArrayList<Destination> path = new ArrayList<>();
        System.out.print(source.getName());
        while (PREVIOUS_VERTEX.get(destination) != null) {
            path.add(destination);
            destination = PREVIOUS_VERTEX.get(destination);
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            Destination vertex = path.get(i);
            System.out.print(" to " + vertex.getName());
        }
        System.out.println();
    }

    private static Destination findVertexWithMinDist() {
        // Linear search for the min cost vertex based on the distance.
        Destination minNode = null;
        long minDistance = Long.MAX_VALUE;
        for (HashMap.Entry<Destination, Integer> entry : DISTANCE_MAP.entrySet()) {
            Destination vertex = entry.getKey();
            int distance = entry.getValue();
            if (NOT_VISITED.contains(vertex) && distance < minDistance) {
                minDistance = distance;
                minNode = vertex;
            }
        }
        return minNode;
    }
}
