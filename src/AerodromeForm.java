import javax.swing.*;
import javax.swing.border.Border;

public class AerodromeForm {
    private JFrame frame;
    private JButton parkPlane;
    private JButton parkAirbus;
    private JButton takeTransport;
    private JButton compareEquality;
    private JTextField placeTransport;
    private JTextField countPlaceTransport;
    private Aerodrome<Plane, IIlluminator> aerodrome;
    private DrawAerodrome drawAerodrome;
    private JPanel groupBox;
    private JPanel equateGroupBox;
    private JLabel placeText;
    private JLabel placeCountText;
    private Border borderTake;
    private Border borderCompare;

    public AerodromeForm() {
        initialization();
        frame = new JFrame("Стоянка");
        frame.setSize(1200, 564);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(parkPlane);
        frame.getContentPane().add(parkAirbus);
        frame.getContentPane().add(groupBox);
        frame.getContentPane().add(equateGroupBox);
        frame.getContentPane().add(drawAerodrome);
        frame.repaint();
    }

    public void initialization() {
        aerodrome = new Aerodrome<>(890, 525);
        drawAerodrome = new DrawAerodrome(aerodrome);
        borderTake = BorderFactory.createTitledBorder("Забрать транспорт");
        borderCompare = BorderFactory.createTitledBorder("");
        parkPlane = new JButton("Припарковать самолет");
        parkAirbus = new JButton("Припарковать аэробус");
        compareEquality = new JButton("Сравнить");
        placeTransport = new JTextField();
        countPlaceTransport = new JTextField();
        takeTransport = new JButton("Забрать");
        placeText = new JLabel("Место: ");
        placeCountText = new JLabel("Число: ");
        groupBox = new JPanel();
        groupBox.setLayout(null);
        groupBox.add(placeText);
        groupBox.add(placeTransport);
        groupBox.add(takeTransport);
        parkPlane.setBounds(850, 12, 300, 40);
        parkPlane.addActionListener(e -> createPlane());
        parkAirbus.setBounds(850, 60, 300, 40);
        parkAirbus.addActionListener(e -> createExcavator());
        groupBox.setBounds(930, 150, 150, 100);
        placeText.setBounds(40, 20, 60, 30);
        placeTransport.setBounds(85, 20, 30, 30);
        takeTransport.setBounds(40, 60, 90, 30);
        takeTransport.addActionListener(e -> takePlane());
        groupBox.setBorder(borderTake);
        drawAerodrome.setBounds(0, 0, 890, 525);
        equateGroupBox = new JPanel();
        equateGroupBox.setLayout(null);
        equateGroupBox.setBorder(borderCompare);
        equateGroupBox.add(compareEquality);
       // equateGroupBox.add(compareInequality);
        equateGroupBox.add(countPlaceTransport);
        equateGroupBox.add(placeCountText);
        equateGroupBox.setBounds(930, 300, 150, 110);
        placeCountText.setBounds(40, 20, 60, 30);
        countPlaceTransport.setBounds(85, 20, 30, 30);
        compareEquality.setBounds(40, 60, 90, 30);
        compareEquality.addActionListener(e -> compare());
    }

    private void createPlane() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            Plane transport = new Plane(100, 1000, colorDialog.getColor());
            if (aerodrome.add(transport)!=-1) {
                frame.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
            }
        }
    }

    private void createExcavator() {
        JColorChooser colorDialog = new JColorChooser();
        JOptionPane.showMessageDialog(frame, colorDialog);
        if (colorDialog.getColor() != null) {
            JColorChooser otherColorDialog = new JColorChooser();
            JOptionPane.showMessageDialog(frame, otherColorDialog);
            if (otherColorDialog.getColor() != null) {
                Plane transport = new Airbus(100, 1000, colorDialog.getColor(), otherColorDialog.getColor(),
                        true, true,  2, 0);
                if (aerodrome.add(transport)!=-1) {
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Аэродром переполнен");
                }
            }
        }
    }

    private void takePlane() {
        if (!placeTransport.getText().equals("")) {
            try {

                Plane transport = aerodrome.delete(Integer.parseInt(placeTransport.getText()));
                if (transport != null) {
                    AirbusForm airbusForm = new AirbusForm();
                    airbusForm.setPlane(transport);
                    frame.repaint();

                } else {
                    JOptionPane.showMessageDialog(frame, "Транспорта не существует");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Транспорта не существует");
            }
        }

    }

    private void compare() {
        int number = Integer.parseInt(countPlaceTransport.getText());
        if (!countPlaceTransport.getText().equals("")) {
            if (aerodrome.equal(number)) {
                JOptionPane.showMessageDialog(frame, "РАВНО");
            }
            if (aerodrome.inequal(number)) {
                JOptionPane.showMessageDialog(frame, "НЕ РАВНО");
            }
        }
    }
}
