package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.Item;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveItemRequest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveItemResponse;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClass;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ItemWindowItems {
    private JPanel panel;
    private JLabel nameLabel;
    private JButton addToCartButton;
    private JButton deleteButton;

    private JFrame frame;

    public ItemWindowItems(ArrayList<Item> items, Item item, AbstractWindow previousWindow) {
        frame = new JFrame("ItemWindowItems");
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        nameLabel.setText(item.getName());
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                InteractClass interactClass = new InteractClass();
                interactClass.init();
                RemoveItemRequest removeItemRequest = new RemoveItemRequest();
                removeItemRequest.setItemId(item.getId());
                RemoveItemResponse response = interactClass.deleteItem(removeItemRequest);
                if (response.isAck()){
                    items.remove(item);
                    previousWindow.update();
                    JOptionPane.showMessageDialog(null, "Item deleted");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Item not deleted: " + response.getMessage());
                }
            }
        });
        addToCartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new AddItemToCartWindow(item);
            }
        });
    }
}
