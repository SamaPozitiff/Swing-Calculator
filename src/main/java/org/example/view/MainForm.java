package org.example.view;

import org.example.entity.Operation;
import org.example.entity.Operations;
import org.example.util.Calculator;
import org.example.util.XmlUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainForm extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton buttonAdd;
    private JButton buttonMultiply;
    private JButton buttonSubtract;
    private JButton buttonDivide;
    private JButton buttonEquals;
    private JButton buttonExport;
    private JButton buttonPoint;
    private JFormattedTextField textField;
    private JButton buttonBackSpase;
    private JButton buttonOpenRoundBracket;
    private JButton buttonCloseRoundBracket;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final ArrayList<Operation> operationsList = new ArrayList<>();
    private final Font font = new Font("TimesRoman", Font.BOLD, 33);

    public MainForm(){
        setupUI();
        addListeners();
    }

    private void setupUI(){
        //добавление всех элементов на панель и после на форму
        JPanel panel = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        textField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        panel.add(textField, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        buttonBackSpase = new JButton("<-");
        panel.add(buttonBackSpase, gbc);

        gbc.gridx = 1;
        buttonDivide = new JButton("/");
        panel.add(buttonDivide, gbc);

        gbc.gridx = 2;
        buttonMultiply  = new JButton("*");
        panel.add(buttonMultiply, gbc);

        gbc.gridx = 3;
        buttonSubtract = new JButton("-");
        panel.add(buttonSubtract, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        button7 = new JButton("7");
        panel.add(button7, gbc);

        gbc.gridx = 1;
        button8 = new JButton("8");
        panel.add(button8, gbc);


        gbc.gridx = 2;
        button9 = new JButton("9");
        panel.add(button9, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        button4 = new JButton("4");
        panel.add(button4, gbc);

        gbc.gridx = 1;
        button5 = new JButton("5");
        panel.add(button5, gbc);

        gbc.gridx = 2;
        button6 = new JButton("6");
        panel.add(button6, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        button1 = new JButton("1");
        panel.add(button1, gbc);

        gbc.gridx = 1;
        button2 = new JButton("2");
        panel.add(button2, gbc);

        gbc.gridx = 2;
        button3 = new JButton("3");
        panel.add(button3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        buttonPoint = new JButton(".");
        panel.add(buttonPoint, gbc);

        gbc.gridx = 2;
        buttonEquals = new JButton("=");
        panel.add(buttonEquals, gbc);

        gbc.gridx = 3;
        buttonExport = new JButton("E");
        panel.add(buttonExport, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        buttonAdd = new JButton("+");
        panel.add(buttonAdd, gbc);

        gbc.gridy = 3;
        buttonOpenRoundBracket  = new JButton("(");
        panel.add(buttonOpenRoundBracket, gbc);

        gbc.gridy = 4;
        buttonCloseRoundBracket = new JButton(")");
        panel.add(buttonCloseRoundBracket, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        button0 = new JButton("0");
        panel.add(button0, gbc);
        for(Component component : panel.getComponents()){
            component.setFont(font);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        setTitle("Калькулятор");
        setBounds(0,0,500,500);
        setVisible(true);
    }

    private void addListeners(){
        //всем элементам присваиваются слушатели
        ActionListener buttonListener = e -> {
            JButton sourceButton = (JButton) e.getSource();
            String buttonText = sourceButton.getText();
            textField.setText(textField.getText() + buttonText);
            repaint();
        };

        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
        button3.addActionListener(buttonListener);
        button4.addActionListener(buttonListener);
        button5.addActionListener(buttonListener);
        button6.addActionListener(buttonListener);
        button7.addActionListener(buttonListener);
        button8.addActionListener(buttonListener);
        button9.addActionListener(buttonListener);
        button0.addActionListener(buttonListener);
        buttonDivide.addActionListener(buttonListener);
        buttonMultiply.addActionListener(buttonListener);
        buttonSubtract.addActionListener(buttonListener);
        buttonAdd.addActionListener(buttonListener);
        buttonPoint.addActionListener(buttonListener);
        buttonOpenRoundBracket.addActionListener(buttonListener);
        buttonCloseRoundBracket.addActionListener(buttonListener);
        buttonEquals.addActionListener(e -> {
            String result = Calculator.evaluate(textField.getText());
            if(result.equalsIgnoreCase("infinity")){
                JOptionPane.showMessageDialog(this, "Деление на ноль недопустимо");
            }else {
                Operation operation = new Operation();
                operation.setOperation(textField.getText() + "=" + result);
                this.operationsList.add(operation);
                textField.setText(result);
            }

        });
        buttonBackSpase.addActionListener(e -> textField.setText(textField.getText().substring(0, textField.getText().length()-1)));
        buttonExport.addActionListener(e -> {
            Operations operations = new Operations();
            operations.setOperations(operationsList);
            XmlUtils.marshall(operations);
            operationsList.clear();
        });
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    e.consume();
                }
            }
        });
    }


}
