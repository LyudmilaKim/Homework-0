package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.drones.HeavyDrone;

public class DroneTypes extends DroneFactory {

    @Override
    public Drone createDrone(String id, double maxPayloadKg) {
        if (maxPayloadKg <= 10) {
            return new LightDrone(id, maxPayloadKg);
        } else {
            return new HeavyDrone(id, maxPayloadKg);
        }
    }
}