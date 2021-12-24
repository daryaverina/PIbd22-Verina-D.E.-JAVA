import java.awt.*;
public class IlluminatorCircle implements IIlluminator{
    private IlluminatorCount countWin;

    public IlluminatorCircle(int number) {
        setDigit(number);
    }

    @Override
    public void setDigit(int number) {
        this.countWin = IlluminatorCount.getCount(number);
    }

    @Override
    public void draw(Graphics g, int _startPosX, int _startPosY, int _airbusWidth, int _airbusHeight) {

        int x = _startPosX + 70;
        for (int i = 0; i < 10; i++) {
            g.setColor(Color.WHITE);
            g.fillOval(x, _startPosY + 81, 7, 7);
            g.setColor(Color.BLACK);
            g.drawOval(x, _startPosY + 81, 7, 7);
            x += 18;
        }
        x = _startPosX + 60;
        if (countWin == IlluminatorCount.twenty || countWin == IlluminatorCount.thirty) {
            for (int i = 0; i < 10; i++) {
                g.setColor(Color.WHITE);
                g.fillOval(x, _startPosY + 71, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x, _startPosY + 71, 7, 7);
                x += 18;
            }
        }
        x = _startPosX + 50;
        if (countWin == IlluminatorCount.thirty) {
            for (int i = 0; i < 10; i++) {
                g.setColor(Color.WHITE);
                g.fillOval(x, _startPosY + 61, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(x, _startPosY + 61, 7, 7);
                x += 18;
            }
        }
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '.' + countWin.ordinal();
    }
}

