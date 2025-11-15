import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class PizzaOrder extends JFrame {
    private final String[] sizes = { "Small", "Medium", "Large" };
    private final String[] toppings = {"Mushrooms", "Pepperoni", "Onions"};

    public PizzaOrder() {
        super("Pizza Ordering System");

        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JComboBox<String> sizeChoice = new JComboBox<String>(sizes);
        JComboBox<String> toppingsChoice = new JComboBox<String>(toppings);
        JComboBox<String> extraCheeseChoice = new JComboBox<String>(new String[] {"Yes", "No"});

        JLabel sizeLabel = new JLabel("Size:");
        JLabel toppingLabel = new JLabel("Topping:");
        JLabel extraCheeseLabel = new JLabel("Extra Cheese:");

        JLabel totalLabel = new JLabel("0");
        totalLabel.setHorizontalAlignment(JLabel.CENTER);

        JButton calculateButton = new JButton("Calculate Total");
        calculateButton.addActionListener(e -> {
            int total = 2;
            switch (sizeChoice.getSelectedItem().toString())
            {
                case "Small" -> total += 10;
                case "Medium" -> total += 15;
                case "Large" -> total += 20;
            }
            switch(extraCheeseChoice.getSelectedItem().toString())
            {
                case "Yes" -> total += 3;
            }
            totalLabel.setText(String.valueOf(total));
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        add(sizeLabel, gbc);
        gbc.gridy++;
        add(sizeChoice, gbc);
        gbc.gridy++;
        add(toppingLabel, gbc);
        gbc.gridy++;
        add(toppingsChoice, gbc);
        gbc.gridy++;
        add(extraCheeseLabel, gbc);
        gbc.gridy++;
        add(extraCheeseChoice, gbc);
        gbc.gridy+=2;
        add(totalLabel, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy--;
        add(calculateButton, gbc);

        setSize(235, 273);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception _) {}

        new PizzaOrder();
    }
}
