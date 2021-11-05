import java.awt.*;

public class Window {

    private WindowCount count;

    public void setNumber(int number) {
        count = WindowCount.getCount(number);
    }

    public void DrawWindow(Graphics g, int _startPosX, int _startPosY, int _airbusWidth, int _airbusHeight) {
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
        if (count == WindowCount.twenty || count == WindowCount.thirty) {
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
        if (count == WindowCount.thirty) {
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
