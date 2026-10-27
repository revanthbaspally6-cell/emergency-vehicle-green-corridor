package com.greencorridor;

public class GreenCorridorApp {
    public static void main(String[] args) {
        RoadNetwork network = new RoadNetwork();
        network.addRoad("Hospital", "Junction A", 3);
        network.addRoad("Hospital", "Main Road", 6);
        network.addRoad("Junction A", "City Center", 4);
        network.addRoad("Main Road", "City Center", 2);
        network.addRoad("Junction A", "Main Road", 1);
        network.addRoad("City Center", "Emergency Site", 5);

        EmergencyVehicle ambulance = new EmergencyVehicle(
                "AMB-101", "Ambulance", "Hospital", "Emergency Site");

        RoutePlanner planner = new RoutePlanner(network);
        RoutePlanner.Route route =
                planner.findOptimalRoute(ambulance.getCurrentLocation(),
                                         ambulance.getDestination());

        System.out.println("=== Emergency Vehicle Green Corridor System ===");
        System.out.println("Vehicle: " + ambulance.getVehicleId()
                + " (" + ambulance.getType() + ")");

        if (!route.isFound()) {
            System.out.println("No route found. Green corridor not activated.");
            return;
        }

        System.out.println("Optimal route: "
                + String.join(" -> ", route.getIntersections()));
        System.out.printf("Estimated travel time: %.1f minutes%n",
                route.getTotalMinutes());

        new TrafficSignalController().activateGreenCorridor(route.getIntersections());
        System.out.println("Demo complete. Signals above are simulated only.");
    }
}
