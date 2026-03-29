import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvestmentCalculator extends JFrame {

    // GUI Components
    private JTextField principalField;
    private JTextField rateField;
    private JTextField yearsField;
    private JLabel resultLabel;
    private JButton calculateButton;

    public InvestmentCalculator() {
        // 1. Set up the main window (JFrame)
        setTitle("Investment Calculator");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on screen
        
        // Use a GridLayout to organize components neatly into rows and columns
        setLayout(new GridLayout(5, 2, 10, 10));

        // 2. Initialize the components
        JLabel principalLabel = new JLabel(" Principal Amount ($):");
        principalField = new JTextField();

        JLabel rateLabel = new JLabel(" Annual Interest Rate (%):");
        rateField = new JTextField();

        JLabel yearsLabel = new JLabel(" Number of Years:");
        yearsField = new JTextField();

        calculateButton = new JButton("Calculate");
        JLabel emptyLabel = new JLabel(""); // Placeholder for grid alignment

        JLabel resultTitleLabel = new JLabel(" Future Value ($):");
        resultLabel = new JLabel("0.00");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(0, 128, 0)); // Dark green text

        // 3. Add components to the frame
        add(principalLabel);
        add(principalField);
        
        add(rateLabel);
        add(rateField);
        
        add(yearsLabel);
        add(yearsField);
        
        add(emptyLabel); // Empty space in the left column
        add(calculateButton);
        
        add(resultTitleLabel);
        add(resultLabel);

        // 4. Add the event listener for the button click
        calculateButton.addActionListener(new CalculateAction());
    }

    /**
     * Inner class to handle the button click event.
     */
    private class CalculateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Parse input from the text fields
                double principal = Double.parseDouble(principalField.getText());
                double rate = Double.parseDouble(rateField.getText());
                int years = Integer.parseInt(yearsField.getText());

                // Validate inputs to ensure they are not negative
                if (principal < 0 || rate < 0 || years < 0) {
                    JOptionPane.showMessageDialog(
                        InvestmentCalculator.this, 
                        "Please enter positive values only.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Calculate Compound Interest: A = P(1 + r/100)^t
                double futureValue = principal * Math.pow(1 + (rate / 100), years);

                // Update the result label with formatted output
                resultLabel.setText(String.format("%,.2f", futureValue));

            } catch (NumberFormatException ex) {
                // Catch the error if the user leaves a field blank or types letters
                JOptionPane.showMessageDialog(
                    InvestmentCalculator.this, 
                    "Please enter valid numeric values in all fields.", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        // Ensure GUI creation happens on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> {
            InvestmentCalculator calculator = new InvestmentCalculator();
            calculator.setVisible(true);
        });
    }
}
