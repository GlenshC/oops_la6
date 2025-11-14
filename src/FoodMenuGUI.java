import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class FoodMenuGUI extends JFrame {
    private static final List<FoodItem> food_list = List.of(
            new FoodItem("Pizza", 100),
            new FoodItem("Burger", 80),
            new FoodItem("Fries", 65),
            new FoodItem("SoftDrinks", 55),
            new FoodItem("Tea", 50),
            new FoodItem("Sundae", 40)
    );
    private float discount;

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception _) {}
        new FoodMenuGUI();
    }

    public FoodMenuGUI() {
        super("Food Ordering System");
        this.discount = 0;

        JPanel root_panel = new JPanel(new GridBagLayout());
        root_panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridBagLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel menu_margin_panel = new JPanel();
        menu_margin_panel.setLayout(new GridLayout(1, 1, 10, 10));
        menu_margin_panel.setBorder(BorderFactory.createTitledBorder("Foods"));

        JPanel menu_panel = new JPanel();
        menu_panel.setLayout(new GridLayout(food_list.size(), 1, 10, 10));
        menu_panel.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));

        JPanel discount_panel = new JPanel();
        discount_panel.setLayout(new GridLayout(1, 1, 0, 0));
        discount_panel.setBorder(BorderFactory.createTitledBorder("Discounts"));

        JPanel discount_margin_panel = new JPanel();
        discount_margin_panel.setLayout(new GridLayout(food_list.size(), 1, 10, 10));
        discount_margin_panel.setBorder(BorderFactory.createEmptyBorder(0, 12, 12, 12));

        for (FoodItem item : food_list) {
            menu_panel.add(item);
        }

        JButton order_btn = new JButton("Order");
        order_btn.addActionListener(this::order_click);

        JRadioButton d_0 = new JRadioButton("None");
        d_0.addActionListener(e -> discount = 0);
        JRadioButton d_5 = new JRadioButton("5% Off");
        d_5.addActionListener(e -> discount = 0.05f);
        JRadioButton d_10 = new JRadioButton("10% Off");
        d_10.addActionListener(e -> discount = 0.1f);
        JRadioButton d_15 = new JRadioButton("15% Off");
        d_15.addActionListener(e -> discount = 0.15f);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(d_0);
        buttonGroup.add(d_5);
        buttonGroup.add(d_10);
        buttonGroup.add(d_15);

        d_0.setSelected(true);

        discount_margin_panel.add(d_0);
        discount_margin_panel.add(d_5);
        discount_margin_panel.add(d_10);
        discount_margin_panel.add(d_15);
        discount_panel.add(discount_margin_panel);

        menu_margin_panel.add(menu_panel);

        // major panels
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        main_panel.add(menu_margin_panel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        main_panel.add(discount_panel, gbc);

        // main and order btn
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        root_panel.add(main_panel, gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 1;
        gbc.weighty = 0;
        root_panel.add(order_btn, gbc);

        add(root_panel);

        setPreferredSize(new Dimension(370,330));
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                if (getFrames().length == 0) {System.exit(0); }
            }
        });
    }

    private void order_click(ActionEvent e) {
        double total = 0;
        for (FoodItem item : food_list) {
            if (item.checked()) total +=  item.getPrice();
        }
        total = total * (1 - discount);
        JOptionPane.showMessageDialog(null,
                String.format("The total price is Php %.2f",  total),
                "Bill",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static class FoodItem extends JPanel {
        private double price;
        private final JCheckBox checkbox_btn;

        public boolean checked()
        {
            return checkbox_btn.isSelected();
        }
        public double getPrice() {
            return price;
        }

        public FoodItem(String name, double price)
        {
            super();
            this.price = price;
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            // checkbox + (label + label)
            checkbox_btn = new JCheckBox();

            Border border_focused = BorderFactory.createLineBorder(Color.GRAY);
            Border border_unfocused = BorderFactory.createEmptyBorder(1, 1, 1, 1);

            JPanel main_panel = new JPanel();
            main_panel.setLayout(new GridBagLayout());
            main_panel.setBorder(border_unfocused);

            JLabel name_label = new JLabel(name);
            JLabel price_label = new JLabel(String.format("Php %.0f", price));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            main_panel.add(name_label, gbc);
            gbc.gridx = 1;
            gbc.weightx = 0;
            main_panel.add(price_label, gbc);

            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 1;
            add(checkbox_btn, gbc);
            gbc.gridx = 1;
            gbc.weightx = 1;
            add(main_panel, gbc);

            // BEHOLD OVER_ENGINEERING !!!!!!
            addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) { checkbox_btn.setSelected(!checkbox_btn.isSelected()); }
                @Override public void mouseEntered(MouseEvent e) {
                    main_panel.setBorder(border_focused);
                }
                @Override public void mouseExited(MouseEvent e) {
                    main_panel.setBorder(border_unfocused);
                }
            });

            checkbox_btn.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    main_panel.setBorder(border_focused);
                }
                @Override public void mouseExited(MouseEvent e) {
                    main_panel.setBorder(border_unfocused);
                }
            });

            checkbox_btn.addFocusListener(new FocusAdapter() {
                @Override public void focusLost(FocusEvent e) {
                    main_panel.setBorder(border_unfocused);
                }
                @Override public void focusGained(FocusEvent e) {
                    main_panel.setBorder(border_focused);
                }
            });
        }
    }
}
