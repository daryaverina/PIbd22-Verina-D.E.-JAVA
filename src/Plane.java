import java.awt.*;
public class Plane extends Vehicle{

    protected int planeWidth = 300;
    protected int planeHeight = 115;

    public Plane(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }

    protected Plane(int maxSpeed, float weight, Color mainColor, int planeWidth, int
            planeHeight)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
    }

    @Override
    public void DrawTransport(Graphics g) {
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

        //начальное кол-во окон

        int x = _startPosX + 70;
        for (int i = 0; i < 10; i++)
        {
            g.setColor(Color.WHITE);
            g.fillOval( x, _startPosY + 81, 5, 7);
            g.setColor(Color.BLACK);
            g.drawOval( x, _startPosY + 81, 5, 7);
            x += 18;
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

        Polygon wings4 = new Polygon();
        wings4.addPoint(_startPosX + 75, _startPosY + 75);
        wings4.addPoint(_startPosX + 120, _startPosY + 75);
        wings4.addPoint(_startPosX + 75, _startPosY + 25);
        wings4.addPoint(_startPosX + 60, _startPosY + 25);
        g.fillPolygon(wings4);

    }
    @Override
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - planeWidth)
                {
                    _startPosX += step;
                }
                break;
            //влево
            case Left:
                if (_startPosX + step > 0)
                {
                    _startPosX -= step;
                }

                break;
            //вверх
            case Up:
                if (_startPosY + step > 0)
                {
                    _startPosY -= step;
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - planeHeight)
                {
                    _startPosY += step;
                }
                break;
        }
    }

}
