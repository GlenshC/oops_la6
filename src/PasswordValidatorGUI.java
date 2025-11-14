import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class PasswordValidatorGUI extends JFrame {
    private final JTextField password;
    private final JLabel status;

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception _) {}
        new PasswordValidatorGUI();
    }

    public PasswordValidatorGUI()
    {
        super("Password Validator");
        password = new JTextField();
        password.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override public void insertUpdate(DocumentEvent e) {onChangePassword();}
                    @Override public void removeUpdate(DocumentEvent e) {onChangePassword();}
                    public void changedUpdate(DocumentEvent e) {}
                }
        );

        status = new JLabel();
        status.setText("Passwords must be 8 characters long");
        status.setForeground(Color.RED);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        panel.add(password, gbc);
        gbc.gridy = 1;
        panel.add(status, gbc);

        add(panel);

        setPreferredSize(new Dimension(400, 100));
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
    public void onChangePassword()
    {
        String pwd = password.getText();
        status.setForeground(Color.RED);

        if (pwd.length() < 8) status.setText("Passwords must be 8 characters long");
        else if (!hasDigit(pwd)) status.setText("Password must contain at least one digit");
        else if (!hasSpecial(pwd)) status.setText("Password must contain at least one special character");
        else
        {
            status.setText("Password is valid");
            status.setForeground(Color.GREEN);
        }
    }

    private boolean hasSpecial(String pwd)
    {
        for (char c : pwd.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDigit(String pwd)
    {
        for (char c : pwd.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
