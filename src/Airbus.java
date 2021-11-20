import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Airbus {
    private int _startPosX;
    private int _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private final int airbusWidth = 350;
    private final int airbusHeight = 150;
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
    public boolean Star;
    public boolean SecondLevel;
    private Illuminator illuminator;

    public float getStartPosX() {
        return _startPosX;
    }

    private void setStartPosX(int startPosX) {
        this._startPosX = startPosX;
    }

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    private void setMaxSpeed(int maxSpeed) {
        this.MaxSpeed = maxSpeed;
    }

    public float getWeight() {
        return Weight;
    }

    private void setWeight(float weight) {
        this.Weight = weight;
    }

    public Color getMainColor() {
        return MainColor;
    }

    private void setMainColor(Color mainColor) {
        this.MainColor = mainColor;
    }

    public Color getDopColor() {
        return DopColor;
    }

    private void setDopColor(Color dopColor) {
        this.DopColor = dopColor;
    }

    public boolean isStar() {
        return Star;
    }

    private void setStar(boolean star) {
        this.Star = star;
    }

    public boolean isSecondLevel() {
        return SecondLevel;
    }

    private void setSecondLevel(boolean level) {
        this.SecondLevel = level;
    }

    public Airbus(int maxSpeed, float weight, Color mainColor, Color dopColor,
                  boolean star, boolean secondlevel, int illuminatorcount) {
        this.MaxSpeed = maxSpeed;
        this.Weight = weight;
        this.MainColor = mainColor;
        this.DopColor = dopColor;
        this.Star = star;
        this.SecondLevel = secondlevel;
        illuminator = new Illuminator();
        illuminator.setNumber(illuminatorcount);
    }

    public void setPosition(int x, int y, int width, int height) {
        if (x >= 0 && x + airbusWidth < width && y >= 0 && y + airbusHeight < height) {
            _startPosX = x;
            _startPosY = y;
            _pictureWidth = width;
            _pictureHeight = height;
        }
    }

    public void moveTrans(Direction dir) {
        float step = MaxSpeed * 100 / Weight;
        switch (dir) {
            case Right:
                if (_startPosX + step < _pictureWidth - airbusWidth) {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX + step > 0) {
                    _startPosX -= step;
                }
                break;
            case Up:
                if (_startPosY + step > 0) {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - airbusHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    public void drawPicture(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine( _startPosX + 255, _startPosY + 100, _startPosX + 255, _startPosY + 110);
        g2.fillOval( _startPosX + 253, _startPosY + 110, 4, 4);
        g2.drawLine( _startPosX + 115, _startPosY + 103, _startPosX + 115, _startPosY + 112);
        g2.fillOval( _startPosX + 117, _startPosY + 110, 4, 4);
        g2.fillOval( _startPosX + 110, _startPosY + 110, 4, 4);
        g2.setStroke(new BasicStroke(2));

        g.drawOval( _startPosX + 65, _startPosY + 75, 220, 30);
        g.setColor(MainColor);
        g.fillOval( _startPosX + 65, _startPosY + 75, 220, 30);
        g.fillOval( _startPosX + 47, _startPosY + 75, 25, 12);
        g.setColor(Color.BLACK);
        g.drawOval( _startPosX + 47, _startPosY + 75, 25, 12);

        Polygon body = new Polygon();
        body.addPoint(_startPosX + 54, _startPosY + 75);
        body.addPoint(_startPosX + 165, _startPosY + 75);
        body.addPoint(_startPosX + 115, _startPosY + 103);
        body.addPoint(_startPosX + 85, _startPosY + 103);
        body.addPoint(_startPosX + 51, _startPosY + 87);
        g.setColor(MainColor);
        g.fillPolygon(body);
        g.setColor(Color.BLACK);
        g.drawLine( _startPosX + 54, _startPosY + 75, _startPosX + 165, _startPosY + 75);
        g.drawLine( _startPosX + 115, _startPosY + 103, _startPosX + 85, _startPosY + 103);
        g.drawLine( _startPosX + 85, _startPosY + 103, _startPosX + 51, _startPosY + 86);

        Polygon front = new Polygon();
        front.addPoint(_startPosX + 265, _startPosY + 80);
        front.addPoint(_startPosX + 290, _startPosY + 90);
        front.addPoint(_startPosX + 265, _startPosY + 100);
        g.setColor(Color.WHITE);
        g.fillPolygon(front);
        g.setColor(Color.BLACK);
        g.drawPolygon(front);
        Polygon front2 = new Polygon();
        front2.addPoint(_startPosX + 265, _startPosY + 90);
        front2.addPoint(_startPosX + 290, _startPosY + 90);
        front2.addPoint(_startPosX + 265, _startPosY + 100);
        g.fillPolygon(front2);


        if (!SecondLevel)
        {
            Polygon wing = new Polygon();
            wing.addPoint(_startPosX + 75, _startPosY + 75);
            wing.addPoint(_startPosX + 120, _startPosY + 75);
            wing.addPoint(_startPosX + 75, _startPosY + 25);
            wing.addPoint(_startPosX + 60, _startPosY + 25);
            g.fillPolygon(wing);
        }

        if (SecondLevel)
        {
            g.setColor(MainColor);
            g.fillOval( _startPosX + 3, _startPosY + 50, 25, 12);
            g.setColor(Color.BLACK);
            g.drawOval( _startPosX + 3, _startPosY + 50, 25, 12);

            Polygon secondlevel = new Polygon();
            secondlevel.addPoint(_startPosX + 20, _startPosY + 50);
            secondlevel.addPoint(_startPosX + 215, _startPosY + 50);
            secondlevel.addPoint(_startPosX + 265, _startPosY + 82);
            secondlevel.addPoint(_startPosX + 45, _startPosY + 82);
            secondlevel.addPoint(_startPosX + 4, _startPosY + 58);
            g.setColor(MainColor);
            g.fillPolygon(secondlevel);
            g.setColor(Color.BLACK);
            g.drawLine( _startPosX + 3, _startPosY + 58, _startPosX + 51, _startPosY + 86);
            g.drawLine( _startPosX + 11, _startPosY + 50, _startPosX + 215, _startPosY + 50);
            g.drawLine( _startPosX + 214, _startPosY + 50, _startPosX + 265, _startPosY + 82);
        }

        if (SecondLevel)
        {
            Polygon secondlevel2 = new Polygon();
            secondlevel2.addPoint(_startPosX + 25, _startPosY + 50);
            secondlevel2.addPoint(_startPosX + 70, _startPosY + 50);
            secondlevel2.addPoint(_startPosX + 25, _startPosY);
            secondlevel2.addPoint(_startPosX + 10, _startPosY);
            g.fillPolygon( secondlevel2);
        }


        if (Star)
        {
            int x_ = 35;
            int y_ = 30;

            Polygon starPolygon = new Polygon();
            starPolygon.addPoint(_startPosX + x_, _startPosY - 10 + y_);
            starPolygon.addPoint(_startPosX + 2 + x_, _startPosY - 3 + y_);
            starPolygon.addPoint(_startPosX + 10 + x_, _startPosY - 3 + y_);
            starPolygon.addPoint(_startPosX + 4 + x_, _startPosY + 1 + y_);
            starPolygon.addPoint(_startPosX + 6 + x_, _startPosY + 9 + y_);
            starPolygon.addPoint(_startPosX + x_, _startPosY + 4 + y_);
            starPolygon.addPoint(_startPosX - 6 + x_, _startPosY + 9 + y_);
            starPolygon.addPoint(_startPosX - 4 + x_, _startPosY + 1 + y_);
            starPolygon.addPoint(_startPosX - 10 + x_, _startPosY - 3 + y_);
            starPolygon.addPoint(_startPosX - 2 + x_, _startPosY - 3 + y_);
            g.setColor(DopColor);
            g.fillPolygon(starPolygon);
            g.setColor(Color.BLACK);
        }
        illuminator.DrawIlluminator(g, _startPosX, _startPosY, airbusWidth, airbusHeight);
        Polygon wings2 = new Polygon();
        wings2.addPoint(_startPosX + 65, _startPosY + 88);
        wings2.addPoint(_startPosX + 85, _startPosY + 88);
        wings2.addPoint(_startPosX + 50, _startPosY + 80);
        wings2.addPoint(_startPosX + 35, _startPosY + 80);
        g.fillPolygon(wings2);

        Polygon wings3 = new Polygon();
        wings3.addPoint(_startPosX + 145, _startPosY + 95);
        wings3.addPoint(_startPosX + 175, _startPosY + 95);
        wings3.addPoint(_startPosX + 140, _startPosY + 80);
        wings3.addPoint(_startPosX + 125, _startPosY + 80);
        g.fillPolygon(wings3);
    }
}
