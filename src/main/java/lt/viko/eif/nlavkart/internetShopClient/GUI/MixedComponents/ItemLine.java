package lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents;

import lt.viko.eif.nlavkart.internetShopClient.generated.Item;

import javax.swing.*;

public class ItemLine {

    public Item item;

    private JLabel nameLabel;
    public JPanel itemPane;

    public ItemLine(Item item) {
        this.item = item;
        nameLabel.setText(item.getName());
    }
}
