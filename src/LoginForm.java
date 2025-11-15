import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    public static final String gui_name = "Login Form";
    JTextField username;
    JPasswordField password;
    public LoginForm() {
        super("Login Form");

        JLabel user_label = new JLabel("Username:");
        JLabel password_label = new JLabel("Password:");

        username = new JTextField();
        password = new JPasswordField();

        JButton login_button = new JButton("Login");
        login_button.addActionListener(e->{login();});

        KeyAdapter key_adapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { login(); }
            }
        };
        username.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { username.transferFocus(); }
            }
        });
        password.addKeyListener(key_adapter);
        login_button.addKeyListener(key_adapter);
        addKeyListener(key_adapter);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(user_label, gbc);
        gbc.gridy++;
        add(username, gbc);
        gbc.gridy++;
        add(password_label,gbc);
        gbc.gridy++;
        add(password,gbc);
        gbc.gridy++;
        gbc.weightx = 0;
        gbc.fill  = GridBagConstraints.NONE;
        add(login_button, gbc);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login()
    {
        String pwd = new String(password.getPassword());
        String user = username.getText();
        String stat = "Failed";
        if (pwd.equals("admin") && user.equals("admin")) { stat = "Successful"; }
        JOptionPane.showMessageDialog(null, String.format("Login %s!", stat), "Status", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        new LoginForm();
    }

}
