package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.REST.forClient.InteractClassRest;
import lt.viko.eif.nlavkart.internetShopClient.generated.AddItemToCartRequest;
import lt.viko.eif.nlavkart.internetShopClient.generated.AddItemToCartResponse;
import lt.viko.eif.nlavkart.internetShopClient.generated.Item;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClassSoap;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddItemToCartWindow extends ServiceUsageVar {
    private JFrame frame;
    private JPanel panel;
    private JLabel itemName;
    private JTextField inputField;
    private JButton addToCartButton;
    private JLabel inputLabel;

    public AddItemToCartWindow(Item item, boolean useSoap) {
        frame = new JFrame("AddItemToCartWindow");
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        frame.setSize(300, 400);

        itemName.setText(item.getName());

        setIsSoapUsed(useSoap);

        addToCartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (inputField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Input field is empty");
                } else {
                    AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest();
                    addItemToCartRequest.setItemId(item.getId());
                    try {
                        int integer = Integer.parseInt(inputField.getText());
                        addItemToCartRequest.setAccountId(integer);
                        addItemToCartRequest.setUsername("");
                    } catch (NumberFormatException ignored){
                        addItemToCartRequest.setAccountId(-1);
                        addItemToCartRequest.setUsername(inputField.getText());
                    }
                    AbstractInteractor interactClass;
                    if (getIsSoapUsed()){
                        interactClass = new InteractClassSoap();
                    } else {
                        interactClass = new InteractClassRest();
                    }
                    AddItemToCartResponse response = interactClass.addItemToCart(addItemToCartRequest);
                    if (response.isAck()){
                        JOptionPane.showMessageDialog(null, "Item added to cart");
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Item not added to cart: " + response.getMessage());
                    }
                }
            }
        });
    }
}
