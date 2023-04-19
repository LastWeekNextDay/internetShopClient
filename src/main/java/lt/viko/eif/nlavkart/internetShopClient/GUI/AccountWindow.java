package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.Account;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveAccountRequest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AccountWindow {

    private JPanel windowPane;
    private JButton cartButton;
    private JButton deleteButton;
    private JButton printButton;
    private JLabel nameLabel;

    AbstractWindow previousWindow;

    public AccountWindow(Account account, ArrayList<Account> listOfAccounts, AbstractWindow previousWindow) {
        this.previousWindow = previousWindow;
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
                InteractClass interactClass = new InteractClass();
                interactClass.init();
                RemoveAccountRequest request = new RemoveAccountRequest();
                request.setAccountId(account.getId());
                interactClass.removeAccount(request);
                listOfAccounts.remove(account);
                previousWindow.update();
                frame.dispose();
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
