package gui;

import model.Car;
import model.Saab95;
import model.Scania;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController implements Action {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    protected Timer timer = new Timer(delay, new TimerListener());
    World gameWorld;
    CarView frame;
    // Calls the gas method for each car once
    public CarController(World gameworld, CarView frame){
        this.gameWorld = gameworld;
        this.frame = frame;
        frame.addObserver(this);

    }

    public void action(Actions actionEnum) {
        switch(actionEnum) {
            case GAS:
                for (Car car :gameWorld.getCars()) {
                    car.gas(frame.gasAmount);
                }
                break;
            case BREAK:
                for (Car car :gameWorld.getCars()) {
                    car.brake(frame.gasAmount);
                }
                break;
            case STARTALL:
                for (Car car :gameWorld.getCars()) {
                    car.startEngine();
                }
                break;
            case STOPALL:
                for (Car car :gameWorld.getCars()) {
                    car.stopEngine();
                }
                break;
            case TURBOON:
                for (Car car :gameWorld.getCars()) {
                    try {
                        ((Saab95) car).setTurboOn();
                    } catch (Exception e) {
                    }
                }
                break;
            case TURBOOFF:
                for (Car car :gameWorld.getCars()) {
                    try {
                        ((Saab95) car).setTurboOff();
                    } catch (Exception e) {
                    }
                }
                break;
            case LIFTBED:
                for (Car car :gameWorld.getCars()) {
                    try {
                        ((Scania) car).raiseBoard();
                    } catch (Exception e) {
                    }
                }
                break;
            case LOWERBED:
                for (Car car :gameWorld.getCars()) {
                    try {
                        ((Scania) car).lowerBoard();
                    } catch (Exception e) {
                    }
                }
                break;
        }


    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : gameWorld.getCars()) {
            car.gas(gas);
        }
    }

    void startCars() {
        for (Car car : gameWorld.getCars()) {
            car.startEngine();
        }
    }

    void stopCars() {
        for (Car car : gameWorld.getCars()) {
            car.stopEngine();
        }
    }

    void brakeCars(double amount) {
        for (Car car : gameWorld.getCars()) {
            car.brake(amount);
        }
    }

    void turboOn() {
        for (Car car : gameWorld.getCars()) {
            try {
                ((Saab95) car).setTurboOn();
            } catch (Exception e) {
            }
        }
    }

    void turboOff() {
        for (Car car : gameWorld.getCars()) {
            try {
                ((Saab95) car).setTurboOff();
            } catch (Exception e) {
            }
        }
    }

    void liftBed() {
        for (Car car : gameWorld.getCars()) {
            try {
                ((Scania) car).raiseBoard();
            } catch (Exception e) {
            }
        }
    }

    void lowerBed() {
        for (Car car : gameWorld.getCars()) {
            try {
                ((Scania) car).lowerBoard();
            } catch (Exception e) {
            }
        }
    }

    /* Each step the TimerListener moves all thegameWorld.getCars() in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameWorld.update();
            frame.drawPanel.setVehicles(gameWorld.getCars());
            frame.drawPanel.repaint();
        }
    }
}