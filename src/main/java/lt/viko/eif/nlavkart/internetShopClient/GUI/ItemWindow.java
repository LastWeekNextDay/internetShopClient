package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.*;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClass;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemWindow {
    private Account account;
    private Item item;
    private JPanel itemWindowPane;
    private JLabel nameLabel;
    private JButton deleteButton;

    public ItemWindow(Account account, Item item) {
        JFrame frame = new JFrame("ItemWindow");
        frame.setContentPane(itemWindowPane);
        frame.setSize(150, 200);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        this.account = account;
        this.item = item;
        nameLabel.setText(item.getName());
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                InteractClass interactClass = new InteractClass();
                interactClass.init();
                RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest();
                removeItemFromCartRequest.setAccountId(account.getId());
                removeItemFromCartRequest.setItemId(item.getId());
                RemoveItemFromCartResponse response = interactClass.removeItemFromCart(removeItemFromCartRequest);
                if (response.isAck()){
                    account.getCart().getItems().remove(item);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Error: " + response.getMessage());
                }
            }
        });
    }
}
