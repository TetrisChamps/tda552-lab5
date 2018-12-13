import model.CarTransporter;
import model.VehicleFactory;
import model.Volvo240;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTransporterTest {

    CarTransporter carTransporter;

    @BeforeEach
    void setUp() {
        carTransporter = new CarTransporter();

    }

    @AfterEach
    void TearDown() {
        carTransporter = null;
    }

    @Test
    void addCar() {

        Volvo240 volvo240 = (Volvo240) VehicleFactory.createVolvo240();
        carTransporter.lower();
        int amountOfLoadedCars = carTransporter.getNumberOfCars();
        carTransporter.addCar(volvo240);

        assertEquals(amountOfLoadedCars + 1, carTransporter.getNumberOfCars(), "model.Car was not loaded");
        assertEquals(carTransporter.removeCar(), volvo240, "The unloaded car is not the same as the loaded");

        carTransporter.raise();
        assertThrows(IllegalStateException.class, () -> carTransporter.addCar(volvo240));
    }

    @Test
    void removeCar() {
        Volvo240 volvo240 = (Volvo240) VehicleFactory.createVolvo240();
        carTransporter.lower();
        carTransporter.addCar(volvo240);
        int amountOfLoadedCars = carTransporter.getNumberOfCars();
        assertEquals(carTransporter.removeCar(), volvo240, "The unloaded car is not the same as the loaded");
        assertEquals(amountOfLoadedCars - 1, carTransporter.getNumberOfCars(), "A car was not unloaded");
        carTransporter.raise();
        assertNull(carTransporter.removeCar());
    }

    @Test
    public void isRampDown() {
        carTransporter.raise();
        assertEquals(carTransporter.isRampDown(), false, "Raised ramp is down");
        carTransporter.lower();
        assertEquals(carTransporter.isRampDown(), true, "Lowered ramp is up");
    }

    @Test
    void raiseRamp() {
        carTransporter.raise();
        assertEquals(carTransporter.isRampDown(), false, "Raised ramp was down;");
    }

    @Test
    void lowerRamp() {
        carTransporter.lower();
        assertEquals(carTransporter.isRampDown(), true, "Lowered ramp was down;");
    }


    @Test
    void speedFactor() {
        assertEquals(carTransporter.speedFactor(), carTransporter.maxSpeed() * 0.01, "speedFactor is not accurate");
    }

}