module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens ch.makery.address to javafx.fxml;
    exports ch.makery.address;
    exports ch.makery.address.controller;
    opens ch.makery.address.controller to javafx.fxml;
}