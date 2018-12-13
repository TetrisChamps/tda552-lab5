package model;

/**
 * A static factory class that creates cars of different models
 */
public class VehicleFactory {

    /**
     * Creates a Saab 9-5 car
     *
     * @return
     */
    public static Vehicle createSaab95() {
        return new Saab95();
    }

    /**
     * Creates a Scania truck
     *
     * @return
     */
    public static Vehicle createScania() {
        return new Scania();
    }

    /**
     * Creates a Volvo 240 car
     *
     * @return
     */
    public static Vehicle createVolvo240() {
        return new Volvo240();
    }

    /**
     * Creates a Car transporter truck
     *
     * @return
     */
    public static Vehicle createCarTransporter() {
        return new CarTransporter();
    }

    /**
     * Create a ferry that carries cars
     *
     * @return
     */
    public static Vehicle createCarFerry() {
        return new CarFerry(0, 0);
    }

}
