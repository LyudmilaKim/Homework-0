package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.cargo.Cargo;

public interface MissionFactory {
    CelestialBody createOrigin();
    CelestialBody createDestination();
    Cargo createCargo();
}