import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class FormPlaneConfig extends JDialog {

    private Plane transport;
    private Color transportColor;
    private IIlluminator transportAdding;
    private DrawPicture drawPicture;

    public FormPlaneConfig(Frame frame) {
        super(frame, true);
        setSize(900, 450);
        setLayout(null);

        //Панель выбора кузова
        JPanel panelTypeOfTransport = new JPanel();
        panelTypeOfTransport.setLayout(null);
        Border typeBorder = BorderFactory.createTitledBorder("Тип самолета");
        panelTypeOfTransport.setBorder(typeBorder);

        JLabel trackedVehicleLable = new JLabel("Самолет");
        trackedVehicleLable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        trackedVehicleLable.setBounds(10, 30, 190, 55);
        trackedVehicleLable.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel excavatorLable = new JLabel("Аэробус");
        excavatorLable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        excavatorLable.setBounds(10, 100, 190, 55);
        excavatorLable.setHorizontalAlignment(SwingConstants.CENTER);

        JCheckBox checkBoxStar = new JCheckBox("Звезда");
        JCheckBox checkBoxSecondLevel = new JCheckBox("Второй этаж");

        JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(1000, 1, 10000, 1));
        JSpinner spinnerWindowCount = new JSpinner(new SpinnerNumberModel(10, 10, 30, 10));


        MouseAdapter listenerTransportType = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel label = (JLabel) e.getSource();
                if(label.getText()=="Самолет"){
                    transport = new Plane((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE);
                }
                else if (label.getText()=="Аэробус"){
                    if (!checkBoxSecondLevel.isSelected()){
                        transport = new Airbus((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE, Color.WHITE,
                                checkBoxStar.isSelected(), checkBoxSecondLevel.isSelected(), 1, 0);
                    }
                    else {
                        transport = new Airbus((int) spinnerMaxSpeed.getValue(), (int) spinnerWeight.getValue(), Color.WHITE, Color.WHITE,
                                checkBoxStar.isSelected(), checkBoxSecondLevel.isSelected(), 1, (int)spinnerWindowCount.getValue()/10-1);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() + panelTypeOfTransport.getX() >= drawPicture.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() + panelTypeOfTransport.getX() <= drawPicture.getX() + drawPicture.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelTypeOfTransport.getY() >= drawPicture.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY() + panelTypeOfTransport.getY() <= drawPicture.getY() + drawPicture.getHeight()) {
                    transport.setPosition(10, 0, drawPicture.getWidth(), drawPicture.getHeight());
                    drawPicture.setTransport(transport);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transport = null;
            }
        };
        trackedVehicleLable.addMouseListener(listenerTransportType);
        excavatorLable.addMouseListener(listenerTransportType);
        panelTypeOfTransport.add(trackedVehicleLable);
        panelTypeOfTransport.add(excavatorLable);
        add(panelTypeOfTransport);
        panelTypeOfTransport.setBounds(10, 10, 210, 170);

        //Панель отрисовки
        drawPicture = new DrawPicture();
        getContentPane().add(drawPicture);
        drawPicture.setBounds(240, 18, 300, 160);
        drawPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        MouseListener listenerPanelDrawTransport = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (transport != null || (drawPicture.getTransport() instanceof Airbus && transportAdding != null)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (transport != null || transportAdding != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };
        drawPicture.addMouseListener(listenerPanelDrawTransport);

        //Панель параметров
        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Параметры");
        panelOptions.setBorder(typeBorder);
        JLabel labelMaxSpeed = new JLabel("Макс скорость");
        JLabel labelWight = new JLabel("Вес");
        panelOptions.setBounds(10, 185, 210, 110);
        labelMaxSpeed.setBounds(10, 20, 100, 15);
        spinnerMaxSpeed.setBounds(10, 35, 100, 25);
        labelWight.setBounds(10, 60, 100, 15);
        spinnerWeight.setBounds(10, 75, 100, 25);
        checkBoxStar.setBounds(115, 35, 90, 20);
        checkBoxSecondLevel.setBounds(115, 55, 90, 20);
        panelOptions.add(spinnerMaxSpeed);
        panelOptions.add(spinnerWeight);
        panelOptions.add(labelMaxSpeed);
        panelOptions.add(labelWight);
        panelOptions.add(checkBoxStar);
        panelOptions.add(checkBoxSecondLevel);

        add(panelOptions);


        //Панель выбора цвета
        JPanel panelColors = new JPanel();
        panelColors.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Цвет");
        panelColors.setBorder(typeBorder);

        JLabel labelMainColor = new JLabel("Базовый цвет");
        labelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMainColor.setBounds(10, 25, 100, 30);
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelDopColor = new JLabel("Дополнительный цвет");
        labelDopColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelDopColor.setBounds(120, 25, 140, 30);
        labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JPanel panelColor = (JPanel) e.getSource();
                transportColor = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawPicture.getTransport() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelMainColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelMainColor.getX() + labelMainColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelMainColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelMainColor.getY() + labelMainColor.getHeight()) {
                        drawPicture.getTransport().setMainColor(transportColor);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelDopColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelDopColor.getX() + labelDopColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelDopColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelDopColor.getY() + labelDopColor.getHeight() &&
                            drawPicture.getTransport() instanceof Airbus) {
                        Airbus excavator = (Airbus) drawPicture.getTransport();
                        excavator.setDopColor(transportColor);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transportColor = null;
            }
        };

        MouseListener listenerColorLabel = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Базовый цвет" -> {
                        if (drawPicture.getTransport() != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                    case "Дополнительный цвет" -> {
                        if (drawPicture.getTransport() instanceof Airbus) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (transportColor != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                }
            }
        };

        labelMainColor.addMouseListener(listenerColorLabel);
        labelDopColor.addMouseListener(listenerColorLabel);

        JPanel panelDarkRed = new JPanel();
        panelDarkRed.setBackground(new Color(176, 0, 0));
        panelDarkRed.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDarkRed.setBounds(10, 75, 30, 30);
        panelDarkRed.addMouseListener(listenerColor);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelYellow.setBounds(90, 75, 30, 30);
        panelYellow.addMouseListener(listenerColor);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlack.setBounds(160, 75, 30, 30);
        panelBlack.addMouseListener(listenerColor);

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelWhite.setBounds(230, 75, 30, 30);
        panelWhite.addMouseListener(listenerColor);

        JPanel panelDeepPink = new JPanel();
        panelDeepPink.setBackground(new Color(255, 20, 147));
        panelDeepPink.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDeepPink.setBounds(10, 120, 30, 30);
        panelDeepPink.addMouseListener(listenerColor);

        JPanel panelOrange = new JPanel();
        panelOrange.setBackground(Color.ORANGE);
        panelOrange.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelOrange.setBounds(90, 120, 30, 30);
        panelOrange.addMouseListener(listenerColor);

        JPanel panelDarkGreen = new JPanel();
        panelDarkGreen.setBackground(new Color(18, 73, 2));
        panelDarkGreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelDarkGreen.setBounds(160, 120, 30, 30);
        panelDarkGreen.addMouseListener(listenerColor);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panelBlue.setBounds(230, 120, 30, 30);
        panelBlue.addMouseListener(listenerColor);

        panelColors.setBounds(575, 10, 275, 170);
        add(panelColors);
        panelColors.add(labelMainColor);
        panelColors.add(labelDopColor);
        panelColors.add(panelDarkRed);
        panelColors.add(panelYellow);
        panelColors.add(panelBlack);
        panelColors.add(panelWhite);
        panelColors.add(panelDeepPink);
        panelColors.add(panelOrange);
        panelColors.add(panelDarkGreen);
        panelColors.add(panelBlue);

        //Панель выбора дополнения
        JPanel panelAdditions = new JPanel();
        panelAdditions.setLayout(null);
        typeBorder = BorderFactory.createTitledBorder("Дополнение");
        panelAdditions.setBorder(typeBorder);

        add(panelAdditions);
        panelAdditions.setBounds(240, 185, 570, 110);

        JLabel labelCircle = new JLabel("Круги");
        labelCircle.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelCircle.setBounds(10, 30, 120, 55);
        labelCircle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelOval = new JLabel("Овалы");
        labelOval.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelOval.setBounds(140, 30, 120, 55);
        labelOval.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelRect = new JLabel("Квадраты");
        labelRect.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelRect.setBounds(270, 30, 120, 55);
        labelRect.setHorizontalAlignment(SwingConstants.CENTER);

        panelAdditions.add(labelCircle);
        panelAdditions.add(labelOval);
        panelAdditions.add(labelRect);


        JLabel labelIllumCount = new JLabel("Количество иллюминаторов");

        labelIllumCount.setBounds(430, 30, 120, 15);
        spinnerWindowCount.setBounds(430, 50, 80, 25);

        panelAdditions.add(labelIllumCount);
        panelAdditions.add(spinnerWindowCount);

        MouseAdapter listenerAdding = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JLabel labelAdditions = (JLabel) e.getSource();
                switch (labelAdditions.getText()) {
                    case "Овалы" -> transportAdding = new IlluminatorOval((int)spinnerWindowCount.getValue()/10-1);
                    case "Квадраты" -> transportAdding = new IlluminatorSquare((int)spinnerWindowCount.getValue()/10-1);
                    case "Круги" -> transportAdding = new IlluminatorCircle((int)spinnerWindowCount.getValue()/10-1);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (drawPicture.getTransport() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() + panelAdditions.getX() >= drawPicture.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getAlignmentX() + panelAdditions.getX() <= drawPicture.getX() + drawPicture.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAdditions.getY() >= drawPicture.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() + panelAdditions.getY() <= drawPicture.getY() + drawPicture.getHeight() &&
                            drawPicture.getTransport() instanceof Airbus) {
                        Airbus excavator = (Airbus) drawPicture.getTransport();
                        excavator.setIllum(transportAdding);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                transportAdding = null;
            }
        };

        labelCircle.addMouseListener(listenerAdding);
        labelOval.addMouseListener(listenerAdding);
        labelRect.addMouseListener(listenerAdding);

        JButton buttonAdd = new JButton("Добавить");
        JButton buttonClear = new JButton("Отмена");
        add(buttonAdd);
        add(buttonClear);
        buttonAdd.setBounds(530, 300, 120, 30);
        buttonClear.setBounds(660, 300, 120, 30);
        buttonAdd.addActionListener(e -> dispose());
        buttonClear.addActionListener(e -> {
            drawPicture.setTransport(null);
            dispose();
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                drawPicture.setTransport(null);
            }
        });
        setVisible(true);
    }

    public ITransport getTransport() {
        return drawPicture.getTransport();
    }
}
