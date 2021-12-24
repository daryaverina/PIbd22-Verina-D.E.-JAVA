import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class AerodromeCollection {

    private final HashMap<String, Aerodrome<ITransport, IIlluminator>> aerodromeStages;

    private final int pictureWidth;

    private final int pictureHeight;

    private final String separator = ":";

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

    public boolean saveData(String filename) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("AerodromeCollection\n");
            for (Map.Entry<String, Aerodrome<ITransport, IIlluminator>> level : aerodromeStages.entrySet()) {
                fileWriter.write("Aerodrome" + separator + level.getKey() + '\n');
                ITransport transport;
                for (int i = 0; (transport = level.getValue().get(i)) != null; i++) {
                    if (transport.getClass().getSimpleName().equals("Plane")) {
                        fileWriter.write("Plane" + separator);
                    } else if (transport.getClass().getSimpleName().equals("Airbus")) {
                        fileWriter.write("Airbus" + separator);
                    }
                    fileWriter.write(transport.toString() + '\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadData(String filename) {
        if (!(new File(filename).exists())) {
            return false;
        }

        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("AerodromeCollection")) {
                aerodromeStages.clear();
            } else {
                return false;
            }

            ITransport transport = null;
            String key = "";
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Aerodrome")) {
                    key = line.split(separator)[1];
                    aerodromeStages.put(key, new Aerodrome<>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("Plane")) {
                        transport = new Plane(line.split(separator)[1]);
                    } else if (line.contains("Airbus")) {
                        transport = new Airbus(line.split(separator)[1]);
                    }
                    if ((aerodromeStages.get(key).add(transport))==-1) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveCamp(String filename, String key) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        if (!aerodromeStages.containsKey(key)) {
            return false;
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (aerodromeStages.containsKey(key))
                fileWriter.write("Aerodrome" + separator + key + '\n');
            ITransport transport;
            for (int i = 0; (transport = aerodromeStages.get(key).get(i)) != null; i++) {
                if (transport.getClass().getSimpleName().equals("Plane")) {
                    fileWriter.write("Plane" + separator);
                } else if (transport.getClass().getSimpleName().equals("Airbus")) {
                    fileWriter.write("Airbus" + separator);
                }
                fileWriter.write(transport.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadAerodrome(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;
            line = scanner.nextLine();
            if (line.contains("Aerodrome:")) {
                key = line.split(separator)[1];
                if (aerodromeStages.containsKey(key)) {
                    aerodromeStages.get(key).clear();
                } else {
                    aerodromeStages.put(key, new Aerodrome<>(pictureWidth, pictureHeight));
                }
            } else {
                return false;
            }
            ITransport transport = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("Plane")) {
                        transport = new Plane(line.split(separator)[1]);
                    } else if (line.contains("Airbus")) {
                        transport = new Airbus(line.split(separator)[1]);
                    }
                    if ((aerodromeStages.get(key).add(transport))==-1) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    //индексатор с 2 параметрами
    public ITransport get(String name, int index) {
        if (aerodromeStages.containsKey(name)) {
            return aerodromeStages.get(name).get(index);
        }
        return null;
    }
}
