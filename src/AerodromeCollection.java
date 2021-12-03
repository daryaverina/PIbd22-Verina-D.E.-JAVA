import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AerodromeCollection {

    private final HashMap<String, Aerodrome<ITransport, IIlluminator>> aerodromeStages;

    private final int pictureWidth;

    private final int pictureHeight;

    public AerodromeCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        aerodromeStages = new HashMap<>();
    }

    public Set<String> keys() {
        return aerodromeStages.keySet();
    }

    public void addAerodrome(String name) {
        if (aerodromeStages.containsKey(name)) {
            return;
        }
        aerodromeStages.put(name, new Aerodrome<>(pictureWidth, pictureHeight));
    }

    public void deleteAerodrome(String name) {
        aerodromeStages.remove(name);
    }

    //Доступ к парковке через "индексатор"
    public Aerodrome<ITransport, IIlluminator> get(String name) {
        if (aerodromeStages.containsKey(name)) {
            return aerodromeStages.get(name);
        }
        return null;
    }

    //индексатор с 2 параметрами
    public ITransport get(String name, int index) {
        if (aerodromeStages.containsKey(name)) {
            return aerodromeStages.get(name).get(index);
        }
        return null;
    }
}
