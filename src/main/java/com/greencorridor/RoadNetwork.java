package com.greencorridor;

import java.util.*;

/** Stores intersections and the roads connecting them. Road costs are travel minutes. */
public class RoadNetwork {
    private final Map<String, List<Road>> adjacency = new HashMap<>();

    public void addIntersection(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Intersection name is required");
        }
        adjacency.putIfAbsent(name, new ArrayList<>());
    }

    public void addRoad(String from, String to, double travelMinutes) {
        addIntersection(from);
        addIntersection(to);
        if (!Double.isFinite(travelMinutes) || travelMinutes < 0) {
    throw new IllegalArgumentException(
        "Travel time must be finite and non-negative"
    );
}

        // Intentional Qodo review target: road cost is not validated yet.
        // A negative, NaN, or infinite cost can make route calculations unreliable.
        adjacency.get(from).add(new Road(to, travelMinutes));
        adjacency.get(to).add(new Road(from, travelMinutes));
    }

    public Set<String> getIntersections() {
        return Collections.unmodifiableSet(adjacency.keySet());
    }

    public List<Road> getRoadsFrom(String intersection) {
        List<Road> roads = adjacency.get(intersection);
        return roads == null ? Collections.emptyList()
                             : Collections.unmodifiableList(roads);
    }

    public static class Road {
        private final String destination;
        private final double travelMinutes;

        public Road(String destination, double travelMinutes) {
            this.destination = destination;
            this.travelMinutes = travelMinutes;
        }

        public String getDestination() { return destination; }
        public double getTravelMinutes() { return travelMinutes; }
    }
}
