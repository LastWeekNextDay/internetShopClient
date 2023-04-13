package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    private JButton createAccountButton;
    private JButton viewItemsButton;
    private JTextArea accountIdFinderTextBox;
    private JButton findAccountButton;
    private JButton viewAllAccountsButton;
    private javax.swing.JPanel JPanel;
    private JPanel MainPanel;

    public MainWindow() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // create account
            }
        });

        viewItemsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // view items
            }
        });

        findAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // find account
            }
        });

        viewAllAccountsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // view all accounts
            }
        });
    }

}
