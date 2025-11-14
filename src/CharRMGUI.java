import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CharRMGUI extends JFrame {

    private final JCheckBox vowelsBox;
    private final JCheckBox consonantsBox;
    private final JCheckBox numbersBox;
    private final JLabel str_label;
    private String str;

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception _) {}
        new CharRMGUI();
    }

    public CharRMGUI() {
        super("Character Remover");
        str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        setSize(400, 250);
        setLocationRelativeTo(null); // Center window

        Border main_border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(3, 1, 10, 10)); // rows, cols, hgap, vgap
        main_panel.setBorder(main_border);

        JPanel btn_panel = new JPanel();
        btn_panel.setLayout(new GridLayout(1, 2, 10, 10)); // rows, cols, hgap, vgap
        btn_panel.setBorder(main_border);

        JPanel box_panel = new JPanel();
        box_panel.setLayout(new GridLayout(1, 3, 10, 10)); // rows, cols, hgap, vgap
        box_panel.setBorder(main_border);

        JPanel label_panel = new JPanel();
        label_panel.setLayout(new GridLayout(1, 1, 10, 10)); // rows, cols, hgap, vgap
        label_panel.setBorder(main_border);

        str_label = new JLabel();
        str_label.setHorizontalAlignment(JLabel.CENTER);
        str_label.setVerticalAlignment(JLabel.CENTER);
        str_label.setText(str);

        label_panel.add(str_label);

        JButton removeBtn = new JButton("Remove");
        JButton restoreBtn = new JButton("Restore");
        removeBtn.addActionListener(this::removeClick);
        restoreBtn.addActionListener(this::restoreClick);

        btn_panel.add(removeBtn);
        btn_panel.add(restoreBtn);

        vowelsBox = new JCheckBox("Vowels");
        consonantsBox = new JCheckBox("Consonants");
        numbersBox = new JCheckBox("Numbers");

        box_panel.add(vowelsBox);
        box_panel.add(consonantsBox);
        box_panel.add(numbersBox);

        main_panel.add(label_panel);
        main_panel.add(box_panel);
        main_panel.add(btn_panel);

        add(main_panel);
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

    // --- Event handlers ---
    private void removeClick(ActionEvent e)
    {
        String targ = "[";
        if (vowelsBox.isSelected()) targ += "AEIOU";
        if (consonantsBox.isSelected()) targ += "BCDFGHJKLMNPQRSTVWXYZ";
        if (numbersBox.isSelected()) targ += "1234567890";
        targ += "]";

        String res = "";
        try {
            res = str.replaceAll(targ, "");
            str = res;
        } catch (java.util.regex.PatternSyntaxException err) {
            System.err.println("No selected pattern to remove.");
        }
        str_label.setText(str);
    }

    private void restoreClick(ActionEvent e)
    {
        str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        str_label.setText(str);
    }
}