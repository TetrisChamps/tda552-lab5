package gui;

import javafx.util.Pair;
import model.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    private Map<String, BufferedImage> carImages = new HashMap<>();
    private Map<Point, Pair<BufferedImage,Pair<Integer,Integer>>> imageAndSizePoints = new HashMap<>();
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
            carImages.put("Volvo240", ImageIO.read(new File("resources\\Volvo240.jpg")));
            carImages.put("Scania", ImageIO.read(new File("resources\\Scania.jpg")));
            carImages.put("Saab95", ImageIO.read(new File("resources\\Saab95.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    BufferedImage getImageFromClass(String s) {
        return carImages.get(s);
    }

    void moveit(String s, int x, int y,Integer height,Integer width) {
        imageAndSizePoints.put(new Point(x, y),new Pair<>(carImages.get(s),new Pair<>(height,width)));
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Map.Entry<Point, Pair<BufferedImage,Pair<Integer,Integer>>> entry : imageAndSizePoints.entrySet()) {
            Point p = entry.getKey();
            BufferedImage i = entry.getValue().getKey();
            int height = entry.getValue().getValue().getKey();
            int width = entry.getValue().getValue().getValue();
            g.drawImage(i, p.x, p.y,width,height,null);
        }

        imageAndSizePoints.clear();
        //carPoints.forEach();
        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
    }
}