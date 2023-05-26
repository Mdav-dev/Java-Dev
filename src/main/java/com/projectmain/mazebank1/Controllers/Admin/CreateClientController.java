package com.projectmain.mazebank1.Controllers.Admin;

import com.projectmain.mazebank1.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;

import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField sName_fld;
    public TextField password_fld;
    public CheckBox payee_address_box;
    public Label payee_address_lbl;
    public CheckBox add_ch_acc_box;
    public TextField checking_acc_bal;
    public CheckBox sv_acc_box;
    public TextField sv_acc_bal;
    public Button create_client_btn;
    public Label error_lbl;

    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingsAccountFlag = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> createClient());
        payee_address_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();

            }
        });
        add_ch_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if(newVal){
                createCheckingAccountFlag = true;
            }
        });
        sv_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) ->{
            if(newVal){
                createSavingsAccountFlag = true;
            }
        });

    }

    public void createClient() {
//        Creating Checking Account
        if (createCheckingAccountFlag) {
            createAccount("Checking");
        }
        if (createSavingsAccountFlag) {
            createAccount("Savings");
        }
        String fName = fName_fld.getText();
        String lName = sName_fld.getText();
        String password = password_fld.getText();
        Model.getInstance().getDatabaseDriver().createClient(fName, lName, payeeAddress, password, LocalDate.now());
        error_lbl.setText("Client Created Successfully!");
        emptyField();

    }

    public void onCreatePayeeAddress() {
        if (fName_fld.getText() != null & sName_fld.getText() != null) {
            payee_address_lbl.setText(payeeAddress);
        }
    }

    private String createPayeeAddress() {
        int id = Model.getInstance().getDatabaseDriver().getLastClientsId() + 1;
        char fChar = Character.toLowerCase((fName_fld.getText().charAt(0)));
        return "@" + fChar + sName_fld.getText() + id;

    }


    private void createAccount(String accountType) {
        double balance = Double.parseDouble(checking_acc_bal.getText());
        double sbalance = Double.parseDouble(sv_acc_bal.getText());
//            Generate Account Number
        String firstSection = "3201";
        String lastSection = Integer.toString((new Random()).nextInt(8999) + 1000);
        String accountNumber = firstSection +" "+lastSection;
//        Create the checking account
        if (accountType.equals("Checking")) {
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber, 10, balance);
        } else {
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress, accountNumber, 2000, sbalance);

        }
    }

    private void emptyField() {
        fName_fld.setText("");
        sName_fld.setText("");
        password_fld.setText("");
        payee_address_box.setSelected(false);
        payee_address_lbl.setText("");
        add_ch_acc_box.setSelected(false);
        checking_acc_bal.setText("");
        sv_acc_box.setSelected(false);
        sv_acc_bal.setText("");


    }





}








































