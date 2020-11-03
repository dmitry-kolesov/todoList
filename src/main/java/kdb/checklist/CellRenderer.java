package kdb.checklist;

import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CellRenderer extends JTextArea implements ListCellRenderer<String> {

    public CellRenderer() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLineWrap(true);
        //setBorder(new LineBorder(Color.BLACK, 2));
    }

    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value);
        Border border = null;
        //if (cellHasFocus) {
            if (isSelected) {
                border = DefaultLookup.getBorder(this, ui, "List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = DefaultLookup.getBorder(this, ui, "List.focusCellHighlightBorder");
            }
        //s}
//        else {
//            border = getNoFocusBorder();
//        }
        setBorder(border);
        //add(testLabel);
        return this;
    }

}