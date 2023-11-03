package ch.makery.address;

import ch.makery.address.controller.BirthdayStatisticsController;
import ch.makery.address.controller.PersonEditDialogController;
import ch.makery.address.controller.PersonOverviewController;
import ch.makery.address.controller.RootLayoutController;
import ch.makery.address.model.AgendaModel;
import ch.makery.address.model.ExeptionPerson;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.ConversorPerson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private final AgendaModel agendaModel = new AgendaModel();
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        //Set application icon

        this.primaryStage.getIcons().add(new Image("file:src/main/java/resources/ch/makery/address/images/icon.png"));

        initRootLayout();
        showPersonOverview();
    }
    public static void main(String[] args) {
        launch();
    }

    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    public MainApp() {
        // Add some sample data
        PersonRepository personRepository = new PersonRepositoryImpl();
        agendaModel.setPersonRepository(personRepository);
        try {
            ArrayList<PersonVO> personVOS = agendaModel.listPersonVO();
            ArrayList<Person> personArrayList = ConversorPerson.convertListPersonVO_Person(personVOS);
            agendaModel.setNumPersonVO(personArrayList.size());
            personData.addAll(personArrayList);
        } catch (ExeptionPerson exeptionPerson){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al listar las personas.");
            alert.setTitle("Error con la base de datos");
            alert.setContentText("No se puede conectar con la base de datos");
            alert.showAndWait();
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAgendaModel(agendaModel);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            // FXMLLoader loader = new FXMLLoader();
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("RootLayout.fxml"));
            // loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar contacto");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAgendaModel(agendaModel);
            controller.setPerson(person);
            controller.updateProgressBar();
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (ExeptionPerson e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estadísticas cumpleaños");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
