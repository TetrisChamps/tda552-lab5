package model;

import java.util.ArrayList;
import java.util.List;

public class World {
    private int width;
    private int height;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<ITurbo> iTurboList = new ArrayList<>();
    private List<IEngine> iEngineList = new ArrayList<>();
    private List<IBoard> iBoardList = new ArrayList<>();

    public World(int worldX, int worldY) {
        this.width = worldX;
        this.height = worldY - 240;

    }

    boolean isOutOfBounds(Vehicle vehicle, double x, double y) {
        if (x < 0) {
            return true;
        }
        if (x + vehicle.getWidth() > width) {
            return true;
        }
        return false;

    }

    public void update() {
        for (Vehicle vehicle : getVehicles()) {
            vehicle.move();
            if (isOutOfBounds(vehicle, vehicle.getX(), vehicle.getY())) {
                vehicle.stop();
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

    public List<Vehicle> getVehicles() {
        return new ArrayList<Vehicle>(vehicles);
    }


    public void setCars(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle v){
        if(v instanceof ITurbo)
            iTurboList.add((ITurbo) v);
        if(v instanceof IBoard)
            iBoardList.add((IBoard) v);
        if(v instanceof IEngine)
            iEngineList.add((IEngine) v);
        vehicles.add(v);
    }

    public void startEngines() {
        for (IEngine e : iEngineList)
            e.startEngine();
    }

    public void stopEngines() {
        for (IEngine e : iEngineList)
            e.stopEngine();
    }

    public void gasVehicles(double amount) {
        for (Vehicle v : vehicles)
            v.gas(amount);
    }

    public void brakeVehicles(double amount) {
        for (Vehicle v : vehicles)
            v.brake(amount);
    }

    public void setTurbosOn() {
        for (ITurbo t : iTurboList)
            t.setTurboOn();
    }

    public void setTurbosOff() {
        for (ITurbo t : iTurboList)
            t.setTurboOff();
    }

    public void lowerBoards() {
        for (IBoard b : iBoardList)
            b.lower();
    }

    public void raiseBoards() {
        for (IBoard b : iBoardList)
            b.raise();
    }
}





