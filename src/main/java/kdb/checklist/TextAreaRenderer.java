package kdb.checklist;

import javax.swing.*;
import java.awt.*;

public class TextAreaRenderer extends JPanel implements ListCellRenderer {
    // Arbitrary long string
//        private static final String LORUM_IPSUM = "Lorem ipsum dolor sit amet, consectetur "
//                + "adipiscing elit. Sed sit amet est eget mauris tempus vehicula. "
//                + "Nunc aliquet quis mi vitae fringilla. Aliquam laoreet eu metus "
//                + "sit amet iaculis. Curabitur imperdiet eu est et lobortis. Mauris "
//                + "eget est id lorem iaculis dictum. Aenean nisi libero, consectetur "
//                + "quis enim sed, pulvinar facilisis erat. Aliquam euismod nulla et "
//                + "dictum dictum. Etiam non ullamcorper sem. Nulla efficitur, quam "
//                + "cursus molestie euismod, dui lorem congue mi, placerat blandit "
//                + "turpis ex id libero. Nullam sit amet porta purus. Mauris scelerisque "
//                + "justo non orci vehicula, sed mattis sapien cursus. Aenean at porttitor "
//                + "dolor. Aenean euismod massa et libero egestas, sed lacinia eros aliquet. "
//                + "In tincidunt vehicula ex, a sollicitudin justo consequat ac. In in "
//                + "purus ut ligula sollicitudin porta quis eu neque. Aliquam erat volutpat.";

    //private static JPanel rootPanel;
    private int width;

    public TextAreaRenderer(int width) {

        setOpaque(true);
        this.width = width;
//    rootPanel = new JPanel(new BorderLayout());
//            rootPanel.setPreferredSize(new Dimension(400, 400));
//            rootPanel.add(scrollPane, BorderLayout.CENTER);
//            return rootPanel;
    }

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index, boolean isSelected, boolean cellHasFocus) {
        int width = this.width / 6;
        int lines = value.toString().length() / width + 1;

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
        //panel.add(new JLabel("Header label"), BorderLayout.BEFORE_FIRST_LINE);
        panel.add(textArea, BorderLayout.CENTER);
        if(isSelected){
//            this.setBackground(list.getSelectionBackground());
//            this.setForeground(list.getSelectionForeground());
            textArea.setBackground(list.getSelectionBackground());
            textArea.setForeground(list.getSelectionForeground());
            //setPreferredSize(new Dimension(list.getWidth(), 40));
        }else{
            //panel.setBackground(Color.WHITE);
//            this.setBackground(list.getBackground());
//            this.setForeground(list.getForeground());
            textArea.setBackground(list.getBackground());
            textArea.setForeground(list.getForeground());
            //setPreferredSize(new Dimension(list.getWidth(), 18));
        }

        return panel;
    }

//        public static JComponent getRootComponent() {
//            DefaultListModel<String> listModel = new DefaultListModel<>();
//            listModel.addAll(Arrays.asList("A short string", LORUM_IPSUM,
//                    "A second short string", LORUM_IPSUM));
//
//            JList<String> list = new JList<>();
//            list.setModel(listModel);
//            list.setCellRenderer(new TextAreaListTest());
//
//            JScrollPane scrollPane = new JScrollPane(list);
//
//            rootPanel = new JPanel(new BorderLayout());
//            rootPanel.setPreferredSize(new Dimension(400, 400));
//            rootPanel.add(scrollPane, BorderLayout.CENTER);
//            return rootPanel;
//        }
//
//        public static void main(String[] args) {
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    JFrame frame = new JFrame("TextArea List Test");
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    frame.add(getRootComponent(), BorderLayout.CENTER);
//                    frame.pack();
//                    frame.setLocationByPlatform(true);
//                    frame.setVisible(true);
//                }
//            });
//        }

}
