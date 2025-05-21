module com.mebae.diparitor {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mebae.diparitor to javafx.fxml;
    exports com.mebae.diparitor;
}
