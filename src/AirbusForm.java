import javax.swing.*;
import java.awt.*;

public class AirbusForm {

    private JButton createPlaneButton;
    private JButton createAirbusButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<Integer> choiceCountIlluminatorButton;
    private JComboBox<String> choiceAddingButton;
    private Airbus airbus;
    private JFrame frame;
    private DrawPicture draw;
    private ITransport transport;



    public void direction(JButton button) {
        String temp = button.getName();
        switch (temp) {
            case "Up":
                transport.MoveTransport(Direction.Up);
                break;
            case "Down":
                transport.MoveTransport(Direction.Down);
                break;
            case "Left":
                transport.MoveTransport(Direction.Left);
                break;
            case "Right":
                transport.MoveTransport(Direction.Right);
                break;
        }
        frame.repaint();
    }

    public void initialization() {
        Icon up = new ImageIcon("images/up_arrow_30.jpg");
        Icon down = new ImageIcon("images/down_arrow_30.jpg");
        Icon left = new ImageIcon("images/left_arrow_30.jpg");
        Icon right = new ImageIcon("images/right_arrow_30.jpg");
        upButton = new JButton(up);
        upButton.setName("Up");
        upButton.setBounds(721, 372, 30, 30);
        upButton.addActionListener(e -> direction(upButton));

        downButton = new JButton(down);
        downButton.setName("Down");
        downButton.setBounds(721, 408, 30, 30);
        downButton.addActionListener(e -> direction(downButton));

        rightButton = new JButton(right);
        rightButton.setName("Right");
        rightButton.setBounds(757, 408, 30, 30);
        rightButton.addActionListener(e -> direction(rightButton));

        leftButton = new JButton(left);
        leftButton.setName("Left");
        leftButton.setBounds(685, 408, 30, 30);
        leftButton.addActionListener(e -> direction(leftButton));

        createPlaneButton = new JButton("Create Plane");
        createPlaneButton.setBounds(0, 0, 130, 30);
        createPlaneButton.addActionListener(e -> {
            transport = new Plane(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.YELLOW);
            transport.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
           // draw.setTransport(transport);
            frame.repaint();
        });

        createAirbusButton = new JButton("Create Airbus");
        createAirbusButton.setBounds(150, 0, 140, 30);
        createAirbusButton.addActionListener(e -> {
            transport = new Airbus(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.YELLOW, Color.BLUE,
                    true, true,  choiceAddingButton.getSelectedIndex(), choiceCountIlluminatorButton.getSelectedIndex());
            transport.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
          //  draw.setTransport(transport);
            frame.repaint();
        });

        choiceAddingButton = new JComboBox<>(new String[]{"Circle", "Oval", "Rectangle"});
        choiceAddingButton.setBounds(0, 40, 130, 30);

        choiceCountIlluminatorButton = new JComboBox<>(new Integer[]{10, 20, 30});
        choiceCountIlluminatorButton.setBounds(150, 40, 130, 30);
    }

    public AirbusForm() {
        draw = new DrawPicture();
        frame = new JFrame("Аэробус");
        frame.setSize(816, 489);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createPlaneButton);
        frame.getContentPane().add(createAirbusButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(choiceCountIlluminatorButton);
        frame.getContentPane().add(choiceAddingButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }

    public void setPlane(ITransport transport) {
        this.transport = transport;
        draw.setTransport(transport);
        frame.repaint();
    }
}