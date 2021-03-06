package gui;

import model.VehicleFactory;
import model.World;
import view.CarView;
import view.VehicleEvents;
import view.VehicleObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController implements VehicleObserver {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    private World world;
    private CarView frame;

    public CarController(World world, CarView frame) {
        this.world = world;
        this.frame = frame;
        frame.addObserver(this);
        world.addVehicle(VehicleFactory.createVolvo240().setPosition(0, 0));
        world.addVehicle(VehicleFactory.createSaab95().setPosition(0, 200));
        world.addVehicle(VehicleFactory.createScania().setPosition(0, 400));
    }

    public void startTimer() {
        timer.start();
    }

    public void onVehicleAction(VehicleEvents actionEnum) {
        switch (actionEnum) {
            case GAS:
                world.gasVehicles(frame.gasAmount);
                break;
            case BREAK:
                world.brakeVehicles(frame.gasAmount);
                break;
            case START_ALL:
                world.startEngines();
                break;
            case STOP_ALL:
                world.stopEngines();
                break;
            case TURBO_ON:
                world.setTurbosOn();
                break;
            case TURBO_OFF:
                world.setTurbosOff();
                break;
            case RAISE_BOARD:
                world.raiseBoards();
                break;
            case LOWER_BED:
                world.lowerBoards();
                break;
            case ADD:
                world.addRandomVehicle();
                break;
            case REMOVE:
                world.removeRandomVehicle();
                break;
        }
    }

    /**
     * Each step, the TimerListener updates the world, feeds the updated information to the view and tells the
     * frame to repaint
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            world.update();
            frame.drawPanel.setVehicles(world.getVehicles());
            frame.drawPanel.repaint();
        }
    }
}