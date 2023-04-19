package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.AccountLine;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.AccountLineListRenderer;
import lt.viko.eif.nlavkart.internetShopClient.Main;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.GetAccountRequest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.GetAccountResponse;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.GetAccountsResponse;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClass;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    private JButton createAccountButton;
    private JButton viewItemsButton;
    private JTextArea accountIdFinderTextBox;
    private JButton findAccountButton;
    private JButton viewAllAccountsButton;
    private JPanel MainPanel;
    private JList list1;
    private JScrollPane scrollPane;

    public MainWindow() {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        scrollPane.setViewportView(list1);
        DefaultListModel<AccountLine> listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        list1.setLayoutOrientation(JList.VERTICAL);
        list1.setCellRenderer(new AccountLineListRenderer());

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
                listModel.clear();
                InteractClass interaction = new InteractClass();
                interaction.init();
                GetAccountRequest request = new GetAccountRequest();
                if (accountIdFinderTextBox.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID or name.");
                    return;
                }
                try {
                    Integer.parseInt(accountIdFinderTextBox.getText());
                    request.setAccountId(Integer.parseInt(accountIdFinderTextBox.getText()));
                } catch (NumberFormatException ex) {
                    request.setAccountId(-1);
                    request.setUsername(accountIdFinderTextBox.getText());
                }
                GetAccountResponse response = interaction.getAccount(request);
                if (response.getAccount() == null) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                    return;
                }
                listModel.addElement(new AccountLine(response.getAccount()));
            }
        });
        viewAllAccountsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listModel.clear();
                InteractClass interaction = new InteractClass();
                interaction.init();
                GetAccountsResponse response = interaction.getAccounts();
                for (int i = 0; i < response.getAccounts().size(); i++) {
                    listModel.addElement(new AccountLine(response.getAccounts().get(i)));
                }
            }
        });
        accountIdFinderTextBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                accountIdFinderTextBox.setText("");
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                if (list1.getSelectedValue() == null) {
                    return;
                }
                AccountLine accountLine = (AccountLine) list1.getSelectedValue();
                new AccountWindow(list1.getSelectedIndex(), accountLine.account);
            }
        });
    }

}
