package com.example.ui;

import com.example.ConfigurationControllers;
import com.example.entities.Automobile;
import com.example.entities.Reservation;
import com.example.service.ReservationsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class EditReservationController extends EventHandling{

    private Reservation reservation;

    @Autowired
    private ReservationsService reservationService;

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
        if(reservation == null) {
            reservation = new Reservation("a","a","a","a","a");
        }
        reservation.setUsername(usernameEdit.getText());
        reservation.setTelephone(telephoneEdit.getText());
        reservation.setReservationmodel(modelEdit.getText());
        reservation.setGive_date(give_dataEdit.getText());
        reservation.setBack_date(back_dateEdit.getText());

        reservationService.update(reservation);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.close();
    }

    public void exit(ActionEvent actionEvent) {
        changeSceneByEvent(actionEvent,autoreservationView,"AutoReservePage");
    }

    public void setReservation(Reservation reservation) {
        usernameEdit.setText(reservation.getUsername());
        telephoneEdit.setText(String.valueOf(reservation.getTelephone()));
        modelEdit.setText(String.valueOf(reservation.getReservationmodel()));
        give_dataEdit.setText(String.valueOf(reservation.getGive_date()));
        back_dateEdit.setText(String.valueOf(reservation.getBack_date()));
    }

}
