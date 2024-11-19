package co.edu.uptc.logic;

import co.edu.uptc.model.Connection;
import co.edu.uptc.model.Substation;

import java.util.*;

public class GraphLogic {
    private Map<Substation, List<Connection>> graph;

    public GraphLogic() {
        this.graph = new HashMap<>();
    }

    public void addSubstation(Substation substation) {
        graph.putIfAbsent(substation, new ArrayList<>());
    }

    public void addConnection(Substation source, Substation target, double resistance) {
        graph.get(source).add(new Connection(source, target, resistance));
        graph.get(target).add(new Connection(target, source, resistance)); // No dirigido
    }

    public Map<Substation, Double> dijkstra(Substation start) {
        Map<Substation, Double> distances = new HashMap<>();
        PriorityQueue<Substation> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        Map<Substation, Substation> previous = new HashMap<>();

        for (Substation substation : graph.keySet()) {
            distances.put(substation, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            Substation current = queue.poll();
            for (Connection connection : graph.get(current)) {
                Substation neighbor = connection.getTarget();
                double newDist = distances.get(current) + connection.getResistance();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public Map<Substation, List<Connection>> getGraph() {
        return graph;
    }
}
