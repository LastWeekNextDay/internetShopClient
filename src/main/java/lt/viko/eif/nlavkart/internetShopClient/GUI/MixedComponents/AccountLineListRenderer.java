package lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents;

public class AccountLineListRenderer implements javax.swing.ListCellRenderer<AccountLine> {
    @Override
    public java.awt.Component getListCellRendererComponent(javax.swing.JList<? extends AccountLine> list, AccountLine value, int index, boolean isSelected, boolean cellHasFocus) {
        return value.AccountLinePanel;
    }
}
