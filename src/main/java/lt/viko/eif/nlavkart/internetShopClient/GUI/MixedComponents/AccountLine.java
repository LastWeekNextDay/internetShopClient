package lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents;

import lt.viko.eif.nlavkart.internetShopClient.generated.Account;

import javax.swing.*;

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
