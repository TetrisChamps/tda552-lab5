package model;

import java.awt.*;

/**
 * A wheeled motor-driven vehicle
 */
public abstract class Car extends Vehicle implements IEngine {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private boolean engineOn = false; // Whether the engine is on or not

    /**
     * Initiates a car, based on certain physical attributes.
     *
     * @param nrDoors     The number of doors
     * @param enginePower Power of the engine, also determines the max speed.
     * @param color       The color that the body of the car is painted.
     * @param modelName   The model name of the car.
     * @param x           The x coordinate of the car.
     * @param y           The y coordinate of the car.
     * @param weight      The weight of the car.
     */
    public Car(int nrDoors, double enginePower, Color color, String modelName, int x, int y, int weight, int height, int width) {
        super(color, modelName, x, y, weight, height, width);
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        stopEngine();
    }

    /**
     * Returns how many doors the car has
     *
     * @return int
     */
    public int getNrDoors() {
        return nrDoors;
    }

    /**
     * Returns the engine power in terms of horse power.
     *
     * @return double
     */
    public double maxSpeed() {
        return enginePower * 1000 / getWeight();
    }

    /**
     * Returns the state of the engine, either on or off.
     *
     * @return boolean
     */
    public boolean getEngineOn() {
        return engineOn;
    }

    /**
     * Turns on the engine
     */
    public void startEngine() {
        if (!engineOn) {
            engineOn = true;
            gas(0.5);
        }
    }

    /**
     * Turns off the engine
     */
    public void stopEngine() {
        engineOn = false;
        stop();
    }

    /**
     * Applies the throttle between 0-1
     *
     */
    public void gas(double amount) {
        if (engineOn && !isLoaded()) {
            super.gas(amount);
        }
    }

    @Override
    public double speedFactor() {
        return maxSpeed() * 0.01;
    }

    /**
     * Presses down the brake by a factor of 0-1
     *
     */
    public void brake(double amount) {
        super.brake(amount);
    }


    @Override
    public void turnLeft() {
        rotateVehicle(10);
    }

    @Override
    public void turnRight() {
        rotateVehicle(-10);
    }
}
