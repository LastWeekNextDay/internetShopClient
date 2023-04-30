package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.JavaXml;
import lt.viko.eif.nlavkart.internetShopClient.Printer;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.Account;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveAccountRequest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClass;
import org.apache.fop.apps.FOPException;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
                JavaXml transformer = new JavaXml(Account.class);
                transformer.save(account, "src/main/resources/print.xml");
                String[] options = {"HTML", "PDF"};
                int choice = JOptionPane.showOptionDialog(null, "Choose format", "Print",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (choice == 0) {
                    try {
                        Printer.printHtml();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (choice == 1) {
                    try {
                        Printer.printPdf();
                        File file = new File("print.pdf");
                        if (file.exists()) {
                            Desktop.getDesktop().open(file);
                        }
                    } catch (IOException | FOPException | TransformerException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
