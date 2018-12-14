package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A representation of a 2 dimensional world in which vehicles and come and interact
 */
public class World {
    private int width;
    private int height;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<ITurbo> iTurboList = new ArrayList<>();
    private List<IEngine> iEngineList = new ArrayList<>();
    private List<IBoard> iBoardList = new ArrayList<>();

    private Random rand = new Random();

    public World(int worldX, int worldY) {
        this.width = worldX;
        this.height = worldY;

    }

    /**
     * Checks if a vehicle is outside the world
     *
     * @param vehicle
     * @param x
     * @param y
     * @return
     */
    private boolean isOutOfBounds(Vehicle vehicle, double x, double y) {
        if (x < 0) {
            return true;
        }
        return x + vehicle.getWidth() > width;

    }

    /**
     * Updates the positions of all the vehicles
     */
    public void update() {
        for (Vehicle vehicle : getVehicles()) {
            vehicle.move();
            if (isOutOfBounds(vehicle, vehicle.getX(), vehicle.getY())) {
                vehicle.stop();
                if (vehicle.getX() < 0) {
                    vehicle.setPosition(0, vehicle.getY());
                } else {
                    vehicle.setPosition(width - vehicle.getWidth(), vehicle.getY());
                }
                vehicle.invertDirection();
                vehicle.gas(1.0);
            }
        }
    }

    /**
     * Returns the list of vehicles contained within the world
     *
     * @return The list of vehicles in the world
     */
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    /**
     * Adds a vehicle to the world
     *
     * @param v Reference to the vehicle to add
     */
    public void addVehicle(Vehicle v) {
        if (v instanceof ITurbo)
            iTurboList.add((ITurbo) v);
        if (v instanceof IBoard)
            iBoardList.add((IBoard) v);
        if (v instanceof IEngine)
            iEngineList.add((IEngine) v);
        vehicles.add(v);
    }

    public void removeVehicle(Vehicle v) {
        if (v instanceof ITurbo)
            iTurboList.remove(v);
        if (v instanceof IBoard)
            iBoardList.remove(v);
        if (v instanceof IEngine)
            iEngineList.remove(v);
        vehicles.remove(v);
    }

    /**
     * Starts the engines of all the vehicles with engines
     */
    public void startEngines() {
        for (IEngine e : iEngineList)
            e.startEngine();
    }

    /**
     * Stops the engines of all the vehicles with engines
     */
    public void stopEngines() {
        for (IEngine e : iEngineList)
            e.stopEngine();
    }

    /**
     * Pushed the "throttle" of all the vehicles
     *
     * @param amount How hard to press the throttle
     */
    public void gasVehicles(double amount) {
        for (Vehicle v : vehicles)
            v.gas(amount);
    }

    /**
     * Pressed the "brake" for all the vehicles
     *
     * @param amount How hard to push the brake
     */
    public void brakeVehicles(double amount) {
        for (Vehicle v : vehicles)
            v.brake(amount);
    }

    /**
     * Turns on the turbos of all the vehicles that have a turbo installed
     */
    public void setTurbosOn() {
        for (ITurbo t : iTurboList)
            t.setTurboOn();
    }

    /**
     * Turns off the turbos of all the vehicles that have turbos
     */
    public void setTurbosOff() {
        for (ITurbo t : iTurboList)
            t.setTurboOff();
    }

    /**
     * Lowers the boards of all the vehicles that have a board
     */
    public void lowerBoards() {
        for (IBoard b : iBoardList)
            b.lower();
    }

    /**
     * Raises the boards of all the vehicles that have one
     */
    public void raiseBoards() {
        for (IBoard b : iBoardList)
            b.raise();
    }

    /**
     * Adds a random vehicle
     */
    public void addRandomVehicle() {
        if (vehicles.size() >= 10) {
            return;
        }
        int number = rand.nextInt(3);
        Vehicle vehicle;
        switch(number){
            case 0:
                vehicle = VehicleFactory.createSaab95();
                break;
            case 1:
                vehicle = VehicleFactory.createVolvo240();
                break;
            case 2:
                vehicle = VehicleFactory.createScania();
                break;
            default:
                vehicle = VehicleFactory.createSaab95();
                break;
        }
        vehicle.setPosition(rand.nextDouble() * (width - vehicle.getWidth()), rand.nextDouble() * (height - vehicle.getHeight()));
        addVehicle(vehicle);
    }

    /**
     * Removes a random vehicle
     */
    public void removeRandomVehicle() {
        if (vehicles.isEmpty()) {
            return;
        }
        int index = rand.nextInt(vehicles.size());
        removeVehicle(vehicles.get(index));
    }
}





