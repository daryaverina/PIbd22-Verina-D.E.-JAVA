import java.awt.*;
public interface IIlluminator {

    void setDigit(int number);

    void draw(Graphics g, int startPosX, int startPosY, int planeWidth, int planeHeight);
}
