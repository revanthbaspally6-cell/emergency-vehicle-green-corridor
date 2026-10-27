package com.greencorridor;

public class EmergencyVehicle {
    private final String vehicleId;
    private final String type;
    private final String currentLocation;
    private final String destination;

    public EmergencyVehicle(String vehicleId, String type,
                            String currentLocation, String destination) {
        if (vehicleId == null || vehicleId.isBlank()) {
            throw new IllegalArgumentException("Vehicle ID is required");
        }
        this.vehicleId = vehicleId;
        this.type = type;
        this.currentLocation = currentLocation;
        this.destination = destination;
    }

    public String getVehicleId() { return vehicleId; }
    public String getType() { return type; }
    public String getCurrentLocation() { return currentLocation; }
    public String getDestination() { return destination; }
}
