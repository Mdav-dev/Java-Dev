package com.projectmain.mazebank1.Controllers.Client;

import com.projectmain.mazebank1.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fname_lbl;
    public Label lname_lbl;
    public Label pAddress_lbl;
    public Label checking_acc_lbl;
    public Label savings_acc_lbl;
    public Label date_lbl;
    public Button delete_lbl;

    private final Client client;

    public ClientCellController(Client client){
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname_lbl.textProperty().bind(client.firstnameProperty());
        lname_lbl.textProperty().bind(client.lastnameProperty());
        pAddress_lbl.textProperty().bind(client.payeeAddressProperty());
        checking_acc_lbl.textProperty().bind(client.checkingAccountProperty().asString());
        savings_acc_lbl.textProperty().bind(client.savingsAccountProperty().asString());
        date_lbl.textProperty().bind(client.dateCreatedProperty().asString());
    }
}
















