package kdb.checklist;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Enumeration;

public class ListKeyListener implements java.awt.event.KeyListener {

    boolean ctrlPressed = false;
    boolean cPressed = false;
    JList listView;
    DefaultListModel listModel;
    EditListAction action;

    private static final KeyStroke ENTER = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    private static final KeyStroke DELETE = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0);

    public ListKeyListener(JList listView, DefaultListModel listModel, EditListAction action) {
        super();
        this.listView = listView;
        listView.getActionMap().put(ENTER, action);
        this.listModel = listModel;
        this.action = action;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_ENTER:
//                if (this.action != null && !action.getIsActionStarted()) {
//                    action.activateAction(ENTER, listView);
//                }
//                break;
            case KeyEvent.VK_C:
                cPressed = true;

                break;
            case KeyEvent.VK_CONTROL:
                ctrlPressed = true;
                break;
            case KeyEvent.VK_DELETE:
                DefaultListModel dlm = (DefaultListModel) listView.getModel();
               for (int i = listView.getSelectedIndices().length - 1; i >= 0; i--) {
                    dlm.removeElementAt(listView.getSelectedIndices()[i]);
                }

//                int first = 0;
//                int last = 0;
//                ArrayList<Interval> intervals = new ArrayList<Interval>();
//                if (listView.getSelectedIndices().length > 0) {
////                    int first = listView.getSelectedIndices()[0];
////                    int last = listView.getSelectedIndices()[0];
//                    for (int current = first; current < listView.getSelectedIndices().length - 1; current++) {
//                        if(listView.getSelectedIndices()[current] + 1 ==  listView.getSelectedIndices()[current + 1]){
//                            last = current + 1;
//                        }
//                        else{
//                            intervals.add(new Interval(first, last));
//                            first = current;
//                            last = first;
//                        }
//                    }
//                }
//
//                int i = 0;
//                .forEach(int x : );
//                listView.getSelectedIndices().
                break;
        }

        if (ctrlPressed && cPressed) {
//                System.out.println("Blocked CTRl+C");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringBuilder result = new StringBuilder();
            Enumeration<String> enumeration = listModel.elements();
            while (enumeration.hasMoreElements()) {
                result.append(enumeration.nextElement() + System.lineSeparator());
            }

//                StringBuilder b = new StringBuilder();
//                Collections.list(parent.selectedItemsListModel.elements()).forEach(o -> {b.append(o+ System.lineSeparator());});
            //Collections.list(parent.selectedItemsListModel.elements());

            StringSelection stringSelection = new StringSelection(result.toString());
            clipboard.setContents(stringSelection, null);
            e.consume();// Stop the event from propagating.
        }
    }
}
