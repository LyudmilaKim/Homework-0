package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;

public abstract class DroneFactory {

    public abstract Drone createDrone(String id, double maxPayloadKg);

    public Drone createLightDrone(String id, double maxPayloadKg) {
        return new LightDrone(id, maxPayloadKg);
    }

    public Drone createHeavyDrone(String id, double maxPayloadKg) {
        return new HeavyDrone(id, maxPayloadKg);
    }
}