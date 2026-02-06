package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.drones.Drone;

public abstract class DroneFactory {
    public abstract Drone createDrone(String id, double maxPayloadKg);
}