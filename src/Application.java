import gui.CarController;
import gui.CarView;
import model.*;

import java.util.ArrayList;

public class Application {




    public static void main(String[] args) {
        //Size of the world and the view window
        int worldSizeX = 800;
        int worldSizeY = 800;
        //Sets the size of the window
        //Uses the same size
        World world = new World(worldSizeX, worldSizeY);

        CarController cc = new CarController(world,new CarView("CarSim 1.0",worldSizeX,worldSizeY));

        // Start the timer
        cc.startTimer();
    }



}
