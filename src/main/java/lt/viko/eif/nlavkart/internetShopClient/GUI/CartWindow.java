package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.AccountLine;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.AccountLineListRenderer;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.ItemLine;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.ItemLineListRenderer;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.Account;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class CartWindow {
    private Account account;
    private JPanel cartWindowPane;
    private JLabel cartIdLabel;
    private JList list1;
    private JScrollPane scrollPane;

    public CartWindow(Account account) {
        this.account = account;
        JFrame frame = new JFrame("CartWindow");
        frame.setContentPane(cartWindowPane);
        cartIdLabel.setText("Cart ID: " + account.getCart().getId());
        frame.setMinimumSize(new Dimension(150, 200));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        scrollPane.setViewportView(list1);
        DefaultListModel<ItemLine> listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        list1.setLayoutOrientation(JList.VERTICAL);
        list1.setCellRenderer(new ItemLineListRenderer());

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
                    new ItemWindow(account, itemLine.item);
                }
            }
        });
    }
}
