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

        JLabel image = null;
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("icon.jpg")));
            setIconImage(icon.getImage());
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            image = new JLabel(scaledIcon);
        } catch (Exception e) {}

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridBagLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JButton char_rm_btn =  new JButton("Character Remover");
        JButton food_menu_btn = new JButton("Food Ordering System");
        JButton pwd_validator_btn = new JButton("Password Validator");

        char_rm_btn.addActionListener(e -> {new CharRMGUI();});
        food_menu_btn.addActionListener(e -> {new FoodMenuGUI();});
        pwd_validator_btn.addActionListener(e -> {new PasswordValidatorGUI();});

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        main_panel.add(char_rm_btn,gbc);
        gbc.gridy = 1;
        main_panel.add(food_menu_btn,gbc);
        gbc.gridy = 2;
        main_panel.add(pwd_validator_btn,gbc);

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
        setSize(320, 175);
        setLocationRelativeTo(null);
        setVisible(true);

       addMouseListener(new  MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               System.out.printf("Size: %d, %d", getSize().width, getSize().height);
           }
       });
    }
}
