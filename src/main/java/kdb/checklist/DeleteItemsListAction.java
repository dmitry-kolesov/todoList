package kdb.checklist;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItemsListAction extends AbstractAction
{
    private Class<?> modelClass;

    /*
     *	Display the popup editor when requested
     */
    public void actionPerformed(ActionEvent e)
    {
        JList list = (JList)e.getSource();
        ListModel model = list.getModel();


    }

    public void activateAction(JList list){
        //Action action = list.getActionMap().get(actionKey);

        if (this != null)
        {
            ActionEvent event = new ActionEvent(
                    list,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            this.actionPerformed(event);
        }
    }
}
