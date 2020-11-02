package kdb.checklist;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListAction extends AbstractAction
{
    private JPopupMenu editPopup;
    private JTextField editTextField;
    private Class<?> modelClass;
    private Boolean isActionStarted = false;

    public EditListAction()
    {
        setModelClass(DefaultListModel.class);
    }

    public Boolean getIsActionStarted(){
        return isActionStarted;
    }

    protected void setModelClass(Class modelClass)
    {
        this.modelClass = modelClass;
    }

    protected void applyValueToModel(String value, ListModel model, int row)
    {
        DefaultListModel dlm = (DefaultListModel)model;
        dlm.set(row, value);
    }

    /*
     *	Display the popup editor when requested
     */
    public void actionPerformed(ActionEvent e)
    {
        JList list = (JList)e.getSource();
        ListModel model = list.getModel();

        if (! modelClass.isAssignableFrom(model.getClass())) return;

        //  Do a lazy creation of the popup editor

        if (editPopup == null)
            createEditPopup(list);

        //  Position the popup editor over top of the selected row

        int row = list.getSelectedIndex();
        Rectangle r = list.getCellBounds(row, row);

        editPopup.setPreferredSize(new Dimension(r.width, r.height));
        editPopup.show(list, r.x, r.y);

        //  Prepare the text field for editing

        editTextField.setText( list.getSelectedValue().toString() );
        editTextField.selectAll();
        editTextField.requestFocusInWindow();
    }

    /*
     *  Create the popup editor
     */
    private void createEditPopup(JList list)
    {
        //  Use a text field as the editor

        editTextField = new JTextField();
        Border border = UIManager.getBorder("List.focusCellHighlightBorder");
        editTextField.setBorder( border );

        //  Add an Action to the text field to save the new value to the model

        editTextField.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String value = editTextField.getText();
                ListModel model = list.getModel();
                int row = list.getSelectedIndex();
                applyValueToModel(value, model, row);
                editPopup.setVisible(false);
                isActionStarted = false;
            }
        });

        //  Add the editor to the popup

        editPopup = new JPopupMenu();
        editPopup.setBorder( new EmptyBorder(0, 0, 0, 0) );
        editPopup.add(editTextField);
    }

    public void activateAction(Object actionKey, JList list){
        Action action = list.getActionMap().get(actionKey);

        if (action != null)
        {
            isActionStarted = true;
            ActionEvent event = new ActionEvent(
                    list,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            action.actionPerformed(event);
        }
    }
}
