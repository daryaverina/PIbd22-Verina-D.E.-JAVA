import java.awt.*;

public class Illuminator {

    private IlluminatorCount count;

    public void setNumber(int number) {
        count = IlluminatorCount.getCount(number);
    }

    public void DrawIlluminator(Graphics g, int _startPosX, int _startPosY, int _airbusWidth, int _airbusHeight) {
        int x = _startPosX + 70;
        for (int i = 0; i < 10; i++)
        {
            g.setColor(Color.WHITE);
            g.fillOval( x, _startPosY + 81, 5, 7);
            g.setColor(Color.BLACK);
            g.drawOval( x, _startPosY + 81, 5, 7);
            x += 18;
        }
        x = _startPosX + 60;
        if (count == IlluminatorCount.twenty || count == IlluminatorCount.thirty) {
            for (int i = 0; i < 10; i++)
            {
                g.setColor(Color.WHITE);
                g.fillOval( x, _startPosY + 71, 5, 7);
                g.setColor(Color.BLACK);
                g.drawOval( x, _startPosY + 71, 5, 7);
                x += 18;
            }
        }
        x = _startPosX + 50;
        if (count == IlluminatorCount.thirty) {
            for (int i = 0; i < 10; i++)
            {
                g.setColor(Color.WHITE);
                g.fillOval( x, _startPosY + 61, 5, 7);
                g.setColor(Color.BLACK);
                g.drawOval( x, _startPosY + 61, 5, 7);
                x += 18;
            }
        }
    }
}
