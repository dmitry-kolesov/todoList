package kdb.checklist;

import javax.swing.*;
import java.awt.*;

public class TextAreaRenderer extends JPanel implements ListCellRenderer {
    private int width;

    public TextAreaRenderer(int width) {

        setOpaque(true);
        this.width = width;
    }

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index, boolean isSelected, boolean cellHasFocus) {
        int width = this.width / 6;
        int lines = value.toString().length() / width;

        this.removeAll();

        JTextArea textArea = new JTextArea(lines / 2, width / 2);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(value.toString());

        JPanel panel = this;//new JPanel();
        panel.setLayout(new BorderLayout());
        textArea.setEnabled(list.isEnabled());
        textArea.setFont(list.getFont());
        this.setEnabled(list.isEnabled());
        this.setFont(list.getFont());
        panel.add(textArea, BorderLayout.CENTER);
        if(isSelected){
            textArea.setBackground(list.getSelectionBackground());
            textArea.setForeground(list.getSelectionForeground());
        }else{
            textArea.setBackground(list.getBackground());
            textArea.setForeground(list.getForeground());
        }

        return panel;
    }
}
