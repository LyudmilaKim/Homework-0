package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "Task or drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "Drone is not IDLE");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "Cargo weight exceeds drone max payload");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "Task is not CREATED");
        }

        task.setState(TaskState.ASSIGNED);
        task.setAssignedDrone(drone);
        drone.setStatus(DroneStatus.IN_FLIGHT);
        return new Result(true, "");
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "Task is null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "Task is not ASSIGNED");
        }
        Drone assignedDrone = task.getAssignedDrone();
        if (assignedDrone == null || assignedDrone.getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "Assigned drone is null or not in flight");
        }

        task.setState(TaskState.DONE);
        assignedDrone.setStatus(DroneStatus.IDLE);
        return new Result(true, "");
    }
}