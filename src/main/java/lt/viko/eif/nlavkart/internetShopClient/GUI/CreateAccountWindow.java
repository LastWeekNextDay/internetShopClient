package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.*;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClass;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAccountWindow {
    private JPanel panel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPasswordField passField;
    private JPasswordField repeatPassField;
    private JLabel passwordStatus;
    private JLabel repeatLabel;
    private JButton createAccountButton;

    private JFrame frame;

    public CreateAccountWindow() {
        frame = new JFrame("CreateAccountWindow");
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 400);

        createAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (usernameField.getText().equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter username");
                    return;
                } else if (passField.getPassword().length == 0){
                    JOptionPane.showMessageDialog(frame, "Please enter password");
                    return;
                } else if (repeatPassField.getPassword().length == 0){
                    JOptionPane.showMessageDialog(frame, "Please repeat password");
                    return;
                } else if (!String.valueOf(passField.getPassword()).equals(String.valueOf(repeatPassField.getPassword()))){
                    JOptionPane.showMessageDialog(frame, "Passwords do not match");
                    return;
                }
                InteractClass interactClass = new InteractClass();
                interactClass.init();
                CreateAccountRequest request = new CreateAccountRequest();
                request.setUsername(usernameField.getText());
                request.setPassword(String.valueOf(passField.getPassword()));
                CreateAccountResponse response1 = interactClass.createAccount(request);
                if (response1.isAck()){
                    JOptionPane.showMessageDialog(frame, "Account created");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not created: " + response1.getMessage());
                }
            }
        });

        passField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPassword();
            }
        });

        repeatPassField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkPassword();
            }
        });
    }

    private void checkPassword(){
        if (repeatPassField.getPassword().length > 0) {
            if (passField.getPassword().length > 0) {
                if (String.valueOf(passField.getPassword()).equals(String.valueOf(repeatPassField.getPassword()))) {
                    passwordStatus.setText("Passwords match");
                } else {
                    passwordStatus.setText("Passwords do not match");
                }
            } else {
                passwordStatus.setText("");
            }
        } else {
            passwordStatus.setText("");
        }
    }
}
