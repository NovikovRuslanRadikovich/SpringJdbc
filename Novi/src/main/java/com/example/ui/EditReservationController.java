package com.example.ui;

import com.example.ConfigurationControllers;
import com.example.entities.Automobile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class EditReservationController extends EventHandling{

    @Qualifier("autoreservationtabView")
    @Autowired
    ConfigurationControllers.View autoreservationView;

    @FXML public TextField usernameEdit;
    @FXML public TextField telephoneEdit;
    @FXML public TextField modelEdit;
    @FXML public TextField give_dataEdit;
    @FXML public TextField back_dateEdit;

    @FXML
    public void initialize() {
    }

    public void editReservation(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        changeSceneByEvent(actionEvent,autoreservationView,"AutoReservePage");
    }

}
