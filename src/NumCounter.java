import javax.swing.*;
import java.awt.*;

public class NumCounter extends JFrame {
    public static final String gui_name = "Number Counter";
    private int value;

    public NumCounter () {
        super("Number Counter");
        value = 0;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel num = new JLabel("0");

        JPanel btn_panel = new JPanel();
        btn_panel.setLayout(new GridBagLayout());

        JButton dec_btn = new JButton("Decrease");
        JButton inc_btn =  new JButton("Increase");

        dec_btn.addActionListener(e -> {
            num.setText(String.format("%d", --value));
        });
        inc_btn.addActionListener(e -> {
            num.setText(String.format("%d", ++value));
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        btn_panel.add(dec_btn,gbc);
        add(num,gbc);
        gbc.gridx = 1;
        btn_panel.add(inc_btn,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btn_panel,gbc);


        setSize(235, 126);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    public static void main(String[] args) {
        new NumCounter();
    }

}
