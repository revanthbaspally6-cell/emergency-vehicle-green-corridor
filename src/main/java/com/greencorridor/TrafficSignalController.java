package com.greencorridor;

import java.util.List;

/** Simulates signal priority; it does not control real traffic lights. */
public class TrafficSignalController {
    public void activateGreenCorridor(List<String> route) {
        if (route == null || route.isEmpty()) {
            System.out.println("Green corridor not activated: route is unavailable.");
            return;
        }

        System.out.println("Simulating green corridor activation:");
        for (String intersection : route) {
            System.out.println("  Signal at " + intersection + " -> GREEN for emergency vehicle");
        }
    }
}
