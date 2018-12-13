package model;

public class VehicleFactory {

    public static Vehicle createSaab95() {
        return new Saab95();
    }

    public static Vehicle createScania() {
        return new Scania();
    }

    public static Vehicle createVolvo240() {
        return new Volvo240();
    }

    public static Vehicle createCarTransporter() {
        return new CarTransporter();
    }

    public static Vehicle createCarFerry() {
        return new CarFerry(0, 0);
    }

}
