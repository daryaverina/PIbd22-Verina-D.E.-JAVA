import java.awt.*;
public abstract class Vehicle implements ITransport {

    protected int _startPosX;

    protected int _startPosY;

    protected int _pictureWidth;

    protected int _pictureHeight;

    public int MaxSpeed;

    public float Weight;

    public Color MainColor;

    public void setPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawTransport(Graphics g);

    public abstract void MoveTransport(Direction direction);

    public int getMaxSpeed() {
        return MaxSpeed;
    }

    protected void setMaxSpeed(int maxSpeed) {
        this.MaxSpeed = maxSpeed;
    }

    public float getWeight() {
        return Weight;
    }

    protected void setWeight(float weight) {
        this.Weight = weight;
    }

    public Color getMainColor() {
        return MainColor;
    }

    public void setMainColor(Color mainColor) {
        this.MainColor = mainColor;
    }
}

