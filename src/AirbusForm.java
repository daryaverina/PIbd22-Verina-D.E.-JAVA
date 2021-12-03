import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AirbusForm {

    private JButton upButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton downButton;
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
    }

    public AirbusForm() {
        draw = new DrawPicture();
        frame = new JFrame("Аэробус");
        frame.setSize(816, 489);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        initialization();
        frame.getContentPane().add(upButton);
        frame.getContentPane().add(downButton);
        frame.getContentPane().add(leftButton);
        frame.getContentPane().add(rightButton);
        frame.getContentPane().add(draw);
        draw.setBounds(0, 0, 900, 500);
        frame.repaint();
    }


    public void setPlane(ITransport transport) {
        this.transport = transport;
        draw.setTransport(transport);
        frame.revalidate();
        frame.repaint();
    }
}