package gui;

import model.Car;
import model.Saab95;
import model.Scania;
import model.Volvo240;

import java.util.ArrayList;

public class World {
    private int width;
    private int height;
    private ArrayList<Car> cars;

    public  World(int worldX,int worldY,ArrayList<Car> initCars){
        this.width = worldX;
        this.height = worldY-240;
        this.cars = initCars;
    }

    boolean isOutOfBounds(Car car ,double x, double y) {
        if (x < 0) {
            return true;
        }
        if (width < x + car.getWidth()) {
            return true;
        }
        if (y < 0) {
            return true;
        }
        if (height < y + car.getHeight()) {
          return true;
        }
        return false;
    }

    void update(){
        for (Car car: cars) {
            car.move();
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }
}





