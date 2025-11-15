import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CharCounter extends JFrame {
    JTextArea   text_area;
    JTextField  char_field;
    JLabel count;

    void countChar(ActionEvent e)
    {
        char a = 0;
        try{
            a = char_field.getText().charAt(0);
        } catch (Exception _) {}
        if (a == 0) return;
        int total = 0;
        String text = text_area.getText();
        for (int i=0; i<text.length();i++)
        {
            if (a ==text.charAt(i))
            {
                total++;
            }
        }
        count.setText(String.valueOf(total));


    }

    public CharCounter() {
        super("Character Counter");
        text_area = new JTextArea();
        text_area.setLineWrap(true);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridBagLayout());
        main_panel.setBorder(BorderFactory.createEmptyBorder(10,15,10, 15));

        JPanel      bottom_panel = new JPanel();
        JLabel      char_label = new JLabel("Enter character:");
        char_field = new LimitedTextField(3,1);
        JButton     count_btn = new JButton("Count");
        count_btn.addActionListener(this::countChar);

//        Dimension fixedSize = new Dimension(20, char_field.getMinimumSize().height); // width x height
//        char_field.setPreferredSize(fixedSize);
//        char_field.setMinimumSize(fixedSize);
//        char_field.setMaximumSize(fixedSize);

        JLabel count_label = new JLabel("Count:");
        count = new JLabel("0");

        bottom_panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottom_panel.add(char_label,gbc);
        gbc.gridx++;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        bottom_panel.add(char_field,gbc);
        gbc.weightx = 0;
        gbc.gridx++;
        bottom_panel.add(count_btn,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        bottom_panel.add(count_label,gbc);
        gbc.gridx++;
        bottom_panel.add(count,gbc);

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        main_panel.add(bottom_panel,gbc);
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        main_panel.add(text_area,gbc);

        add(main_panel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static class LimitedTextField extends JTextField {
        public LimitedTextField(int columns, int max_len) {
            super(columns);
            ((AbstractDocument)getDocument()).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                    if ((fb.getDocument().getLength() + string.length()) <= max_len) super.insertString(fb, offset, string, attr);
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (fb.getDocument().getLength() - length + text.length() <= max_len) super.replace(fb, offset, length, text, attrs);
                }
            });
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception _) {}
        new CharCounter();
    }
}
