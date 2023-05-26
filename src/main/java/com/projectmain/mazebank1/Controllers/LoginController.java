package com.projectmain.mazebank1.Controllers;

import com.projectmain.mazebank1.Models.Model;
import com.projectmain.mazebank1.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public Button login_btn;
    public Label error_lbl;
    public TextField password_fld;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(event -> onLogin());

    }
    private void onLogin(){
        Stage stage =(Stage) error_lbl.getScene().getWindow();

        if(Model.getInstance().getViewFactory().getLoginAccountType()== AccountType.CLIENT){

//            Evaluate Client Login Credentials
            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
            if (Model.getInstance().getClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                error_lbl.setText("Login Successful");
//                Close the Login stage
                Model.getInstance().getViewFactory().closeStage(stage);

            }else {
                password_fld.setText("");
                payee_address_fld.setText("");
                error_lbl.setText("No Such Login Credentials.");
            }

        }else {
//            Evaluate Admin Login Credentials
            Model.getInstance().evaluationAdminCred(payee_address_fld.getText(), password_fld.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showAdminWindow();
//                Close the login page
                Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials");
            }
        }
    }

    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
//        Changing Payee Address
        if (acc_selector.getValue()== AccountType.ADMIN){
            payee_address_lbl.setText("Username:");
        }else {
            payee_address_lbl.setText("Payee Address:");
        }
    }
}








