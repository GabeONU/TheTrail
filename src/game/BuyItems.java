package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyItems extends JFrame {
    private Store store;
    private Person player;

    public BuyItems(Store store, Person player) {
        this.store = store;
        this.player = player;
        initialize();
    }

    private void initialize() {
        setTitle("Store");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Hi, welcome to the store!");
        welcomeLabel.setBounds(220, 30, 200, 20);
        panel.add(welcomeLabel);

        int y = 60;
        for (Item item : store.getItems()) {
            JCheckBox checkBox = new JCheckBox(item.getName() + " - Price: $" + item.getPrice() + ", Weight: " + item.getWeight() + " lbs");
            checkBox.setBounds(50, y, 400, 20);
            panel.add(checkBox);

            JTextField quantityField = new JTextField("0");
            quantityField.setBounds(470, y, 50, 20);
            panel.add(quantityField);

            y += 30;
        }

        JButton buyButton = new JButton("Buy");
        buyButton.setBounds(250, 300, 100, 30);
        buyButton.addActionListener(e -> buyItems(panel));
        panel.add(buyButton);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(370, 300, 100, 30);
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton);
    }

    private void buyItems(JPanel panel) {
        double totalPrice = 0.0;

        // Iterate over all components in the panel
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    // Extract item name, price, and weight from the checkbox text
                    String[] parts = checkBox.getText().split(" - ");
                    String itemName = parts[0];
                    double pricePerPound = Double.parseDouble(parts[1].substring(8).replace("$", ""));
                    int weight = Integer.parseInt(parts[2].substring(9, parts[2].indexOf(" lbs")));

                    // Placeholder for quantity input - for now, let's assume the player wants to buy 1 pound of each selected item
                    int quantity = Integer.parseInt(((JTextField) components[1]).getText());

                    // Calculate total price
                    double itemTotalPrice = pricePerPound * quantity;
                    totalPrice += itemTotalPrice;

                    // Placeholder for deducting money from player - you can add actual logic here
                    // player.deductMoney(itemTotalPrice);
                }
            }
        }

        // Placeholder for displaying the total price to the user
        JOptionPane.showMessageDialog(this, "Total price: $" + totalPrice);
    }
}
