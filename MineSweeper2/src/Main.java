import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame ("MineSweeper!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
        MineSweeperPanel panel = new MineSweeperPanel();    //creates and instance of the panel
        frame.getContentPane().add(panel);//adds panel to the frame
        frame.setSize(400,400);//defines size of frame
        frame.setVisible(true);//sets frame as visible
    }
}
