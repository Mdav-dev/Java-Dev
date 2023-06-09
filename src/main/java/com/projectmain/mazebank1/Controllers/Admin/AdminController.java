package com.projectmain.mazebank1.Controllers.Admin;

import com.projectmain.mazebank1.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal)-> {
            switch (newVal){
                case LOGOUT -> Model.getInstance().getViewFactory().showLoginWindow();
                case DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientViews());
            }
        });

    }
}
