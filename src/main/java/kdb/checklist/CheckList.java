package kdb.checklist;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.Box;

public class CheckList {
    private JList allItemsView;
    private JList selectedItemsView;
    private JButton copyButton;
    private JPanel rootPanel;
    private JTextPane newItemView;
    private JButton addNewItemBtn;
    private JSplitPane splitPane1;
    private DefaultListModel<String> allItemsListModel;
    private DefaultListModel<String> selectedItemsListModel;

    final static String allCategoriesFilePath = "allCategories.csv";
    final static String selectedCategoriesFilePath = "selectedCategories.csv";

    public CheckList() throws IOException {
        //$$$setupUI$$$();
        $$$setupUI$$$();
        initComponents();

    }

    protected void initComponents() throws IOException {
        //rootPanel = new JPanel();
        HtmlCellRenderer leftCellRenderer = new HtmlCellRenderer(200);
        HtmlCellRenderer rightCellRenderer = new HtmlCellRenderer(200);

        allItemsView.setCellRenderer(leftCellRenderer);
        allItemsView.setMinimumSize(new Dimension(100, 20));
        allItemsView.setMaximumSize(new Dimension(-1, 50));
        allItemsView.setBorder(new LineBorder(Color.GRAY, 1));

        selectedItemsView.setCellRenderer(rightCellRenderer);
        selectedItemsView.setMinimumSize(new Dimension(100, 20));
        selectedItemsView.setMaximumSize(new Dimension(-1, 50));
        selectedItemsView.setBorder(new LineBorder(Color.GRAY, 1));
        selectedItemsView.setPrototypeCellValue("1234567890");

        rootPanel.setSize(600, 400);
        newItemView.setMinimumSize(new Dimension(100, 20));
        newItemView.setMaximumSize(new Dimension(200, 20));

        // todo  в один метод
        allItemsListModel = initializeListModelAndView(allItemsListModel, allItemsView, allCategoriesFilePath);

        selectedItemsListModel = initializeListModelAndView(selectedItemsListModel, selectedItemsView, selectedCategoriesFilePath);

        EditListAction edit = new EditListAction();
        EditListAction rightEdit = new EditListAction();
        selectedItemsView.addKeyListener(new ListKeyListener(selectedItemsView, selectedItemsListModel, rightEdit));
        allItemsView.addKeyListener(new ListKeyListener(allItemsView, allItemsListModel, edit));

        MouseAction mouseAction = new MouseAction(selectedItemsView, rightEdit);
        selectedItemsView.addMouseListener(mouseAction);
        MouseAction allItemsMouseAction = new MouseAction(allItemsView, edit);
        allItemsView.addMouseListener(allItemsMouseAction);

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

    public void onClosing() throws IOException {

        FileWorker.writeItemsFile(selectedCategoriesFilePath, selectedItemsListModel.elements());
        FileWorker.writeItemsFile(allCategoriesFilePath, allItemsListModel.elements());
    }

    public DefaultListModel initializeListModelAndView(DefaultListModel model, JList listView, String path) throws IOException {
        List<String> items = FileWorker.readFile(path);
        model = new DefaultListModel();
        //items.forEach(x -> allItemsListModel.addElement(x));
        addAll(items, model);
        listView.setModel(model);
        return model;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void addItem(ActionEvent e) throws IOException {
        String newItemText = newItemView.getText();
        allItemsListModel.addElement(newItemText);
        FileWorker.writeFile(allCategoriesFilePath, newItemText);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane();
        allItemsView = new JList();
        scrollPane.setViewportView(allItemsView);
        allItemsView.setLayoutOrientation(JList.VERTICAL);
        Font allItemsViewFont = this.$$$getFont$$$(null, -1, 16, allItemsView.getFont());
        if (allItemsViewFont != null) allItemsView.setFont(allItemsViewFont);
        //GridBagConstraints gbc;
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(scrollPane);

        JScrollPane rightScrollPane = new JScrollPane();
        selectedItemsView = new JList();
        rightScrollPane.setViewportView(selectedItemsView);
        selectedItemsView.setLayoutOrientation(JList.VERTICAL);
        Font selectedItemsViewFont = this.$$$getFont$$$(null, -1, 16, selectedItemsView.getFont());
        if (selectedItemsViewFont != null) selectedItemsView.setFont(selectedItemsViewFont);
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(rightScrollPane);
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
        rootPanel.setEnabled(false);
        splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(250);
        splitPane1.setEnabled(true);
        splitPane1.setResizeWeight(0.5);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 4.0;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(splitPane1, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        splitPane1.setLeftComponent(scrollPane1);
        allItemsView = new JList();
        Font allItemsViewFont = this.$$$getFont$$$(null, -1, 16, allItemsView.getFont());
        if (allItemsViewFont != null) allItemsView.setFont(allItemsViewFont);
        scrollPane1.setViewportView(allItemsView);
        final JScrollPane scrollPane2 = new JScrollPane();
        splitPane1.setRightComponent(scrollPane2);
        selectedItemsView = new JList();
        Font selectedItemsViewFont = this.$$$getFont$$$(null, -1, 16, selectedItemsView.getFont());
        if (selectedItemsViewFont != null) selectedItemsView.setFont(selectedItemsViewFont);
        scrollPane2.setViewportView(selectedItemsView);
        copyButton = new JButton();
        Font copyButtonFont = this.$$$getFont$$$(null, -1, 16, copyButton.getFont());
        if (copyButtonFont != null) copyButton.setFont(copyButtonFont);
        copyButton.setText("Перенести в правую часть");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        rootPanel.add(copyButton, gbc);
        newItemView = new JTextPane();
        Font newItemViewFont = this.$$$getFont$$$(null, -1, 16, newItemView.getFont());
        if (newItemViewFont != null) newItemView.setFont(newItemViewFont);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        rootPanel.add(newItemView, gbc);
        addNewItemBtn = new JButton();
        Font addNewItemBtnFont = this.$$$getFont$$$(null, -1, 16, addNewItemBtn.getFont());
        if (addNewItemBtnFont != null) addNewItemBtn.setFont(addNewItemBtnFont);
        addNewItemBtn.setText("Добавить");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
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
