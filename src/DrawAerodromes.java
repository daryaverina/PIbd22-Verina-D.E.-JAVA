import javax.swing.*;
import java.awt.*;

public class DrawAerodromes extends JPanel {

    private final AerodromeCollection aerodromeCollection;
    private String selectedItem = null;

    public DrawAerodromes(AerodromeCollection aerodromeCollection) {
        this.aerodromeCollection = aerodromeCollection;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (selectedItem != null) {
            if (aerodromeCollection != null) {
                aerodromeCollection.get(selectedItem).Draw(g);
            }
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }
}
