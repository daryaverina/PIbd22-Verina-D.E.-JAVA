import java.awt.*;
import java.lang.reflect.Array;

public class Aerodrome<T extends ITransport, G extends IIlluminator> {

    private final T[] places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 340;

    private final int placeSizeHeight = 125;

    public Aerodrome(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        places = (T[]) Array.newInstance(ITransport.class,width * height);
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public int add(T plane) {
        int i = 0;
        while (i < pictureHeight / placeSizeHeight)
        {
            int j = 0;
            while (j < pictureWidth /placeSizeWidth)
            {
                if (places[i * (pictureWidth / placeSizeWidth) + j] == null)
                {
                    places[i * (pictureWidth / placeSizeWidth) + j] = plane;
                    plane.setPosition(placeSizeWidth * j + 5, placeSizeHeight * i + 5, pictureWidth, pictureHeight);
                    return i;
                }
                j++;
            }
            i++;
        }
        return -1;
    }

    public T delete(int index) {
        if ((index > places.length) || (index < 0)) return null;
        else
        {
            if (places[index] == null) return null;
            else
            {
                T temp = places[index];
                places[index] = null;
                return temp;
            }
        }
    }

    public int count(){
        int counter = 0;
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    public boolean equal(double number) {
        return number==count();
    }

    public boolean inequal(double number) {
        return number!=count();
    }

    public void Draw(Graphics g) {
        DrawMarking(g);
        for (int i = 0; i <places.length; i++)
        {
            if(places[i]!=null)
                places[i].DrawTransport(g);
        }
    }

    private void DrawMarking(Graphics g) {
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j) {
                g.drawLine(i * placeSizeWidth, j * placeSizeHeight, (int) (i *
                        placeSizeWidth + placeSizeWidth -20), j * placeSizeHeight);
            }
            g.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth,
                    (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }
}
