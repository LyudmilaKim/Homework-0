package edu.narxoz.galactic.factory;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.cargo.Cargo;

public class Mission implements MissionFactory {

    @Override
    public CelestialBody createOrigin() {
        return new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
    }

    @Override
    public CelestialBody createDestination() {
        return new Planet("Mars", 100, 0, "Carbon Dioxide");
    }

    @Override
    public Cargo createCargo() {
        return new Cargo(15, "Heavy equipment");
    }
}