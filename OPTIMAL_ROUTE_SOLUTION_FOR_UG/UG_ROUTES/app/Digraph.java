package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Digraph {
    protected final HashMap<Destination, ArrayList<Destination>> map = new HashMap<>();
    protected final ArrayList<RouteDuration> ghu = new ArrayList<>();
    private int vertexSize = 0;

    public void createVertex(Destination vertex) {
        if (!map.containsKey(vertex)) {
            map.put(vertex, new ArrayList<>());
            vertexSize++;
        }
    }

    public void addRouteEdge(RouteDuration edge) {
        if (ghu.contains(edge))
            return;

        this.ghu.add(edge);
        for (Destination source : map.keySet()) {
            if (source == edge.getOrigin()) {
                map.get(source).add(edge.getDestination());
            }
        }
    }

    public ArrayList<RouteDuration> getDestinationEdges(Destination source) {
        ArrayList<RouteDuration> destinations = new ArrayList<>();
        for (RouteDuration edge : this.ghu) {
            if (edge.getOrigin() == source) {
                destinations.add(edge);
            }
        }
        return destinations;
    }

    public RouteDuration getEdge(Destination source, Destination destination) {
        for (RouteDuration edge : this.ghu) {
            if (edge.getOrigin() == source && edge.getDestination() == destination) {
                return edge;
            }
        }
        return null;
    }

    public Destination getNodeByName(String name) {
        for (Destination vertex : map.keySet()) {
            if (vertex.getName().toLowerCase().equals(name.toLowerCase())) {
                return vertex;
            }
        }
        return null;
    }

    public Set<Destination> getNodes() {
        return this.map.keySet();
    }

    public int getNodeSize() {
        return vertexSize;
    }

    public void printGraph() {
        System.out.println("\n          GRAPH: ADJACENCY LIST                ");
        System.out.println("              PLACES ON CAMPUS                 \n");
        for (HashMap.Entry<Destination, ArrayList<Destination>> entry : map.entrySet()) {
            Destination vertex = entry.getKey();
            ArrayList<Destination> destinations = entry.getValue();
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            boolean emptyList = true;
            for (Destination destinatnion : destinations) {
                if (emptyList)
                    builder.append(destinatnion.getName());
                else
                    builder.append(", " + destinatnion.getName());
                emptyList = false;
            }
            builder.append("]");
            System.out.println(vertex.getName() + " to " + builder.toString());
            System.out.println("");
        }
    }

    public void listPlaces(Destination except) {
        int index = 1;
        for (Destination vertex : map.keySet()) {
            if (vertex != except) {
                System.out.println(index + ". " + vertex.getName());
            }
            index++;
        }
    }

}
