package com.projectmain.mazebank1.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label checking_acc_num;
    public Label transaction_limit;
    public Label checking_acc_date;
    public Label checking_acc_bal;
    public Label savings_acc_num;
    public Label withdrawal_limit;
    public Label savings_acc_date;
    public Label savings_acc_bal;
    public TextField amount_to_savings_acc;
    public Button to_savings_transfer_btn;
    public TextField amount_to_checking_acc;
    public Button to_checking_transfer_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
