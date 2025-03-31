import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JButton SIGNINButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel mainPanel;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        textField1 = new JTextField(15);
        passwordField1 = new JPasswordField(15);
        SIGNINButton = new JButton("SIGN IN");


        mainPanel.add(new JLabel("Username:"));
        mainPanel.add(textField1);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField1);
        mainPanel.add(new JLabel());
        mainPanel.add(SIGNINButton);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SIGNINButton.addActionListener(_ -> {
            if (authenticate(textField1.getText(), new String(passwordField1.getPassword()))) {
                POSSystem posSystem = new POSSystem();
                posSystem.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        });
    }

    private boolean authenticate(String user, String pass) {
        return "admin".equals(user) && "1234".equals(pass);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}