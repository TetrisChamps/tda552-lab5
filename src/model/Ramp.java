package model;

/**
 * A representation of a ramp
 */
public class Ramp extends Board {
    private boolean up = true; // Whether the board is up or down

    @Override
    public void raise() {
        up = true;
    }

    @Override
    public void lower() {
        up = false;
    }

    @Override
    public boolean isDown() {
        return !up;
    }

    @Override
    public int getAngle() {
        return up ? 90 : 0;
    }
}
