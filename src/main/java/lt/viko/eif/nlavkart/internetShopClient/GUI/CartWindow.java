package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.UpdateFunc;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.ItemLine;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.ItemLineListRenderer;
import lt.viko.eif.nlavkart.internetShopClient.generated.Account;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class CartWindow extends ServiceUsageVar implements UpdateFunc {
    private Account account;

    private int numOfItems;

    private JFrame frame;
    private JPanel cartWindowPane;
    private JLabel cartIdLabel;
    private JList list1;
    private JScrollPane scrollPane;

    private DefaultListModel<ItemLine> listModel;

    public CartWindow(Account account, boolean useSoap) {
        this.account = account;
        frame = new JFrame("CartWindow");
        frame.setContentPane(cartWindowPane);
        cartIdLabel.setText("Cart ID: " + account.getCart().getId());
        frame.setMinimumSize(new Dimension(150, 200));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        setIsSoapUsed(useSoap);
        scrollPane.setViewportView(list1);
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        list1.setLayoutOrientation(JList.VERTICAL);
        list1.setCellRenderer(new ItemLineListRenderer());

        numOfItems = account.getCart().getItems().size();

        for (int i = 0; i < account.getCart().getItems().size(); i++) {
            listModel.addElement(new ItemLine(account.getCart().getItems().get(i)));
        }
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (e.getValueIsAdjusting()) {
                        return;
                    }
                    if (list1.getSelectedValue() == null) {
                        return;
                    }
                    ItemLine itemLine = (ItemLine) list1.getSelectedValue();
                    new ItemWindowCart(account, itemLine.item, CartWindow.this, getIsSoapUsed());
                    if (!account.getCart().getItems().contains(itemLine.item)) {
                        listModel.removeElement(itemLine);
                    }

                }
            }
        });
    }

    @Override
    public void update() {
        if (!frame.isVisible()){
            frame.setVisible(true);
        }
        if (numOfItems != account.getCart().getItems().size()){
            listModel.clear();
            for (int i = 0; i < account.getCart().getItems().size(); i++) {
                listModel.addElement(new ItemLine(account.getCart().getItems().get(i)));
            }
            numOfItems = account.getCart().getItems().size();
        }
    }
}
