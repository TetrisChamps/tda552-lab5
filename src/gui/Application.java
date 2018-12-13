package gui;

import model.Car;
import model.Saab95;
import model.Scania;
import model.Volvo240;

import java.util.ArrayList;

public class Application {




    public static void main(String[] args) {
        //Size of the world and the view window
        int worldSizeX = 800;
        int worldSizeY = 800;
        //Sets the size of the window
        //Uses the same size
        World gameWorld = new World(worldSizeX,worldSizeY,new ArrayList<Car>());

        Volvo240 v = new Volvo240();
        v.setPosition(0, 0);
        gameWorld.getCars().add(v);
        Saab95 s = new Saab95();
        s.setPosition(0, 100);
        gameWorld.getCars().add(s);
        Scania sc = new Scania();
        sc.setPosition(0, 200);
        gameWorld.getCars().add(sc);
        CarController cc = new CarController(gameWorld,new CarView("CarSim 1.0",worldSizeX,worldSizeY));

        // Start the timer
        cc.timer.start();
    }



}
