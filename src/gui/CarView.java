package gui;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an onVehicleAction fires of in
 * each of it's components.
 */
public class CarView extends JFrame {
    private ArrayList<VehicleObserver> observers = new ArrayList<>();

    DrawPanel drawPanel;

    private JPanel controlPanel = new JPanel();

    private JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    int gasAmount = 100;
    private JLabel gasLabel = new JLabel("Amount of gas");

    private JButton gasButton = new JButton("Gas");
    private JButton brakeButton = new JButton("Brake");
    private JButton turboOnButton = new JButton("Saab Turbo on");
    private JButton turboOffButton = new JButton("Saab Turbo off");
    private JButton liftBedButton = new JButton("Scania Lift Bed");
    private JButton lowerBedButton = new JButton("Lower Lift Bed");

    private JButton startButton = new JButton("Start all cars");
    private JButton stopButton = new JButton("Stop all cars");
    private JButton addCarButton = new JButton("Add car");
    private JButton removeCarButton = new JButton("Remove car");

    public CarView(String framename, int sizeX, int sizeY) {
        drawPanel = new DrawPanel(sizeX, sizeY);
        initComponents(framename, sizeX, sizeY + 240);
    }

    /**
     * Initialise the components of the frame
     */
    private void initComponents(String title, int x, int y) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(x, y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) ((JSpinner) e.getSource()).getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(addCarButton, 3);
        controlPanel.add(brakeButton, 4);
        controlPanel.add(turboOffButton, 5);
        controlPanel.add(lowerBedButton, 6);
        controlPanel.add(removeCarButton, 7);
        controlPanel.setPreferredSize(new Dimension((x / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(x / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(x / 5 - 15, 200));
        this.add(stopButton);

        gasButton.addActionListener(e -> notifyCarObserver(VehicleEvents.GAS));
        brakeButton.addActionListener(e -> notifyCarObserver(VehicleEvents.BREAK));
        startButton.addActionListener(e -> notifyCarObserver(VehicleEvents.START_ALL));
        stopButton.addActionListener(e -> notifyCarObserver(VehicleEvents.STOP_ALL));
        turboOnButton.addActionListener(e -> notifyCarObserver(VehicleEvents.TURBO_ON));
        turboOffButton.addActionListener(e -> notifyCarObserver(VehicleEvents.TURBO_OFF));
        liftBedButton.addActionListener(e -> notifyCarObserver(VehicleEvents.RAISE_BOARD));
        lowerBedButton.addActionListener(e -> notifyCarObserver(VehicleEvents.LOWER_BED));

        addCarButton.addActionListener(e -> notifyCarObserver(VehicleEvents.ADD));
        removeCarButton.addActionListener(e -> notifyCarObserver(VehicleEvents.REMOVE));

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();
        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Add an observer that should listen to events send from this observable
     *
     * @param observer The observer
     */
    public void addObserver(VehicleObserver observer) {
        observers.add(observer);
    }

    /**
     * Remove an observer
     *
     * @param observer The observer
     */
    public void deleteObserver(VehicleObserver observer) {
        observers.remove(observer);
    }

    /**
     * Broadcast an event to the observers
     *
     * @param actionEnum The action
     */
    public void notifyCarObserver(VehicleEvents actionEnum) {
        for (VehicleObserver actorObject : observers) {
            actorObject.onVehicleAction(actionEnum);
        }
    }
}