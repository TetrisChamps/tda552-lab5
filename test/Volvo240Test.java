import model.VehicleFactory;
import model.Volvo240;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    private Volvo240 volvo240;

    @BeforeEach
    void setUp() {
        Volvo240 volvo240 = (Volvo240) VehicleFactory.createVolvo240();
    }

    @AfterEach
    void tearDown() {
        volvo240 = null;
    }

    @Test
    void speedFactor() {
        assertEquals(1.25, volvo240.speedFactor());
    }
}