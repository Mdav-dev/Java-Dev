package com.projectmain.mazebank1.Controllers.Admin;

import com.projectmain.mazebank1.Models.Model;
import com.projectmain.mazebank1.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();

    }

    private void addListeners() {
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClients());
        deposit_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onLogout() {

//        Get Stage
            Stage stage = (Stage) clients_btn.getScene().getWindow();
//        Close the client window
            Model.getInstance().getViewFactory().closeStage(stage);
//        Show Login window
            Model.getInstance().getViewFactory().showLoginWindow();
//        Set Client Login Success Flag to False
            Model.getInstance().setAdminLoginSuccessFlag(false);

    }

    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
    }

    private void onClients() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }

    private void onCreateClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }
}
