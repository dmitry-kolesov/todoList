package kdb.checklist;

import javax.swing.*;
import java.awt.*;

public class HtmlCellRenderer extends DefaultListCellRenderer {
    public static final String HTML_1 = "<html><body style='width: %d px; min-width: %d px; max-height:50px; vertical-align:top;'>";
    //public static final String HTML_2 = "px'>";
    public static final String HTML_3 = "</body></html>";
    private int width;

    public HtmlCellRenderer(int width) {
        this.width = width;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        String text = String.format(HTML_1 ,width,width) + value.toString()
                + HTML_3;
//        if(isSelected){
//            setBackground(Color.YELLOW);
//            setPreferredSize(new Dimension(list.getWidth(), 40));
//        }else{
//            setBackground(Color.PINK);
//            setPreferredSize(new Dimension(list.getWidth(), 18));
//        }
        return super.getListCellRendererComponent(list, text, index, isSelected,
                cellHasFocus);
    }
}

