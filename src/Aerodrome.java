import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Aerodrome<T extends ITransport, G extends IIlluminator> {

    private final List<T> places;

    private final int maxCount;
    private final T[] places;

    private final int pictureWidth;

    private final int pictureHeight;

    private final int placeSizeWidth = 340;

    private final int placeSizeHeight = 125;

    public Aerodrome(int picWidth, int picHeight) {
        int width = picWidth / placeSizeWidth;
        int height = picHeight / placeSizeHeight;
        maxCount = width * height;
        places = new ArrayList<>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    public int add(T plane) {
        if (places.size() < maxCount) {
            places.add(plane);
            return places.size();
        }
        return -1;
    }

    public T delete(int index) {
        if (index >= 0 && index < maxCount && places.get(index) != null) {
            T plane = places.get(index);
            places.remove(index);
            return plane;
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
        return null;
    }

    public boolean equal(double number) {
        return number==places.size();
    }

    public boolean inequal(double number) {
        return number!=places.size();
    }

    public void Draw(Graphics g) {
        DrawMarking(g);

        for (int i = 0; i <places.size(); i++)
        for (int i = 0; i <places.length; i++)
        {
            places.get(i).setPosition(i % (pictureWidth / placeSizeWidth) * placeSizeWidth + 6,
                    i / (pictureWidth / placeSizeWidth) * placeSizeHeight + 5, pictureWidth, pictureHeight);
            places.get(i).DrawTransport(g);
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
    //возвр аэродром по индексу
    public T get(int index) {
        if (index > -1 && index < places.size()) {
            return places.get(index);
        }
        return null;
    }
}
