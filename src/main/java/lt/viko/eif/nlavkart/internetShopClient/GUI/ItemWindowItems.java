package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.UpdateFunc;
import lt.viko.eif.nlavkart.internetShopClient.REST.forClient.InteractClassRest;
import lt.viko.eif.nlavkart.internetShopClient.generated.Item;
import lt.viko.eif.nlavkart.internetShopClient.generated.RemoveItemRequest;
import lt.viko.eif.nlavkart.internetShopClient.generated.RemoveItemResponse;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClassSoap;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ItemWindowItems extends ServiceUsageVar {
    private JPanel panel;
    private JLabel nameLabel;
    private JButton addToCartButton;
    private JButton deleteButton;

    private JFrame frame;

    public ItemWindowItems(ArrayList<Item> items, Item item, UpdateFunc previousWindow, boolean useSoap) {
        frame = new JFrame("ItemWindowItems");
        frame.setContentPane(panel);
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
                new AddItemToCartWindow(item, getIsSoapUsed());
            }
        });
    }
}
