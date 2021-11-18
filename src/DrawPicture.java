import javax.swing.*;
import java.awt.*;

public class DrawPicture extends JPanel {

    private ITransport transport;

    public void paintComponent(Graphics g) {
        if (transport != null) {
            transport.DrawTransport(g);
        }
    }

    public void setTransport(ITransport transport) {
        this.transport = transport;
    }

    public ITransport getTransport() {
        return transport;
    }

}
