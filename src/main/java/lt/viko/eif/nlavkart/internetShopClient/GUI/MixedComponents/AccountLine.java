package lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.Account;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountLine {
    public Account account;
    public JPanel AccountLinePanel;
    private JLabel AccountName;
    private JButton cartButton;
    private JButton printButton;
    private JButton deleteButton;

    public AccountLine(Account account) {
        this.account = account;
        AccountName.setText(this.account.getUsername());
    }
}
