module com.projectmain.mazebank1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.projectmain.mazebank1 to javafx.fxml;
    exports com.projectmain.mazebank1;
    exports com.projectmain.mazebank1.Controllers;
    exports com.projectmain.mazebank1.Controllers.Client;
    exports com.projectmain.mazebank1.Controllers.Admin;
    exports com.projectmain.mazebank1.Views;
}