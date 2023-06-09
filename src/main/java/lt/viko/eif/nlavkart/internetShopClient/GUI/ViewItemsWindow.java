package lt.viko.eif.nlavkart.internetShopClient.GUI;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.ServiceUsageVar;
import lt.viko.eif.nlavkart.internetShopClient.GUI.Inheritances.UpdateFunc;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.ItemLine;
import lt.viko.eif.nlavkart.internetShopClient.GUI.MixedComponents.ItemLineListRenderer;
import lt.viko.eif.nlavkart.internetShopClient.REST.forClient.InteractClassRest;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient.InteractClassSoap;
import lt.viko.eif.nlavkart.internetShopClient.generated.*;

import javax.swing.*;
import java.util.ArrayList;

public class ViewItemsWindow extends ServiceUsageVar implements UpdateFunc {
    private ArrayList<Item> items;
    private int itemsSize;
    private JFrame frame;
    private JPanel panel;
    private JList list1;
    private JScrollPane scrollPane;
    private JComboBox comboBox1;

    private ArrayList<Category> categories;

    private DefaultListModel<ItemLine> listModel;

    private DefaultComboBoxModel<String> comboBoxModel;

    public ViewItemsWindow(boolean useSoap) {
        frame = new JFrame("ViewItemsWindow");
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(350, 365);

        setIsSoapUsed(useSoap);

        items = new ArrayList<>();

        categories = new ArrayList<>();

        scrollPane.setViewportView(list1);
        listModel = new DefaultListModel<>();
        list1.setModel(listModel);
        list1.setLayoutOrientation(JList.VERTICAL);
        list1.setCellRenderer(new ItemLineListRenderer());

        comboBoxModel = new DefaultComboBoxModel<>();
        comboBox1.setModel(comboBoxModel);
        AbstractInteractor interactClass;
        if (getIsSoapUsed()){
            interactClass = new InteractClassSoap();
        } else {
            interactClass = new InteractClassRest();
        }
        GetCategoriesRequest getCategoriesRequest = new GetCategoriesRequest();
        GetCategoriesResponse getCategoriesResponse = interactClass.getCategories(getCategoriesRequest);
        comboBoxModel.addElement("All");
        for (Category category : getCategoriesResponse.getCategories()) {
            categories.add(category);
            comboBoxModel.addElement(category.getName());
        }
        GetItemsRequest initialRequest = new GetItemsRequest();
        initialRequest.setCategoryId(-1);
        GetItemsResponse initialResp = interactClass.getItems(initialRequest);
        for (Item item : initialResp.getItems()) {
            listModel.addElement(new ItemLine(item));
            items.add(item);
            itemsSize++;
        }
        update();
        comboBox1.addActionListener(e -> {
            GetItemsRequest getItemsRequest = new GetItemsRequest();
            if (comboBox1.getSelectedIndex() != 0) {
                getItemsRequest.setCategoryId(categories.get(comboBox1.getSelectedIndex() - 1).getId());
            } else {
                getItemsRequest.setCategoryId(-1);
            }
            GetItemsResponse getItemsResponse = interactClass.getItems(getItemsRequest);
            listModel.clear();
            items.clear();
            for (Item item : getItemsResponse.getItems()) {
                listModel.addElement(new ItemLine(item));
                items.add(item);
                itemsSize++;
            }
        });

        list1.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                if (list1.getSelectedValue() == null) {
                    return;
                }
                ItemLine itemLine = (ItemLine) list1.getSelectedValue();
                new ItemWindowItems(items, itemLine.item, ViewItemsWindow.this, getIsSoapUsed());
            }
        });
        frame.pack();
    }

    @Override
    public void update() {
        if (items.size() != itemsSize) {
            listModel.clear();
            for (Item item : items) {
                listModel.addElement(new ItemLine(item));
            }
            itemsSize = items.size();
        }
        frame.pack();
    }
}
