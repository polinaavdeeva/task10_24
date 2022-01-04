package ru.vsu.cs.avdeeva_p_a;


import utils.JTableUtils;
import utils.ListUtils;
import utils.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class FrameMain extends JFrame {
    private JTextField textFieldForPlaces;
    private JButton SelectButton;
    private JButton LoadButton;
    private JButton SaveButton;
    private JPanel panelMain;
    private JTable tableInput;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;
    private final JMenuBar menuBarMain;
    private final JMenu menuLookAndFeel;

    public FrameMain() {
        this.setTitle("Task 10");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();

        FindingIncomingStudents findingIncomingStudents = new FindingIncomingStudents();
        String[] columnNames = {"ФИО", "Физ", "Мат", "Рус", "Аттестат"};

        JTableUtils.initJTableForArray(tableInput, 150, true, true, true, false, 25, 15);
        List<List<String>> list2 = null;
        try {
            list2 = ListUtils.loadStringList2FromFile("example.txt");
            JTableUtils.writeArrayToJTable(tableInput, ListUtils.toStringArr2(list2), null);
            JTableUtils.renameJTableColumns(tableInput, columnNames);
        } catch (FileNotFoundException e) {
            SwingUtils.showErrorMessageBox(e);
        }

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("№24");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        SelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[][] str = JTableUtils.readStringArrayFromJTable(tableInput);
                    List<List<String>> inputList = ListUtils.toList2(str);
                    int budgetPlaces = Integer.parseInt(textFieldForPlaces.getText());

                    List<List<String>> outputList = findingIncomingStudents.findingIncomingStudents(inputList,budgetPlaces);

                    JTableUtils.writeArrayToJTable(tableInput, ListUtils.toStringArr2(outputList), null);
                    JTableUtils.renameJTableColumns(tableInput, columnNames);
                } catch (JTableUtils.JTableUtilsException jTableUtilsException) {
                    jTableUtilsException.printStackTrace();
                    SwingUtils.showErrorMessageBox(jTableUtilsException);
                }
            }
        });

        LoadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        List<List<String>> list2 = ListUtils.loadStringList2FromFile(fileChooserOpen.getSelectedFile().getPath());

                        JTableUtils.writeArrayToJTable(tableInput, ListUtils.toStringArr2(list2), null);

                        JTableUtils.renameJTableColumns(tableInput, columnNames);
                    }
                } catch (Exception exception) {
                    SwingUtils.showErrorMessageBox(exception);
                }
            }
        });

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[][] str = JTableUtils.readStringArrayFromJTable(tableInput);
                        List<List<String>> outputList = ListUtils.toList2(str);
                        String file = fileChooserSave.getSelectedFile().getPath();

                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }

                        ListUtils.writeList2ToFile(file, outputList);
                    }
                } catch (HeadlessException | JTableUtils.JTableUtilsException headlessException) {
                    headlessException.printStackTrace();
                    SwingUtils.showErrorMessageBox(headlessException);
                }
            }
        });
    }
}
