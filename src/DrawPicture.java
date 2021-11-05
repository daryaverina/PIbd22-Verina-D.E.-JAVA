import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel {

    private Airbus airbus;

    public void paintComponent(Graphics g) {
        if (airbus != null) {
            airbus.drawPicture(g);
        }
    }

    public void setPlane(Airbus airbus) {
        this.airbus = airbus;
    }

    public Airbus getPlane() {
        return airbus;
    }
}