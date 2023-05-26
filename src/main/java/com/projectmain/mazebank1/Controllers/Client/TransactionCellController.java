package com.projectmain.mazebank1.Controllers.Client;

import com.projectmain.mazebank1.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public FontAwesomeIconView receiving_arrow;
    public FontAwesomeIconView sending_arrow;
    public Label transaction_date;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_transacted_lbl;


    private final Transaction transaction;

    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
