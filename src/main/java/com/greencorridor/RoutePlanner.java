package com.greencorridor;

import java.util.*;

/** Uses Dijkstra's algorithm to find the minimum-time route. */
public class RoutePlanner {
    private final RoadNetwork network;

    public RoutePlanner(RoadNetwork network) {
        this.network = Objects.requireNonNull(network, "network cannot be null");
    }

    public Route findOptimalRoute(String start, String destination) {
        if (start == null || destination == null
                || !network.getIntersections().contains(start)
                || !network.getIntersections().contains(destination)) {
            return Route.notFound();
        }

        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<NodeDistance> queue =
                new PriorityQueue<>(Comparator.comparingDouble(NodeDistance::distance));

        for (String node : network.getIntersections()) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        queue.add(new NodeDistance(start, 0.0));

        while (!queue.isEmpty()) {
            NodeDistance current = queue.poll();
            if (current.distance() > distances.get(current.node())) {
                continue;
            }
            if (current.node().equals(destination)) {
                break;
            }

            for (RoadNetwork.Road road : network.getRoadsFrom(current.node())) {
                double candidate = current.distance() + road.getTravelMinutes();
                if (candidate < distances.get(road.getDestination())) {
                    distances.put(road.getDestination(), candidate);
                    previous.put(road.getDestination(), current.node());
                    queue.add(new NodeDistance(road.getDestination(), candidate));
                }
            }
        }

        double totalMinutes = distances.get(destination);
        if (!Double.isFinite(totalMinutes)) {
            return Route.notFound();
        }

        LinkedList<String> path = new LinkedList<>();
        String step = destination;
        while (step != null) {
            path.addFirst(step);
            if (step.equals(start)) {
                break;
            }
            step = previous.get(step);
        }

        if (path.isEmpty() || !path.getFirst().equals(start)) {
            return Route.notFound();
        }
        return new Route(path, totalMinutes, true);
    }

    private record NodeDistance(String node, double distance) {}

    public static class Route {
        private final List<String> intersections;
        private final double totalMinutes;
        private final boolean found;

        private Route(List<String> intersections, double totalMinutes, boolean found) {
            this.intersections = List.copyOf(intersections);
            this.totalMinutes = totalMinutes;
            this.found = found;
        }

        public static Route notFound() {
            return new Route(List.of(), Double.POSITIVE_INFINITY, false);
        }
        public List<String> getIntersections() { return intersections; }
        public double getTotalMinutes() { return totalMinutes; }
        public boolean isFound() { return found; }
    }
}
