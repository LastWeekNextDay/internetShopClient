package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.UpdateFunc;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.AccountLine;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.AccountLineListRenderer;
import lt.viko.eif.nlavkart.internetShopClient.REST.forClient.InteractClassRest;
import lt.viko.eif.nlavkart.internetShopClient.generated.Account;
import lt.viko.eif.nlavkart.internetShopClient.generated.GetAccountRequest;
import lt.viko.eif.nlavkart.internetShopClient.generated.GetAccountResponse;
import lt.viko.eif.nlavkart.internetShopClient.generated.GetAccountsResponse;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClassSoap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainWindow extends ServiceUsageVar implements UpdateFunc {
    private int numOfAccountsInList;
    private ArrayList<Account> listOfAccounts;
    private JButton createAccountButton;
    private JButton viewItemsButton;
    private JTextArea accountIdFinderTextBox;
    private JButton findAccountButton;
    private JButton viewAllAccountsButton;
    private JPanel MainPanel;
    private JList list1;
    private JFrame frame;
    private DefaultListModel<AccountLine> listModel;
    private JScrollPane scrollPane;

    public MainWindow() {
        frame = new JFrame("MainWindow");
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        listOfAccounts = new ArrayList<>();
        scrollPane.setViewportView(list1);
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        list1.setLayoutOrientation(JList.VERTICAL);
        list1.setCellRenderer(new AccountLineListRenderer());

        int choice = JOptionPane.showConfirmDialog(null, "Do you want to use SOAP Service?",
                "Service choice", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            setIsSoapUsed(true);
        } else {
            setIsSoapUsed(false);
        }
        if (choice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        AbstractInteractor interaction;
        if (getIsSoapUsed()) {
            interaction = new InteractClassSoap();
        } else {
            interaction = new InteractClassRest();
        }

        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new CreateAccountWindow(getIsSoapUsed());
            }
        });

        viewItemsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ViewItemsWindow(getIsSoapUsed());
            }
        });

        findAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listModel.clear();

                GetAccountRequest request = new GetAccountRequest();
                if (accountIdFinderTextBox.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an account ID or name.");
                    return;
                }
                try {
                    Integer.parseInt(accountIdFinderTextBox.getText());
                    request.setAccountId(Integer.parseInt(accountIdFinderTextBox.getText()));
                    request.setUsername("");
                } catch (NumberFormatException ex) {
                    request.setAccountId(-1);
                    request.setUsername(accountIdFinderTextBox.getText());
                }
                GetAccountResponse response = interaction.getAccount(request);
                if (response.getAccount() == null) {
                    JOptionPane.showMessageDialog(null, "Account not found.");
                    return;
                }
                listOfAccounts.clear();
                listModel.addElement(new AccountLine(response.getAccount()));
                listOfAccounts.add(response.getAccount());
                update();
            }
        });
        viewAllAccountsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listModel.clear();
                GetAccountsResponse response = interaction.getAccounts();
                listOfAccounts.clear();
                for (int i = 0; i < response.getAccounts().size(); i++) {
                    listModel.addElement(new AccountLine(response.getAccounts().get(i)));
                    listOfAccounts.add(response.getAccounts().get(i));
                }
                update();
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
                numOfAccountsInList = listOfAccounts.size();
                new AccountWindow(accountLine.account, listOfAccounts,
                        MainWindow.this, getIsSoapUsed());
            }
        });
    }

    @Override
    public void update() {
        if (!frame.isVisible()){
            frame.setVisible(true);
        }
        if (listOfAccounts.size() != numOfAccountsInList) {
            listModel.clear();
            for (Account account : listOfAccounts) {
                listModel.addElement(new AccountLine(account));
            }
            numOfAccountsInList = listOfAccounts.size();
        }
        frame.pack();
    }
}
