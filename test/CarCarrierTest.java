import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarCarrierTest {

    private CarCarrier carrier;

    @BeforeEach
    void setUp() {
        carrier = new CarCarrier(10, 2000);
    }

    @AfterEach
    void tearDown() {
        carrier = null;
    }

    @Test
    void move() {
        //TODO: Fix test
        /* CarTransporter transporter = new CarTransporter();
        carrier.addCar(new Volvo240(), transporter);
        carrier.addCar(new Volvo240(), transporter);
        carrier.move(10, 5);
        assertEquals(10, carrier.getVehicles().get(0).getX());
        assertEquals(5, carrier.getVehicles().get(0).getY());
        assertEquals(10, carrier.getVehicles().get(1).getX());
        assertEquals(5, carrier.getVehicles().get(1).getY());
        */
    }

    @Test
    void addCar() {
        CarTransporter transporter = new CarTransporter();
        assertThrows(IllegalArgumentException.class, () -> carrier.addCar(transporter, transporter), "Cannot add itself to itself");
        Car c = (Saab95) VehicleFactory.createSaab95();
        carrier.addCar(c, transporter);
        Scania scania = (Scania) VehicleFactory.createScania();
        assertThrows(IllegalStateException.class, () -> carrier.addCar(scania, transporter), "model.Car is too heave");
        assertThrows(IllegalStateException.class, () -> carrier.addCar(c, transporter), "model.Car is already loaded");
        carrier = new CarCarrier(5, 2000);
        for (int i = 0; i < 5; i++) {
            carrier.addCar((Saab95) VehicleFactory.createSaab95(), transporter);
        }
        assertThrows(IllegalStateException.class, () -> carrier.addCar((Saab95) VehicleFactory.createSaab95(), transporter));
    }

    @Test
    void removeCar() {
        Car transporter = (Volvo240) VehicleFactory.createVolvo240();
        assertEquals(null, carrier.removeCar(true, transporter));
        Car c = (Saab95) VehicleFactory.createSaab95();
        carrier.addCar(c, transporter);
        assertEquals(c, carrier.removeCar(false, transporter));
    }

}