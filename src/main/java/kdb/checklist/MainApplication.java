package kdb.checklist;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainApplication {

    static CheckList checkList;
    public static void main(String args[]) throws IOException {

        JFrame frame = new JFrame("Список заказов");
        frame.setSize(400,400);
        frame.setMinimumSize(new Dimension(400, 400));
        checkList = new CheckList();
        frame.setContentPane(checkList.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    checkList.onClosing();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                super.windowClosing(e);
            }
        });

        frame.pack();
        frame.setVisible(true);

    }
}
