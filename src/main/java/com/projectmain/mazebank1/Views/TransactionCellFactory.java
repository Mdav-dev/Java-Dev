package com.projectmain.mazebank1.Views;

import com.projectmain.mazebank1.Controllers.Client.TransactionCellController;
import com.projectmain.mazebank1.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TransactionCellFactory extends ListCell<Transaction> {
    @Override
    protected void updateItem(Transaction transaction, boolean empty) {
        super.updateItem(transaction, empty);
        if(empty){
            setText(null);
            setGraphic(null);

        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            TransactionCellController controller = new TransactionCellController(transaction);
            loader.setController(controller);
            setTooltip(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
