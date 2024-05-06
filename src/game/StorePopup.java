// StorePopup.java
package game;

import javax.swing.*;

public class StorePopup extends JFrame {
    private Store store;
    private Person player;

    public StorePopup(Store store, Person player) {
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
        // Implement the logic for buying items here
        // You can get the selected checkboxes, their corresponding items, and the quantities entered
        // Then calculate the total price and deduct it from the player's money
        // Finally, update the inventory and display a message confirming the purchase
        // For now, let's just display a message
        JOptionPane.showMessageDialog(this, "Items purchased successfully!");
    }
}
    
