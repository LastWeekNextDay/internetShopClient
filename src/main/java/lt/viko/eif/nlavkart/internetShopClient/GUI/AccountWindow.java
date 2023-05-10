package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.UpdateFunc;
import lt.viko.eif.nlavkart.internetShopClient.JavaXml;
import lt.viko.eif.nlavkart.internetShopClient.Printer;
import lt.viko.eif.nlavkart.internetShopClient.REST.forClient.InteractClassRest;
import lt.viko.eif.nlavkart.internetShopClient.generated.Account;
import lt.viko.eif.nlavkart.internetShopClient.generated.RemoveAccountRequest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClassSoap;
import org.apache.fop.apps.FOPException;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccountWindow extends ServiceUsageVar {

    private JPanel windowPane;
    private JButton cartButton;
    private JButton deleteButton;
    private JButton printButton;
    private JLabel nameLabel;

    UpdateFunc previousWindow;

    public AccountWindow(Account account, ArrayList<Account> listOfAccounts, UpdateFunc previousWindow,
                         boolean useSoap) {
        this.previousWindow = previousWindow;
        setIsSoapUsed(useSoap);
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
                AbstractInteractor interactClass;
                if (getIsSoapUsed()){
                    interactClass = new InteractClassSoap();
                } else {
                    interactClass = new InteractClassRest();
                }
                RemoveAccountRequest request = new RemoveAccountRequest();
                request.setAccountId(account.getId());
                request.setUsername(account.getUsername());
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
                new CartWindow(account, getIsSoapUsed());
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
