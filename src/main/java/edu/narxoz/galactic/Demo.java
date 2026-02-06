package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.factory.DroneFactory;
import edu.narxoz.galactic.factory.DroneTypes;
import edu.narxoz.galactic.task.DeliveryTask;

public class Demo {

    public static void main(String[] args) {
        CelestialBody origin = new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
        CelestialBody destination = new SpaceStation("Orbital Station Alpha", 150, 50, 3);
        Cargo cargo = new Cargo(15, "Heavy equipment");

        Drone lightDrone = new LightDrone("LD-001", 10);
        Drone heavyDrone = new HeavyDrone("HD-001", 20);

        DeliveryTask task = new DeliveryTask(origin, destination, cargo);
        Dispatcher dispatcher = new Dispatcher();

        System.out.println("Assigning LightDrone:");
        Result r1 = dispatcher.assignTask(task, lightDrone);
        System.out.println(r1.ok() ? "Success" : "Fail: " + r1.reason());

        System.out.println("\nAssigning HeavyDrone:");
        Result r2 = dispatcher.assignTask(task, heavyDrone);
        System.out.println(r2.ok() ? "Success" : "Fail: " + r2.reason());

        if (r2.ok()) {
            System.out.println("\nEstimated time: " + task.estimateTime() + " minutes");

            System.out.println("\nCompleting task:");
            Result r3 = dispatcher.completeTask(task);
            System.out.println(r3.ok() ? "Success" : "Fail: " + r3.reason());

            System.out.println("\nFinal state:");
            System.out.println("Drone status: " + heavyDrone.getStatus());
            System.out.println("Task state: " + task.getState());
        }

        System.out.println("\nFactory Method demonstration");
        DroneFactory factory = new DroneTypes();
        Drone demoDrone = factory.createDrone("DEMO-001", 12);
        System.out.println("Created drone type via factory: " + demoDrone.getClass().getSimpleName());
    }
}