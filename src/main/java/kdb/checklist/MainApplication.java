package kdb.checklist;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApplication {
    public static void main(String args[]) throws IOException {

        JFrame frame = new JFrame("Список заказов");
        frame.setSize(400,400);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setContentPane(new CheckList().getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
