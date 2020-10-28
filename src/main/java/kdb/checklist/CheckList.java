package kdb.checklist;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class CheckList {
    private JList allItemsView;
    private JList selectedItemsView;
    private JButton copyButton;
    private JPanel rootPanel;
    private JTextPane newItemView;
    private JButton addNewItemBtn;
    private DefaultListModel allItemsListModel;
    private DefaultListModel selectedItemsListModel;

    final static String filePath = "categories.csv";

    public CheckList() throws IOException {
        initComponents();

    }

    protected void initComponents() throws IOException {
        //rootPanel = new JPanel();
        rootPanel.setSize(400, 400);
        allItemsView.setBorder(new LineBorder(Color.GRAY, 1));
        selectedItemsView.setBorder(new LineBorder(Color.GRAY, 1));
        newItemView.setMinimumSize(new Dimension(100, 20));

        selectedItemsView.addKeyListener(new MyKeyListener(this));

        List<String> items = FileWorker.readFile(filePath);
        allItemsListModel = new DefaultListModel();
        //items.forEach(x -> allItemsListModel.addElement(x));
        addAll(items, allItemsListModel);

        allItemsView.setModel(allItemsListModel);
        selectedItemsListModel = new DefaultListModel();
        selectedItemsView.setModel(selectedItemsListModel);

        copyButton.addActionListener(e -> moveItemToRight(e));
        addNewItemBtn.addActionListener(new AbstractAction() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    addItem(e);
                                                } catch (IOException ioException) {
                                                    ioException.printStackTrace();
                                                }
                                            }
                                        }
//        e -> {
//            try {
//
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
        );
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void addItem(ActionEvent e) throws IOException {
        String newItemText = newItemView.getText();
        allItemsListModel.addElement(newItemText);
        FileWorker.writeFile(filePath, newItemText);
        newItemView.setText("");
    }

    private void moveItemToRight(ActionEvent e) {
        addAll(allItemsView.getSelectedValuesList(), selectedItemsListModel);
        //selectedItemsListModel.addAll(allItemsView.getSelectedValuesList());
    }

    private void addAll(List source, DefaultListModel model) {
        source.forEach(x -> model.addElement(x));
        //selectedItemsListModel.addAll(allItemsView.getSelectedValuesList());
    }

    private static class MyKeyListener implements KeyListener {


        boolean ctrlPressed = false;
        boolean cPressed = false;
        CheckList parent;

        public MyKeyListener(CheckList parent) {
            super();
            this.parent = parent;
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
                case KeyEvent.VK_C:
                    cPressed = true;

                    break;
                case KeyEvent.VK_CONTROL:
                    ctrlPressed = true;
                    break;
            }

            if (ctrlPressed && cPressed) {
//                System.out.println("Blocked CTRl+C");
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringBuilder result = new StringBuilder();
                Enumeration<String> enumeration = parent.selectedItemsListModel.elements();
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        allItemsView = new JList();
        Font allItemsViewFont = this.$$$getFont$$$(null, -1, 16, allItemsView.getFont());
        if (allItemsViewFont != null) allItemsView.setFont(allItemsViewFont);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(allItemsView, gbc);
        selectedItemsView = new JList();
        Font selectedItemsViewFont = this.$$$getFont$$$(null, -1, 16, selectedItemsView.getFont());
        if (selectedItemsViewFont != null) selectedItemsView.setFont(selectedItemsViewFont);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(selectedItemsView, gbc);
        copyButton = new JButton();
        copyButton.setText("Copy To Right");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(copyButton, gbc);
        newItemView = new JTextPane();
        Font newItemViewFont = this.$$$getFont$$$(null, -1, 16, newItemView.getFont());
        if (newItemViewFont != null) newItemView.setFont(newItemViewFont);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(newItemView, gbc);
        addNewItemBtn = new JButton();
        addNewItemBtn.setText("+");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rootPanel.add(addNewItemBtn, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
