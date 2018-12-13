package model;

import java.awt.*;

/**
 * A representation of a high level vehicle.
 */
public abstract class Vehicle implements IMovable {
    private final String modelName; // The model name of the vehicle
    final private int weight;
    final private int height;
    final private int width;
    private Color color; // Color of the vehicle
    private boolean loaded = false; // Whether the vehicle is loaded or not
    private double x; // The position on the x axis
    private double y; // The position on the y axis
    private double rotation = 0.0; // The rotation of the object, in degrees
    private double speed = 0.0; // The speed of the object


    /**
     * Standard constructor for making an abstract vehicle, must be called from subclass
     *
     * @param color     The color of the vehicle
     * @param modelName The model name of the vehicle
     * @param x         Coordinate x of the vehicle
     * @param y         Coordinate y of the vehicle
     * @param weight    the weight of the vehicle
     * @param height    the height of the vehicle
     * @param width     the width  of the vehicle
     */
    public Vehicle(Color color, String modelName, double x, double y, int weight, int height, int width) {

        this.color = color;
        this.modelName = modelName;
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.height = height;
        this.width = width;
    }

    /**
     * Adds an angle to another and returns the new angle based on the unit circle
     *
     * @param angle      Base angle
     * @param deltaAngle Angle to be added to the base
     * @return The resulting angle
     */

    public static double applyDeltaToAngle(double angle, double deltaAngle) {
        angle = (angle + deltaAngle) % 360;
        return (angle > 0) ? angle : 360 + angle;

    }

    /**
     * Return the height of the vehicle
     *
     * @return The height of the vehicle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the width of the vehicle
     *
     * @return The width of the vehicle
     */
    public int getWidth() {
        return width;
    }


    /**
     * Return the model name of the vehicle
     *
     * @return The model name
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Returns the colour of the vehicle
     *
     * @return The color of the vehicle
     */
    public Color getColor() {
        return color;
    }

    /**
     * Moves the car a distance of its current speed in the direction it is facing
     */
    @Override
    public void move() {
        x += Math.cos(Math.toRadians(rotation)) * speed;
        y += Math.sin(Math.toRadians(rotation)) * speed;
    }

    /**
     * Returns the maximum acceleration of the Vehicle.
     *
     * @return The increase in speed.
     **/
    public abstract double speedFactor();

    private void increaseSpeed(double amount) {
        double newSpeed = speed + speedFactor() * amount;
        speed = maxSpeed() >= newSpeed ? newSpeed : maxSpeed();
    }

    /**
     * Decreses the speed by amount
     *
     * @param amount
     */
    private void decreaseSpeed(double amount) {
        double newSpeed = speed - speedFactor() * amount;
        speed = newSpeed > 0 ? newSpeed : 0;
    }

    /**
     * Increases the current speed, must be called from overridden subclasses
     *
     * @param amount Gas amount (0.0 - 1.0)
     */
    public void gas(double amount) {
        increaseSpeed(Maths.clamp(amount, 0.0, 1.0));
    }


    /**
     * Decreases the speed of the vehicle, must be called from overriden methods.
     *
     * @param amount Brake amount (0.0 - 1.0)
     */
    public void brake(double amount) {
        decreaseSpeed(Maths.clamp(amount, 0.0, 1.0));
    }

    /**
     * This abstract method returns the maximum speed of an object in any direction. This must be overriden as
     * how a vehicle defines its maximum speed can vary greatly.
     *
     * @return maxSpeed
     */
    public abstract double maxSpeed();

    /**
     * Returns the current speed of the vehicle
     *
     * @return Current speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Stops the vehicle abruptly
     */
    public void stop() {
        speed = 0;
    }

    public void invertDirection() {
        rotateVehicle(180);
    }

    /**
     * Get rotation of the vehicle
     *
     * @return Vehicle rotation
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Changes the angle of the vehicle by deltaAngle
     *
     * @param deltaAngle the change in angle
     */
    public final void rotateVehicle(double deltaAngle) {
        this.rotation = applyDeltaToAngle(this.rotation, deltaAngle);
    }

    /**
     * Gets the x position of the vehicle
     *
     * @return x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y position of the vehicle
     *
     * @return y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x and y coordinates of the vehicle
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return this
     */
    public Vehicle setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Loads the vehicle
     */
    public void load() {
        loaded = true;
    }

    /**
     * Unloads the vehicle
     */
    public void unload() {
        loaded = false;
    }

    /**
     * Returns whether the vehicle is loaded or not
     *
     * @return If the vehicle is loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Returns the weight of the vehicle
     *
     * @return The vehicle's weight
     */
    public int getWeight() {
        return weight;
    }
}
