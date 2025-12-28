/**
 * CalculateApp class ...
 * 
 * Set button action and slider listener.
 * FXMLLoader calls initialize() to intialize the Controller.
 */

package com.minte9.javafx.change_listener;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class CalculateApp {

    private NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();
    private NumberFormat PERCENT = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0.15);
    
    @FXML private TextField amountTextField;
    @FXML private Label tipPercentageLabel;
    @FXML private Slider tipPercentageSlider;
    @FXML private TextField tipTextField;
    @FXML private TextField totalTextField;

    public static void main(String[] args) {
        CalculateGui.main(args);
    }

    @FXML void calculateButtonPressed() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);
            tipTextField.setText(CURRENCY.format(tip));
            totalTextField.setText(CURRENCY.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }

    @FXML void initialize() {
        CURRENCY.setRoundingMode(RoundingMode.HALF_UP);
        tipPercentageSlider.valueProperty().addListener( // Look Here
            (o, v, newValue) -> {
                tipPercentage = BigDecimal.valueOf(
                    newValue.intValue() / 100.0
                );
                tipPercentageLabel.setText(PERCENT.format(tipPercentage));
            }
        );
    }
}