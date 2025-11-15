import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Main extends JFrame {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception _) {}
        new Main();
    }
    public Main() {
        super("Belarmino's Main Window");
        setLayout(new GridBagLayout());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("Size: %d, %d\n", getSize().width, getSize().height);
            }
        });

        JLabel image = null;
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("icon.jpg")));
            setIconImage(icon.getImage());
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            image = new JLabel(scaledIcon);
        } catch (Exception e) {}

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridBagLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JButton char_rm_btn =  new JButton("Character Remover");
        JButton food_menu_btn = new JButton("Food Ordering System");
        JButton pwd_validator_btn = new JButton("Password Validator");
        JButton char_counter_btn = new JButton("Character Counter");
        JButton num_counter_btn = new JButton("Number Counter");
        JButton pizza_order_btn = new JButton("Pizza Ordering System");

        JPanel my_gui_panel = new JPanel();
        my_gui_panel.setLayout(new GridBagLayout());
        my_gui_panel.setBorder(BorderFactory.createTitledBorder("Assigned GUI"));

        JPanel other_gui = new JPanel();
        other_gui.setLayout(new GridBagLayout());
        other_gui.setBorder(BorderFactory.createTitledBorder("Other GUI"));

        char_rm_btn.addActionListener(e -> {new CharRMGUI();});
        food_menu_btn.addActionListener(e -> {new FoodMenuGUI();});
        pwd_validator_btn.addActionListener(e -> {new PasswordValidatorGUI();});
        char_counter_btn.addActionListener(e -> new CharCounter());
        num_counter_btn.addActionListener(e -> new NumCounter());
        pizza_order_btn.addActionListener(e -> new PizzaOrder());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        my_gui_panel.add(char_rm_btn,gbc);
        other_gui.add(char_counter_btn,gbc);
        gbc.gridy = 1;
        my_gui_panel.add(food_menu_btn,gbc);
        other_gui.add(num_counter_btn,gbc);
        gbc.gridy = 2;
        my_gui_panel.add(pwd_validator_btn,gbc);
        other_gui.add(pizza_order_btn,gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        main_panel.add(my_gui_panel,gbc);
        gbc.gridy = 1;
        main_panel.add(other_gui,gbc);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(main_panel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        if (image != null) add(image, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(477, 319);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
