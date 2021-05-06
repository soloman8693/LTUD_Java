package View;

import Controller.DataDictionaryController;
import Controller.HistoryController;
import Model.DataDictionaryDTO;
import Model.HistoryDTO;
import Utils.ButtonColumn;
import Utils.Helper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Main extends JPanel {

    private static final long serialVersionUID = 1L;
    private static DataDictionaryController dataDictionaryController = new DataDictionaryController();
    private static HistoryController historyController = new HistoryController();
    private static JEditorPane jTextArea;
    private static JTextField field1;
    DefaultTableModel model;
    JTable table;
    DefaultTableModel model1;
    JTable table1;
    String key;
    Integer id;

    public Main() {

//        // set flow layout for the frame
//        this.getContentPane().setLayout(
//                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)
//        );
//
//        // create empty JTextField
//        field1 = new JTextField();
//
//        JButton btnSearch = new JButton();
//        btnSearch.setText("Search");
//        btnSearch.addActionListener(searchEvent());
//
//        jTextArea = new JEditorPane("text/html", "");
//
//        Border blackline = BorderFactory.createLineBorder(Color.black);
//        JPanel inputContainer = new JPanel(new GridLayout(1, 2));
//        inputContainer.setBorder(blackline);
//        inputContainer.add(field1);
//        inputContainer.add(btnSearch);
//
//        JTabbedPane tabbedPane = new JTabbedPane();
////        ImageIcon icon = createImageIcon("images/middle.gif");
//
//        JComponent panel1 = inputContainer;
//        tabbedPane.addTab("Tab 1", null, panel1,
//                "Does nothing");
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//        // add textfields to frame
//        add(inputContainer);
//        add(jTextArea);


        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = null;

        JComponent panel1 = makeHomepage("Panel #1");
        tabbedPane.addTab("Search", icon, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeListPanel("Panel #2");
        tabbedPane.addTab("List", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent panel3 = makeHistoryPage("Panel #3");
        tabbedPane.addTab("History", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JComponent panel4 = makeRandomPage(
                "Panel #4");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Random Feature", icon, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        JComponent panel5 = makeRandomEveryDay(
                "Panel #5");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("On this day Slang Word", icon, panel5,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(tabbedPane.getSelectedIndex() == 2) {
                    model1.setRowCount(0);
                    List<HistoryDTO> result = historyController.loadAll();
                    for (HistoryDTO tmp : result) {
                        model1.addRow(new Object[]{tmp.getInput(), tmp.getCreatedDate()});
                    }
                    table1.setModel(model1);
                }
            }
        });

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeHomepage(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // create empty JTextField
        field1 = new JTextField();

        JButton btnSearch = new JButton();
        btnSearch.setText("Search");
        btnSearch.addActionListener(searchEvent());

        jTextArea = new JEditorPane("text/html", "");

        Border blackline = BorderFactory.createLineBorder(Color.black);
        JPanel inputContainer = new JPanel(new GridLayout(1, 2));
        inputContainer.setBorder(blackline);
        inputContainer.add(field1);
        inputContainer.add(btnSearch);

        JTabbedPane tabbedPane = new JTabbedPane();
//        ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panel1 = inputContainer;
        tabbedPane.addTab("Tab 1", null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        // add textfields to frame
        panel.add(inputContainer);
        panel.add(jTextArea);
        return panel;
    }

    protected JComponent makeHistoryPage(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel list = new JPanel(new GridLayout(1, 2));
        String[] columnNames = {"Input", "Date"};

        model1 = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<HistoryDTO> result = historyController.loadAll();
        for (HistoryDTO tmp : result) {
            model1.addRow(new Object[]{tmp.getInput(), tmp.getCreatedDate()});
        }
        table1 = new JTable(model1);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table1);
        list.add(scrollPane);

        panel.add(list);
        return panel;
    }

    protected JComponent makeListPanel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel action = new JPanel(new GridLayout(2, 1));
        JPanel input = new JPanel();
        input.setLayout(new BoxLayout(input, BoxLayout.X_AXIS));
        JLabel lblKey = new JLabel("Keyword");
        JTextField txtKey = new JTextField();
        JLabel lblContent = new JLabel("Description");
        JTextArea jTextArea = new JTextArea();

        input.add(lblKey);
        input.add(txtKey);
        input.add(lblContent);
        input.add(jTextArea);

        JPanel buttonAction = new JPanel();
        buttonAction.setLayout(new BoxLayout(buttonAction, BoxLayout.X_AXIS));

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do stuff
                Integer rs = dataDictionaryController.update(key, txtKey.getText(), jTextArea.getText());
                if (rs > 0) {
                    model.setRowCount(0);
                    List<DataDictionaryDTO> result = dataDictionaryController.loadAll();
                    for (DataDictionaryDTO tmp : result) {
                        model.addRow(new Object[]{tmp.getIddata_dictionary(), tmp.getData_key(), tmp.getData_content()});
                    }
                    table.setModel(model);
                    JOptionPane.showMessageDialog(null, "Successful", "InfoBox: " + "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Fail", "InfoBox: " + "Thong Bao", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do stuff
                List<DataDictionaryDTO> check = dataDictionaryController.findByKey(txtKey.getText());
                if (check.size() > 0) {
                    JOptionPane.showMessageDialog(null, "Duplicated", "InfoBox: " + "Thong Bao", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Integer rs = dataDictionaryController.add(txtKey.getText(), jTextArea.getText());
                if (rs > 0) {
                    model.setRowCount(0);
                    List<DataDictionaryDTO> result = dataDictionaryController.loadAll();
                    for (DataDictionaryDTO tmp : result) {
                        model.addRow(new Object[]{tmp.getIddata_dictionary(), tmp.getData_key(), tmp.getData_content()});
                    }
                    table.setModel(model);
                    JOptionPane.showMessageDialog(null, "Successful", "InfoBox: " + "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Fail", "InfoBox: " + "Thong Bao", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //do stuff
                if (id == null) {
                    JOptionPane.showMessageDialog(null, "Please choose the specified item", "InfoBox: " + "Thong Bao", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...",
                        JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (input == 0) {
                    Integer rs = dataDictionaryController.delete(id);
                    if (rs > 0) {
                        model.setRowCount(0);
                        List<DataDictionaryDTO> result = dataDictionaryController.loadAll();
                        for (DataDictionaryDTO tmp : result) {
                            model.addRow(new Object[]{tmp.getIddata_dictionary(), tmp.getData_key(), tmp.getData_content()});
                        }
                        table.setModel(model);
                        JOptionPane.showMessageDialog(null, "Successful", "InfoBox: " + "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fail", "InfoBox: " + "Thong Bao", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonAction.add(btnUpdate);
        buttonAction.add(btnAdd);
        buttonAction.add(btnRemove);

        action.add(input);
        action.add(buttonAction);

        JPanel list = new JPanel(new GridLayout(1, 2));
        String[] columnNames = {"ID", "Slang", "Description"};

        model = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        List<DataDictionaryDTO> result = dataDictionaryController.loadAll();
        for (DataDictionaryDTO tmp : result) {
            model.addRow(new Object[]{tmp.getIddata_dictionary(), tmp.getData_key(), tmp.getData_content()});
        }
        table = new JTable(model);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() > -1) {
                    id = Integer.valueOf((Integer) table.getValueAt(table.getSelectedRow(), 0));
                    key = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
                    String decri = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
                    txtKey.setText(key);
                    jTextArea.setText(decri);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        list.add(scrollPane);

        panel.add(action);
        panel.add(list);
        return panel;
    }

    protected JComponent makeRandomEveryDay(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnRefresh = new JButton();
        btnRefresh.setText("Random Description");
        btnRefresh.addActionListener(searchEvent());

        jTextArea = new JEditorPane("text/html", "");

        Border blackline = BorderFactory.createLineBorder(Color.black);
        JPanel inputContainer = new JPanel();
        inputContainer.setLayout(new BoxLayout(inputContainer, BoxLayout.X_AXIS));
        inputContainer.setBorder(blackline);
        inputContainer.add(btnRefresh);

        JTabbedPane tabbedPane = new JTabbedPane();
//        ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panel1 = inputContainer;
        tabbedPane.addTab("Tab 1", null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        // add textfields to frame
        panel.add(inputContainer);
        panel.add(jTextArea);
        return panel;
    }

    protected JComponent makeRandomPage(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // create empty JTextField
        JButton btnRandomSlang = new JButton();
        btnRandomSlang.setText("Random Slang");
        btnRandomSlang.addActionListener(searchEvent());

        JButton btnDescrip = new JButton();
        btnDescrip.setText("Random Description");
        btnDescrip.addActionListener(searchEvent());

        jTextArea = new JEditorPane("text/html", "");

        Border blackline = BorderFactory.createLineBorder(Color.black);
        JPanel inputContainer = new JPanel(new GridLayout(1, 2));
        inputContainer.setBorder(blackline);
        inputContainer.add(btnRandomSlang);
        inputContainer.add(btnDescrip);

        JTabbedPane tabbedPane = new JTabbedPane();
//        ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panel1 = inputContainer;
        tabbedPane.addTab("Tab 1", null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        // add textfields to frame
        panel.add(inputContainer);
        panel.add(jTextArea);
        return panel;
    }

//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new Main();
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }


    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Main(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private ActionListener searchEvent() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "";
                List<DataDictionaryDTO> result = dataDictionaryController.search(field1.getText().equalsIgnoreCase("") ? "" : field1.getText());
                for (DataDictionaryDTO tmp : result) {
                    text += createResultTemplate(tmp);
                }
                jTextArea.setText(text);
                if (!field1.getText().equalsIgnoreCase(""))
                    historyController.add(field1.getText());
            }
        };
    }

    private ActionListener randomSlang() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "";
                List<DataDictionaryDTO> result = dataDictionaryController.search(field1.getText().equalsIgnoreCase("") ? "" : field1.getText());
                for (DataDictionaryDTO tmp : result) {
                    text += createResultTemplate(tmp);
                }
                jTextArea.setText(text);
                if (!field1.getText().equalsIgnoreCase(""))
                    historyController.add(field1.getText());
            }
        };
    }

    private ActionListener randomDescrip() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = "";
                List<DataDictionaryDTO> result = dataDictionaryController.search(field1.getText().equalsIgnoreCase("") ? "" : field1.getText());
                for (DataDictionaryDTO tmp : result) {
                    text += createResultTemplate(tmp);
                }
                jTextArea.setText(text);
                if (!field1.getText().equalsIgnoreCase(""))
                    historyController.add(field1.getText());
            }
        };
    }
    private String createResultTemplate(DataDictionaryDTO dto) {
        return "<hr>" +
                "<h2>" + dto.getData_key() + "</h2>"
                + "<p>" + dto.getData_content() + "</p><br>";
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });

        Helper.readFile();
    }
}
