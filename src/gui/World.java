package gui;

import model.Car;
import model.Vehicle;

import java.util.List;

public class World {
    private int width;
    private int height;
    private List<Car> cars;

    public World(int worldX, int worldY, List<Car> cars) {
        this.width = worldX;
        this.height = worldY - 240;
        this.cars = cars;
    }

    boolean isOutOfBounds(Vehicle vehicle, double x, double y) {
        if (x < 0) {
            return true;
        }
        if (x + vehicle.getWidth() > width) {
            return true;
        }
        /* if (y < 0) {
            return true;
        }
        if (height < y + car.getHeight()) {
          return true;
        }*/
        return false;

    }

    void update() {
        for (Vehicle vehicle : getCars()) {
            vehicle.move();
            if (isOutOfBounds(vehicle, vehicle.getX(), vehicle.getY())) {
                vehicle.stop();
                //BufferedImage i = frame.drawPanel.getImageFromClass(vehicle.getModelName());
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}





