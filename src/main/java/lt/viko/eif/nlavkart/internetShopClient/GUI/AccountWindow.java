package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountWindow {
    private Account account;

    private int index;
    private JPanel windowPane;
    private JButton cartButton;
    private JButton deleteButton;
    private JButton printButton;
    private JLabel nameLabel;

    public AccountWindow(int index, Account account) {
        this.index = index;
        this.account = account;
        JFrame frame = new JFrame("AccountWindow");
        frame.setContentPane(windowPane);
        nameLabel.setText(account.getUsername());
        frame.setMinimumSize(new Dimension(150, 200));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // delete account
            }
        });
        cartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CartWindow(account);
            }
        });
        printButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // print account
            }
        });
    }
}
