package gui;

import javafx.util.Pair;
import model.Car;
import model.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    private Map<String, BufferedImage> carImages = new HashMap<>();
    private List<Vehicle> vehicles = new ArrayList<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "src\\pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // Rememember to right click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            // Linux users need to modify \ to / in path string
            //TODO
            carImages.put("Volvo240", ImageIO.read(new File("resources/Volvo240.jpg")));
            carImages.put("Scania", ImageIO.read(new File("resources/Scania.jpg")));
            carImages.put("Saab95", ImageIO.read(new File("resources/Saab95.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Vehicle v : vehicles) {
            BufferedImage i = carImages.get(v.getModelName());
            if (i == null) {
                continue;
            }
            g.drawImage(i, (int) Math.floor(v.getX()), (int) Math.floor(v.getY()), v.getWidth(), v.getHeight(), null);
        }
    }
}