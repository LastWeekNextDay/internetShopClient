package lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents;

public class ItemLineListRenderer implements javax.swing.ListCellRenderer<ItemLine> {
    @Override
    public java.awt.Component getListCellRendererComponent(javax.swing.JList<? extends ItemLine> list, ItemLine value, int index, boolean isSelected, boolean cellHasFocus) {
        return value.itemPane;
    }
}

