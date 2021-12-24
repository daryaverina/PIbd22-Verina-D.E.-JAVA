import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Airbus extends Plane {

    public Color DopColor;
    public boolean Star;
    public boolean SecondLevel;
    private IIlluminator Illum;

    public float getStartPosX() {
        return _startPosX;
    }

    public IIlluminator getIlum() {
        return Illum;
    }

    public void setIllum(IIlluminator illum) {
        this.Illum = illum;
    }


    public Color getDopColor() {
        return DopColor;
    }

    public void setDopColor(Color dopColor) {
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
                  boolean star, boolean secondlevel) {
        super(maxSpeed, weight, mainColor, 100, 100);
        this.MaxSpeed = maxSpeed;
        this.Weight = weight;
        this.MainColor = mainColor;
        this.DopColor = dopColor;
        this.Star = star;
        this.SecondLevel = secondlevel;
    }

    public Airbus(String info) {
        super("");
        String[] strs = info.split(separator);
        if (strs.length == 7) {
            MaxSpeed = Integer.parseInt(strs[0]);
            Weight = Float.parseFloat(strs[1]);
            MainColor = Color.decode(strs[2]);
            DopColor = Color.decode(strs[3]);
            Star = Boolean.parseBoolean(strs[4]);
            SecondLevel = Boolean.parseBoolean(strs[5]);
            if (strs[6].contains("null")) {
                Illum = null;
            } else {
                String[] argsAddition = strs[6].split("\\.");
                int digit = Integer.parseInt(argsAddition[1]);
                switch (argsAddition[0]) {
                    case "IlluminatorCircle" -> Illum = new IlluminatorCircle(digit);
                    case "IlluminatorOval" -> Illum = new IlluminatorOval(digit);
                    case "IlluminatorSquare" -> Illum = new IlluminatorSquare(digit);
                }
            }
        }
    }



    public void DrawTransport(Graphics g) {

        super.DrawTransport(g);

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
            secondlevel2.addPoint(_startPosX + 52, _startPosY + 50);
            secondlevel2.addPoint(_startPosX + 97, _startPosY + 50);
            secondlevel2.addPoint(_startPosX + 52, _startPosY);
            secondlevel2.addPoint(_startPosX + 37, _startPosY);
            g.fillPolygon( secondlevel2);
        }

        if (!SecondLevel)
        {

            Polygon wing = new Polygon();
            wing.addPoint(_startPosX + 75, _startPosY + 75);
            wing.addPoint(_startPosX + 120, _startPosY + 75);
            wing.addPoint(_startPosX + 75, _startPosY + 25);
            wing.addPoint(_startPosX + 60, _startPosY + 25);
            g.fillPolygon(wing);
        }

        if (Star)
        {
            int x_ = 72;
            int y_ = 38;
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
        if (Illum != null) {
            Illum.draw(g, _startPosX, _startPosY, planeWidth, planeHeight);
        }
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
    @Override
    public String toString() {
        return MaxSpeed + separator + Weight + separator + MainColor.getRGB() + separator + DopColor.getRGB() + separator
                + Star + separator + SecondLevel  + separator + Illum;
    }
}
