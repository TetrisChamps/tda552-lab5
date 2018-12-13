import model.Scania;
import model.VehicleFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {
    Scania scania;

    @BeforeEach
    void setUp() {
        scania = (Scania) VehicleFactory.createScania();
    }

    @AfterEach
    void tearDown() {
        scania = null;
    }

    @Test
    void raiseBoard() {
        assertEquals(scania.getBoardAngle(), 0);
        scania.raise();
        scania.raise();
        scania.raise();
        assertEquals(scania.getBoardAngle(), 30);
    }

    @Test
    void lowerBoard() {
        scania.raise();
        assertEquals(scania.getBoardAngle(), 10);
        scania.lower();
        assertEquals(scania.getBoardAngle(), 0);
        scania.lower();
        assertEquals(scania.getBoardAngle(), 0);
    }

    @Test
    void getBoardAngle() {
        scania.raise();
        scania.raise();
        scania.raise();
        scania.raise();
        assertEquals(scania.getBoardAngle(), 40);
    }

    @Test
    void gas() {
        scania.raise();
        scania.startEngine();
        scania.gas(1);
        assertEquals(scania.getSpeed(), 0);
        scania.lower();
        scania.gas(1);
        assertNotEquals(scania.getSpeed(), 0);
    }

    @Test
    void speedFactor() {
        assertEquals(scania.speedFactor(), 4);
    }
}