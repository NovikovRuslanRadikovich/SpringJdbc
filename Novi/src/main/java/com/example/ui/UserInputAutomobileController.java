package com.example.ui;


import com.example.ConfigurationControllers;
import com.example.service.AutomobilesService;
import com.example.service.UsersService;
import com.example.validation.ReservationValid;
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

import java.text.ParseException;

public class UserInputAutomobileController extends EventHandling {

    @Qualifier("userautomobileView")
    @Autowired
    private ConfigurationControllers.View userautomobileView;

    @Autowired
    AutomobilesService automobilesService;

    @Autowired
    UsersService usersService;

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
    public void confirmReservation(ActionEvent actionEvent) throws ParseException {

        ReservationValid Valid = new ReservationValid();

        if(Valid.validateByEndDate(back_date.getText())
                && Valid.validateByStartDate(give_date.getText()) &&
                Valid.validateByComparingStartAndEndDates(give_date.getText(),back_date.getText())
              ) {
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

        } else if(automobilesService.findFromModel(model.getText()) == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fail");
            alert.setHeaderText("No such model");
            alert.setContentText("Correct the model of the car");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong input!");
            alert.setHeaderText("Incorrect data format");
            alert.setContentText("Please, verify give_data and back_data");
            alert.showAndWait();
        }

    }

    @FXML
    public void cancelReservation(ActionEvent actionEvent) {

        changeSceneByEvent(actionEvent,userautomobileView,"UserAutomobile");
    }
}
