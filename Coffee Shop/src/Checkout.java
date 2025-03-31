import javax.swing.*;
import java.awt.*;

public class Checkout extends JFrame {
    private JTextArea textArea1;
    private JButton cardButton;
    private JButton cashButton;
    private double totalAmount;

    public Checkout(JFrame parent, double total, String orderDetails) {
        setTitle("Checkout");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);

        totalAmount = total;

        JLabel totalLabel = new JLabel(String.format("Total: ₱%.2f", totalAmount));
        textArea1 = new JTextArea(10, 30);
        textArea1.setText(orderDetails);
        textArea1.setEditable(false);

        JButton cashButton = new JButton("Pay with Cash");
        JButton cardButton = new JButton("Pay with Card");

        cashButton.addActionListener(_ -> {
            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

            JTextField totalField = new JTextField(String.format("₱%.2f", totalAmount));
            totalField.setEditable(false);

            JTextField cashField = new JTextField();
            inputPanel.add(new JLabel("Total Amount:"));
            inputPanel.add(totalField);
            inputPanel.add(new JLabel("Cash Given:"));
            inputPanel.add(cashField);

            int result = JOptionPane.showConfirmDialog(this, inputPanel, "Enter Payment", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double cashGiven = Double.parseDouble(cashField.getText());

                    if (cashGiven >= totalAmount) {
                        double change = cashGiven - totalAmount;
                        JOptionPane.showMessageDialog(this, String.format(
                                "Payment Successful!\nTotal: ₱%.2f\nCash: ₱%.2f\nChange: ₱%.2f",
                                totalAmount, cashGiven, change
                        ));
                        parent.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, String.format(
                                "Insufficient payment. You need ₱%.2f more.",
                                totalAmount - cashGiven
                        ), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cardButton.addActionListener(_ -> {
            JOptionPane.showMessageDialog(this, "Card payment processed. Thank you!");
            parent.setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(totalLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea1), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(cashButton);
        buttonPanel.add(cardButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = new JFrame("Parent Frame");
            parentFrame.setSize(300, 200);
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.setLocationRelativeTo(null);
            parentFrame.setVisible(true);

            double total = 150.75;
            String orderDetails = "Item 1: ₱50.00\nItem 2: ₱100.75";

            Checkout checkout = new Checkout(parentFrame, total, orderDetails);
            checkout.setVisible(true);
            parentFrame.setVisible(false);
        });
    }
}