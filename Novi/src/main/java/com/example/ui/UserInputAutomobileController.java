package com.example.ui;


import com.example.ConfigurationControllers;
import com.example.service.AutomobilesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserInputAutomobileController extends EventHandling {

    @Qualifier("userautomobileView")
    @Autowired
    private ConfigurationControllers.View userautomobileView;

    @Autowired
    AutomobilesService automobilesService;

    @FXML public TableView table;
    @FXML public TextField username;
    @FXML public TextField telephone;
    @FXML public TextField model;
    @FXML public TextField give_date;
    @FXML public TextField back_date;

    @FXML
    public void initialize() {
    }

    @FXML
    public void confirmReservation(ActionEvent actionEvent) {
        String name = username.getText();
        String tele = telephone.getText();
        String mod = model.getText();
        String give = give_date.getText();
        String back = back_date.getText();

        if(automobilesService.findByModel(mod) != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thanksgiving");
            alert.setHeaderText("Thanks for your choice");
            alert.setContentText("Operator will call you later");

            alert.show();

            username.setText("");
            telephone.setText("");
            model.setText("");
            give_date.setText("");
            back_date.setText("");

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fail");
            alert.setTitle("No such model");
            alert.setContentText("Correct the model of the car");

            alert.showAndWait();
        }

    }

    @FXML
    public void cancelReservation(ActionEvent actionEvent) {
//        Node toAutomobile = (Node) actionEvent.getSource();
//
//        Stage stage = (Stage) toAutomobile.getScene().getWindow();
//        stage.setScene(new Scene(userautomobileView.getView()));//на userautomobile
//        stage.show();
        changeSceneByEvent(actionEvent,userautomobileView,"UserAutomobile");
    }
}
