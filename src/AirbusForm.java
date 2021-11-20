import javax.swing.*;
import java.awt.*;

public class AirbusForm {

    private JButton createButton;
    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
    private JComboBox<Integer> choiceButton;
    private Airbus airbus;
    private JFrame frame;
    private DrawPicture draw;

    public void direction(JButton button) {
        String temp = button.getName();
        switch (temp) {
            case "Up":
                airbus.moveTrans(Direction.Up);
                break;
            case "Down":
                airbus.moveTrans(Direction.Down);
                break;
            case "Left":
                airbus.moveTrans(Direction.Left);
                break;
            case "Right":
                airbus.moveTrans(Direction.Right);
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

        upButton.setEnabled(false);
        downButton.setEnabled(false);
        rightButton.setEnabled(false);
        leftButton.setEnabled(false);

        createButton = new JButton("Создать");
        createButton.setBounds(12, 40, 85, 23);
        createButton.addActionListener(e -> {
            airbus = new Airbus(100 + ((int) (Math.random() * 300)), 1000 + ((int) (Math.random() * 2000)), Color.BLUE,
                    Color.YELLOW, true, true, choiceButton.getSelectedIndex());
            airbus.setPosition(10 + ((int) (Math.random() * 100)), 10 + ((int) (Math.random() * 100)), 900, 500);
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            rightButton.setEnabled(true);
            leftButton.setEnabled(true);
            draw.setPlane(airbus);
            frame.repaint();
        });

        choiceButton = new JComboBox<>(new Integer[]{10, 20, 30});
        choiceButton.setBounds(12, 12, 85, 23);
    }

    public AirbusForm() {
        draw = new DrawPicture();
        frame = new JFrame("Аэробус");
        frame.setSize(816, 489);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(createButton);
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(choiceButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }
}
