package com.projectmain.mazebank1.Controllers.Admin;

import com.projectmain.mazebank1.Models.Client;
import com.projectmain.mazebank1.Models.Model;
import com.projectmain.mazebank1.Views.ClientCellFactory;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField search_fld;
    public Button search_btn;
    public ListView<Client> search_listview;
    public TextField deposit_fld;
    public Button deposit_btn;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(event -> onClientSearch());
        deposit_btn.setOnAction(event -> onDeposit());
    }
    private void onClientSearch(){
        ObservableList<Client> searchResults = Model.getInstance().searchClients(search_fld.getText());
        search_listview.setItems(searchResults);
        search_listview.setCellFactory(e-> new ClientCellFactory());
        client = searchResults.get(0);


    }

    private void onDeposit(){
        double amount = Double.parseDouble(deposit_fld.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();
        if (deposit_fld.getText() != null){
            Model.getInstance().getDatabaseDriver().depositSavings(client.payeeAddressProperty().get(), newBalance);
        }
        emptyField();
    }

    private void emptyField(){
        search_fld.setText("");
        deposit_fld.setText("");
    }
}






























