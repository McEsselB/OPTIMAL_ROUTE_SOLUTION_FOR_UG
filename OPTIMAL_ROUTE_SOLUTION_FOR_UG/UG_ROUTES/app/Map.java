
package app;

public class Map extends Digraph {
    RouteDuration dg;

    @Override
    public void addRouteEdge(RouteDuration edge) {

        this.dg = edge;
        if (ghu.contains(edge))
            return;

        this.ghu.add(edge);
        this.ghu.add(new RouteDuration(edge.getDestination(), edge.getOrigin(), edge.getDistance()));
        for (Destination vertex : map.keySet()) {
            if (vertex == edge.getOrigin()) {
                map.get(vertex).add(edge.getDestination());
            }
        }

        for (Destination vertex : map.keySet()) {
            if (vertex == edge.getDestination()) {
                map.get(vertex).add(edge.getOrigin());
            }
        }
    }

    public int getDistance(Destination locations, Destination locations1) {
        return dg.getDistance();
    }
}
