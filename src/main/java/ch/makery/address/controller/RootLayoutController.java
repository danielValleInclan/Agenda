package ch.makery.address.controller;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {
    private MainApp mainApp;
    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
}
