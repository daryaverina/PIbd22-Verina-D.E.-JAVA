import javax.swing.*;
import java.awt.*;

public class DrawAerodrome extends JPanel {
    private final Aerodrome<Plane, IIlluminator> PlaneCamp;

    public DrawAerodrome(Aerodrome<Plane, IIlluminator> PlaneCamp) {
        this.PlaneCamp = PlaneCamp;
    }

    protected void paintComponent(Graphics g) {
        if (PlaneCamp != null) {
            PlaneCamp.Draw(g);
        }
    }

    public Aerodrome<Plane, IIlluminator> getPlaneCamp() {
        return PlaneCamp;
    }
}
