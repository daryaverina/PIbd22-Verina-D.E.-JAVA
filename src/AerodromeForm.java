import javax.swing.*;
import javax.swing.border.Border;
import java.util.LinkedList;
import java.util.List;

public class AerodromeForm {
    private JFrame frame;
    private JButton parkPlane;
    private JButton parkAirbus;
    private JButton takeTransport;
    private JButton addAerodrome;
    private JButton deleteAerodrome;
    private JButton putTransportIntoList;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private DrawAerodromes drawAerodromes;
    private JPanel groupBoxTake;
    private JPanel campsGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private JTextField aerodromesField;
    private JList<String> listBoxAerodromes;
    private Border borderTake;
    private Border borderAerodromes;
    private DefaultListModel<String> aerodromeList;
    // список куда уходят самолеты
    private LinkedList<Plane> listTransport;
    private AerodromeCollection aerodromeCollection;

    public AerodromeForm() {
        initialization();
        frame = new JFrame("Аэродромы");
        frame.setSize(1200, 564);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkPlane);
        frame.getContentPane().add(parkAirbus);
        frame.getContentPane().add(groupBoxTake);
        frame.getContentPane().add(drawAerodromes);
        frame.getContentPane().add(campsGroupBox);
        frame.repaint();
    }

    public void initialization() {
        listTransport = new LinkedList<>();
        aerodromeCollection = new AerodromeCollection(890, 525);
        drawAerodromes = new DrawAerodromes(aerodromeCollection);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderAerodromes = BorderFactory.createTitledBorder("Аэродромы");
        parkPlane = new JButton("Припарковать самолет");
        parkAirbus = new JButton("Припарковать аэробус");
        putTransportIntoList = new JButton("Поместить в список");
        addAerodrome = new JButton("Добавить аэродром");
        deleteAerodrome = new JButton("Удалить аэродром");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать из списка");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Кол-во: ");
        aerodromesField = new JTextField();
        aerodromeList = new DefaultListModel<>();
        listBoxAerodromes = new JList<>(aerodromeList);
        groupBoxTake = new JPanel();
        groupBoxTake.setLayout(null);
        groupBoxTake.add(placeText);
        groupBoxTake.add(placeTransport);
        groupBoxTake.add(takeTransport);
        groupBoxTake.add(putTransportIntoList);
        parkPlane.setBounds(850, 12, 300, 40);
        parkPlane.addActionListener(e -> {
            createPlane();
        });
        parkAirbus.setBounds(850, 60, 300, 40);
        parkAirbus.addActionListener(e -> {
            createAirbus();
        });
        groupBoxTake.setBounds(880, 110, 250, 160);
        placeText.setBounds(90, 20, 60, 30);
        placeTransport.setBounds(135, 20, 30, 30);
        putTransportIntoList.setBounds(40, 70, 170, 30);
        putTransportIntoList.addActionListener(e -> {
            placeIntoListTransport();
        });
        takeTransport.setBounds(40, 110, 170, 30);
        takeTransport.addActionListener(e -> {
            takeTransportFrame();
        });
        groupBoxTake.setBorder(borderTake);
        drawAerodromes.setBounds(0, 0, 890, 525);
        campsGroupBox = new JPanel();
        campsGroupBox.setLayout(null);
        campsGroupBox.setBounds(880, 270, 250, 240);
        campsGroupBox.setBorder(borderAerodromes);
        aerodromesField.setBounds(50, 30, 150, 20);
        listBoxAerodromes.setBounds(50, 90, 150, 100);
        listBoxAerodromes.addListSelectionListener(e -> {
            changeItemList();
        });
        addAerodrome.setBounds(50, 55, 150, 30);
        addAerodrome.addActionListener(e -> {
            addAerodrome();
        });
        deleteAerodrome.setBounds(50, 200, 150, 30);
        deleteAerodrome.addActionListener(e -> {
            deleteAerodrome();
        });
        campsGroupBox.add(aerodromesField);
        campsGroupBox.add(listBoxAerodromes);
        campsGroupBox.add(addAerodrome);
        campsGroupBox.add(deleteAerodrome);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
    }

    private void createPlane() {
        if (listBoxAerodromes.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                Plane transport = new Plane(100, 1000, colorDialog.getColor());
                if (aerodromeCollection.get(listBoxAerodromes.getSelectedValue()).add(transport)!=-1) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createAirbus() {
        if (listBoxAerodromes.getSelectedIndex() >= 0) {
            JColorChooser colorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, colorDialog);
            if (colorDialog.getColor() != null) {
                JColorChooser otherColorDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, otherColorDialog);
                if (otherColorDialog.getColor() != null) {
                    Plane transport = new Airbus(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                            true,  true, 0, 0);
                    if (aerodromeCollection.get(listBoxAerodromes.getSelectedValue()).add(transport)!=-1) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Аэродром не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void placeIntoListTransport() {
        if (listBoxAerodromes.getSelectedIndex() >= 0) {
            if (!placeTransport.getText().equals("")) {
                try {
                    Plane transport = (Plane) aerodromeCollection.get(listBoxAerodromes.getSelectedValue()).delete(Integer.parseInt(placeTransport.getText()));
                    if (transport != null) {
                        listTransport.add(transport);
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Не существующий транспорт", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reloadLevels() {
        int index = listBoxAerodromes.getSelectedIndex();
        aerodromeList.removeAllElements();
        int i = 0;
        for (String name : aerodromeCollection.keys()) {
            aerodromeList.add(i, name);
            i++;
        }
        int itemsCount = aerodromeList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxAerodromes.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxAerodromes.setSelectedIndex(index);
        }
    }

    private void addAerodrome() {
        if (!aerodromesField.getText().equals("")) {
            aerodromeCollection.addAerodrome(aerodromesField.getText());
            reloadLevels();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Введите название стоянки", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteAerodrome() {
        if (listBoxAerodromes.getSelectedIndex() >= 0) {
            int result = JOptionPane.showConfirmDialog(frame, "Удалить стоянку " + listBoxAerodromes.getSelectedValue() + "?", "Удаление",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                aerodromeCollection.deleteAerodrome(listBoxAerodromes.getSelectedValue());
                reloadLevels();
                frame.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Стоянка не выбрана", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void changeItemList() {
        drawAerodromes.setSelectedItem(listBoxAerodromes.getSelectedValue());
        frame.repaint();
    }

    private void takeTransportFrame() {
        if (!listTransport.isEmpty()) {
            AirbusForm excavatorForm = new AirbusForm();
            excavatorForm.setPlane(listTransport.get(0));
            listTransport.remove(0);
            frame.repaint();
        }
    }
}
