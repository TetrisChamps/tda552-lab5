package model;

import java.util.ArrayList;
import java.util.List;

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

    public World(int worldX, int worldY) {
        this.width = worldX;
        this.height = worldY - 240;

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
     * @return
     */
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    /**
     * Adds a vehicle to the world
     *
     * @param v
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
     * @param amount
     */
    public void gasVehicles(double amount) {
        for (Vehicle v : vehicles)
            v.gas(amount);
    }

    /**
     * Pressed the "brake" for all the vehicles
     *
     * @param amount
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
}





