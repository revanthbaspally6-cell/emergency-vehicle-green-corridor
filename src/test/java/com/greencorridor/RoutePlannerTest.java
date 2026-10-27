package com.greencorridor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class RoutePlannerTest {
    @Test
    void selectsRouteWithLowestTravelTime() {
        RoadNetwork network = new RoadNetwork();
        network.addRoad("A", "B", 4);
        network.addRoad("B", "D", 5);
        network.addRoad("A", "C", 2);
        network.addRoad("C", "D", 3);

        RoutePlanner.Route route = new RoutePlanner(network).findOptimalRoute("A", "D");

        assertTrue(route.isFound());
        assertEquals(5.0, route.getTotalMinutes());
        assertEquals(List.of("A", "C", "D"), route.getIntersections());
    }

    @Test
    void reportsWhenDestinationIsUnreachable() {
        RoadNetwork network = new RoadNetwork();
        network.addIntersection("Hospital");
        network.addIntersection("Emergency Site");

        RoutePlanner.Route route =
                new RoutePlanner(network).findOptimalRoute("Hospital", "Emergency Site");

        assertFalse(route.isFound());
    }
}
