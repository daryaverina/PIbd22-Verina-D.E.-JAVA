import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Aerodrome<T extends ITransport, G extends IIlluminator> {

    private final List<T> places;

    private final int maxCount;

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
        }
        return null;
    }

    /*private boolean CheckFreePlace(int indexPlace) {
        return places[indexPlace] == null;
    }*/


    public boolean equal(double number) {
        return number==places.size();
    }

    public boolean inequal(double number) {
        return number!=places.size();
    }

    public void Draw(Graphics g) {
        DrawMarking(g);

        for (int i = 0; i <places.size(); i++)
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
