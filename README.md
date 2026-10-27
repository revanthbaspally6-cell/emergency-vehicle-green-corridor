# Emergency Vehicle Green Corridor System — Qodo Demo

A small Java 17 demonstration prototype for a Software Engineering project. It models intersections and roads as a weighted graph, uses Dijkstra's algorithm to find a minimum-time route, and simulates green-signal priority along that route.

## Files
- `EmergencyVehicle.java`: emergency vehicle details
- `RoadNetwork.java`: intersections and weighted roads
- `RoutePlanner.java`: Dijkstra route calculation
- `TrafficSignalController.java`: simulated green-corridor activation
- `GreenCorridorApp.java`: runnable demonstration
- `RoutePlannerTest.java`: route and unreachable-destination tests

## Run
Requires Java 17+ and Maven:
```bash
mvn test
mvn -q package
java -cp target/classes com.greencorridor.GreenCorridorApp
```

## Qodo Merge assignment workflow
1. Create a new GitHub repository for this demo (do not overwrite the original project).
2. Push this project to GitHub.
3. Create a new branch and make a small change to `RoadNetwork.java`.
4. Open a pull request against `main`.
5. Use Qodo's review workflow on the pull request.
6. Read its actual findings, test them, apply valid fixes, and capture screenshots.

## Deliberate review scenario
`RoadNetwork.addRoad()` currently accepts negative and non-finite travel times. Ask Qodo to review the pull request for correctness and edge cases. A possible improvement is to reject weights where `travelMinutes < 0` or `!Double.isFinite(travelMinutes)`. Dijkstra's shortest-path guarantees require non-negative edge weights.

This is a simulation only. It does not connect to live emergency services, maps, or real traffic signal hardware. Never use it to control real traffic.
