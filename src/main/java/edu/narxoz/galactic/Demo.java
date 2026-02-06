package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.CelestialBody;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.bodies.SpaceStation;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.factory.DroneFactory;
import edu.narxoz.galactic.factory.DroneTypes;
import edu.narxoz.galactic.factory.Mission;
import edu.narxoz.galactic.factory.MissionFactory;
import edu.narxoz.galactic.task.DeliveryTask;

public class Demo {

    public static void main(String[] args) {
        MissionFactory missionFactory = new Mission();
        CelestialBody origin = missionFactory.createOrigin();
        CelestialBody destination = new SpaceStation("Orbital Station Alpha", 150, 50, 3);

        Cargo cargo = missionFactory.createCargo();

        DroneFactory droneFactory = new DroneTypes();
        Drone lightDrone = droneFactory.createDrone("LD-001", 10);
        Drone heavyDrone = droneFactory.createDrone("HD-001", 20);

        DeliveryTask task = new DeliveryTask(origin, destination, cargo);
        Dispatcher dispatcher = new Dispatcher();

        System.out.println("Assigning LightDrone (max payload 10 kg):");
        Result result1 = dispatcher.assignTask(task, lightDrone);
        System.out.println(result1.ok() ? "Success" : "Fail: " + result1.reason());

        System.out.println("Assigning HeavyDrone (max payload 20 kg):");
        Result result2 = dispatcher.assignTask(task, heavyDrone);
        System.out.println(result2.ok() ? "Success" : "Fail: " + result2.reason());

        if (result2.ok()) {
            System.out.println("Estimated delivery time: " + task.estimateTime() + " minutes");

            System.out.println("Completing task:");
            Result result3 = dispatcher.completeTask(task);
            System.out.println(result3.ok() ? "Success" : "Fail: " + result3.reason());

            System.out.println("Final state:");
            System.out.println("Drone status: " + heavyDrone.getStatus());
            System.out.println("Task state: " + task.getState());
        }
    }
}