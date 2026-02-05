package edu.narxoz.galactic;

import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.dispatcher.Result;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.task.DeliveryTask;

public class Demo {
    public static void main(String[] args) {
        Planet earth = new Planet("Earth", 0, 0, "Nitrogen-Oxygen");
        Planet mars = new Planet("Mars", 100, 0, "Carbon Dioxide");

        Cargo cargo = new Cargo(15, "Heavy equipment");

        LightDrone light = new LightDrone("LD001", 10);
        HeavyDrone heavy = new HeavyDrone("HD001", 20);

        DeliveryTask task = new DeliveryTask(earth, mars, cargo);

        Dispatcher dispatcher = new Dispatcher();

        System.out.println("Assign to LightDrone:");
        Result r1 = dispatcher.assignTask(task, light);
        System.out.println(r1.ok() ? "Success" : "Fail: " + r1.reason());

        System.out.println("\nAssign to HeavyDrone:");
        Result r2 = dispatcher.assignTask(task, heavy);
        System.out.println(r2.ok() ? "Success" : "Fail: " + r2.reason());

        if (r2.ok()) {
            System.out.println("\nEstimated time: " + task.estimateTime() + " min");

            System.out.println("\nComplete task:");
            Result r3 = dispatcher.completeTask(task);
            System.out.println(r3.ok() ? "Success" : "Fail: " + r3.reason());

            System.out.println("Drone status: " + heavy.getStatus());
            System.out.println("Task state: " + task.getState());
        }
    }
}