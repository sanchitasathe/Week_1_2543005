import java.awt.*;
import java.awt.event.*;

public class SmallCalculatorAWT extends Frame implements ActionListener {
    TextField display;
    String operator = "";
    double num1 = 0, num2 = 0;

    Button[] numButtons = new Button[10];
    Button bAdd, bSub, bMul, bDiv, bEq, bClear;

    SmallCalculatorAWT() {
        setTitle("AWT Calculator");
        setSize(200, 250); 
        setLayout(new BorderLayout());

        
        display = new TextField();
        display.setEditable(false);
        display.setBackground(new Color(255, 255, 200));
        display.setFont(new Font("Arial", Font.BOLD, 14)); // smaller font
        add(display, BorderLayout.NORTH);

        
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4, 3, 3)); // tighter spacing
        buttonPanel.setBackground(new Color(230, 240, 250));

        
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].setFont(new Font("Arial", Font.PLAIN, 12)); // smaller button text
            numButtons[i].addActionListener(this);
        }

        
        bAdd = new Button("+");
        bSub = new Button("-");
        bMul = new Button("*");
        bDiv = new Button("/");
        bEq = new Button("=");
        bClear = new Button("C");

        Button[] ops = {bAdd, bSub, bMul, bDiv, bEq, bClear};
        for (Button b : ops) {
            b.setFont(new Font("Arial", Font.PLAIN, 12));
            b.setBackground(new Color(200, 220, 240));
            b.addActionListener(this);
        }

        
        buttonPanel.add(numButtons[7]); buttonPanel.add(numButtons[8]); buttonPanel.add(numButtons[9]); buttonPanel.add(bAdd);
        buttonPanel.add(numButtons[4]); buttonPanel.add(numButtons[5]); buttonPanel.add(numButtons[6]); buttonPanel.add(bSub);
        buttonPanel.add(numButtons[1]); buttonPanel.add(numButtons[2]); buttonPanel.add(numButtons[3]); buttonPanel.add(bMul);
        buttonPanel.add(numButtons[0]); buttonPanel.add(bClear);        buttonPanel.add(bEq);           buttonPanel.add(bDiv);

        add(buttonPanel, BorderLayout.CENTER);

       
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();

        try {
            if (cmd.matches("[0-9]")) {
                display.setText(display.getText() + cmd);
            } else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || cmd.equals("/")) {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            } else if (cmd.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                double result = 0;

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Divide by zero");
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } else if (cmd.equals("C")) {
                display.setText("");
                num1 = num2 = 0;
                operator = "";
            }
        } catch (ArithmeticException e) {
            display.setText("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            display.setText("Invalid input!");
        }
    }

    public static void main(String[] args) {
        new SmallCalculatorAWT();
    }
}
