import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CharRMGUI extends JFrame  {
    public static final String gui_name = "Character Remover";
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

        str_label = new JLabel(str);
        
        vowelsBox = new JCheckBox("Vowels");
        consonantsBox = new JCheckBox("Consonants");
        numbersBox = new JCheckBox("Numbers");
        
        JButton remove_btn = new JButton("Remove");
        JButton restore_btn = new JButton("Restore");
        
        str_label.setName("textLabel");
        vowelsBox.setName("vowelCheckbox");
        consonantsBox.setName("consonantCheckbox");
        numbersBox.setName("numberCheckbox");
        remove_btn.setName("removeButton");
        restore_btn.setName("restoreButton");

        remove_btn.addActionListener(this::removeClick);
        restore_btn.addActionListener(this::restoreClick);

        JPanel checkbox_panel = new JPanel();
        checkbox_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        checkbox_panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        checkbox_panel.add(vowelsBox);
        checkbox_panel.add(consonantsBox);
        checkbox_panel.add(numbersBox);

        JPanel btn_panel = new JPanel();
        btn_panel.setLayout(new GridLayout(1, 2, 10, 10));
        btn_panel.add(remove_btn);
        btn_panel.add(restore_btn);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        main_panel.add(str_label, gbc);
        gbc.gridy = 1;
        main_panel.add(checkbox_panel, gbc);
        gbc.gridy = 2;
        main_panel.add(btn_panel, gbc);

        add(main_panel);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize( 300,140);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                if (getFrames().length == 0) {System.exit(0); }
            }
        });
    }

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