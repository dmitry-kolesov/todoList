package kdb.checklist;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseAction implements MouseListener {
    private JList listView;
    private EditListAction listAction;
    private final static String MOUSE_DOUBLE_CLICKED = "mouse_double_clicked";

    public MouseAction(JList list, EditListAction listAction){
        this.listView = list;
        this.listAction = listAction;
        listView.getActionMap().put(MOUSE_DOUBLE_CLICKED, listAction);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            if(listAction != null) {
                listAction.activateAction(MOUSE_DOUBLE_CLICKED, listView);
            }
//            int index = list.locationToIndex(e.getPoint());
//            ListModel dlm = list.getModel();
//            Object item = dlm.getElementAt(index);;
//            list.ensureIndexIsVisible(index);
//            System.out.println("Double clicked on " + item);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
