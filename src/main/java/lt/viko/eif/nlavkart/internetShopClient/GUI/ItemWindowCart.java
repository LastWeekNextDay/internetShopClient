package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.UpdateFunc;
import lt.viko.eif.nlavkart.internetShopClient.REST.forClient.InteractClassRest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClassSoap;
import lt.viko.eif.nlavkart.internetShopClient.generated.Account;
import lt.viko.eif.nlavkart.internetShopClient.generated.Item;
import lt.viko.eif.nlavkart.internetShopClient.generated.RemoveItemFromCartRequest;
import lt.viko.eif.nlavkart.internetShopClient.generated.RemoveItemFromCartResponse;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemWindowCart extends ServiceUsageVar {
    private JPanel itemWindowPane;
    private JLabel nameLabel;
    private JButton deleteButton;

    public ItemWindowCart(Account account, Item item, UpdateFunc previousWindow, boolean useSoap) {
        JFrame frame = new JFrame("ItemWindowCart");
        frame.setContentPane(itemWindowPane);
        frame.setSize(150, 200);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        nameLabel.setText(item.getName());
        setIsSoapUsed(useSoap);
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
                RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest();
                removeItemFromCartRequest.setAccountId(account.getId());
                removeItemFromCartRequest.setUsername(account.getUsername());
                removeItemFromCartRequest.setItemId(item.getId());
                RemoveItemFromCartResponse response = interactClass.removeItemFromCart(removeItemFromCartRequest);
                if (response.isAck()){
                    account.getCart().getItems().remove(item);
                    previousWindow.update();
                    JOptionPane.showMessageDialog(null, "Item removed from cart");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: " + response.getMessage());
                }
            }
        });
    }
}
