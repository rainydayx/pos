import javax.swing.*;
import java.awt.*;

public class POSSystem extends JFrame {
    private JPanel mainPanel;
    private JButton latteButton, espressoButton, americanoButton, mochaButton;
    private JButton flatWhiteButton, cappuccinoButton, macchiatoButton, frappuccinoButton, matchaButton;
    private JButton CHARGEButton;
    private JTextArea textArea1;
    private double total = 0.0;

    private static final double LATTE_PRICE = 99.00;
    private static final double ESPRESSO_PRICE = 119.00;
    private static final double AMERICANO_PRICE = 99.00;
    private static final double MOCHA_PRICE = 109.00;
    private static final double FLATWHITE_PRICE = 99.00;
    private static final double CAPPUCCINO_PRICE = 119.00;
    private static final double MACCHIATO_PRICE = 109.00;
    private static final double FRAPPUCCINO_PRICE = 109.00;
    private static final double MATCHA_PRICE = 109.00;

    public POSSystem() {
        setTitle("Coffee POS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        initializeComponents();
        setupLayout();
        setupEventListeners();

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        latteButton = new JButton(String.format("Latte (₱%.2f)", LATTE_PRICE));
        espressoButton = new JButton(String.format("Espresso (₱%.2f)", ESPRESSO_PRICE));
        americanoButton = new JButton(String.format("Americano (₱%.2f)", AMERICANO_PRICE));
        mochaButton = new JButton(String.format("Mocha (₱%.2f)", MOCHA_PRICE));
        flatWhiteButton = new JButton(String.format("Flat White (₱%.2f)", FLATWHITE_PRICE));
        cappuccinoButton = new JButton(String.format("Cappuccino (₱%.2f)", CAPPUCCINO_PRICE));
        macchiatoButton = new JButton(String.format("Macchiato (₱%.2f)", MACCHIATO_PRICE));
        frappuccinoButton = new JButton(String.format("Frappuccino (₱%.2f)", FRAPPUCCINO_PRICE));
        matchaButton = new JButton(String.format("Matcha (₱%.2f)", MATCHA_PRICE));

        CHARGEButton = new JButton("CHARGE");

        textArea1 = new JTextArea(10, 30);
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }

    private void setupLayout() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 5, 5));

        buttonPanel.add(latteButton);
        buttonPanel.add(espressoButton);
        buttonPanel.add(americanoButton);
        buttonPanel.add(mochaButton);
        buttonPanel.add(flatWhiteButton);
        buttonPanel.add(cappuccinoButton);
        buttonPanel.add(macchiatoButton);
        buttonPanel.add(frappuccinoButton);
        buttonPanel.add(matchaButton);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(CHARGEButton);

        mainPanel.add(new JScrollPane(textArea1), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void setupEventListeners() {
        latteButton.addActionListener(_ -> addToOrder("Latte", LATTE_PRICE));
        espressoButton.addActionListener(_ -> addToOrder("Espresso", ESPRESSO_PRICE));
        americanoButton.addActionListener(_ -> addToOrder("Americano", AMERICANO_PRICE));
        mochaButton.addActionListener(_ -> addToOrder("Mocha", MOCHA_PRICE));
        flatWhiteButton.addActionListener(_ -> addToOrder("Flat White", FLATWHITE_PRICE));
        cappuccinoButton.addActionListener(_ -> addToOrder("Cappuccino", CAPPUCCINO_PRICE));
        macchiatoButton.addActionListener(_ -> addToOrder("Macchiato", MACCHIATO_PRICE));
        frappuccinoButton.addActionListener(_ -> addToOrder("Frappuccino", FRAPPUCCINO_PRICE));
        matchaButton.addActionListener(_ -> addToOrder("Matcha", MATCHA_PRICE));

        CHARGEButton.addActionListener(e -> {
            if (total > 0) {
                Checkout checkout = new Checkout(this, total, textArea1.getText());
                checkout.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "No items selected!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void addToOrder(String itemName, double price) {
        total += price;

        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append(textArea1.getText());

        orderSummary.append(String.format("%-12s ₱%7.2f\n", itemName + ":", price));

        String[] lines = orderSummary.toString().split("\n");
        orderSummary.setLength(0);

        for (String line : lines) {
            if (!line.startsWith("Subtotal:")) {
                orderSummary.append(line).append("\n");
            }
        }

        orderSummary.append(String.format("%-12s ₱%7.2f\n", "Subtotal:", total));

        textArea1.setText(orderSummary.toString());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            POSSystem pos = new POSSystem();
            pos.setVisible(true);
        });
    }
}
