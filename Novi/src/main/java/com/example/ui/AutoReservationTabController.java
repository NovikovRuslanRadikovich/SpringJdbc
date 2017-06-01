package com.example.ui;


import com.example.ConfigurationControllers;
import com.example.entities.Automobile;
import com.example.entities.Reservation;
import com.example.service.AutomobilesService;
import com.example.service.ReservationsService;
import com.example.validation.AutomobileValid;
import com.example.validation.ReservationValid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class AutoReservationTabController extends EventHandling{

    private Stage autoEditStage;

    private Stage reservationEditStage;

    @Autowired
    AutomobilesService automobileServiceImpl;


    @Autowired
    ReservationsService reservationsServiceImpl;

    @Qualifier("editautomobileView")
    @Autowired
    ConfigurationControllers.View editAutoView;

    @Qualifier("editreservationView")
    @Autowired
    ConfigurationControllers.View editReservationView;

    public TableView autotable;
    public TextField year;
    public TextField automodel;
    public TextField probeg;
    public TextField powerty;
    public TextField rentpay;


    public TableView reservationtable;
    public TextField username;
    public TextField telephone;
    public TextField reservationmodel;
    public TextField give_date;
    public TextField back_date;

    private ObservableList<Automobile> dataAutomobile;

    private ObservableList<Reservation> dataReservation;

    @FXML
    public void initialize() {
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init(){
        List<Automobile> automobiles = automobileServiceImpl.findAll();
        if(automobiles != null) {
            dataAutomobile = FXCollections.observableArrayList(automobiles);
        }

        TableColumn<Automobile,String> automodelColumn = new TableColumn<>("Model");
        automodelColumn.setCellValueFactory(new PropertyValueFactory<>("automodel"));

        TableColumn<Automobile, String> yearColumn = new TableColumn<>("Год");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Automobile, String> probegColumn = new TableColumn<>("Пробег");
        probegColumn.setCellValueFactory(new PropertyValueFactory<>("probeg"));

        TableColumn<Automobile, String> powertyColumn = new TableColumn<>("Мощность");
        powertyColumn.setCellValueFactory(new PropertyValueFactory<>("powerty"));

        TableColumn<Automobile, String> rentpayColumn = new TableColumn<>("Аренда");
        rentpayColumn.setCellValueFactory(new PropertyValueFactory<>("rentpay"));

        autotable.getColumns().setAll(automodelColumn, yearColumn, probegColumn, powertyColumn, rentpayColumn);

        autotable.setItems(dataAutomobile);



        List<Reservation> reservations = reservationsServiceImpl.findAll();
        if(reservations != null) {
            dataReservation = FXCollections.observableArrayList(reservations);
        }

        TableColumn<Automobile,String> usernameColumn = new TableColumn<>("Клинет");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Automobile, String> telephoneColumn = new TableColumn<>("Телефон");
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        TableColumn<Automobile, String> reservationmodelColumn = new TableColumn<>("Модель");
        reservationmodelColumn.setCellValueFactory(new PropertyValueFactory<>("reservationmodel"));

        TableColumn<Automobile, String> give_dateColumn = new TableColumn<>("Выдача");
        give_dateColumn.setCellValueFactory(new PropertyValueFactory<>("give_date"));

        TableColumn<Automobile, String> back_dateColumn = new TableColumn<>("Возврат");
        back_dateColumn.setCellValueFactory(new PropertyValueFactory<>("back_date"));

        reservationtable.getColumns().setAll(usernameColumn, telephoneColumn, reservationmodelColumn, give_dateColumn, back_dateColumn);

        reservationtable.setItems(dataReservation);
    }


    @FXML
    public void addCar() {
        AutomobileValid autoValid = new AutomobileValid();
        if(autoValid.validateByPowerty(powerty.getText()) && autoValid.validateByProbeg(probeg.getText())
                && autoValid.validateByRentpay(rentpay.getText())
                && autoValid.validateByYear(year.getText())) {
            Automobile automobile = new Automobile(automodel.getText(), year.getText()
                    , probeg.getText(), powerty.getText(),rentpay.getText());
             automobileServiceImpl.save(automobile);
             dataAutomobile.add(automobile);
             automodel.setText("");
             year.setText("");
             probeg.setText("");
             powerty.setText("");
             rentpay.setText("");
         }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong data");
            alert.setHeaderText("Check data for automobile!");
            alert.setContentText("Impossible to add automobile with such parameters");
            alert.showAndWait();
        }
    }


    @FXML
    public void deleteCar(ActionEvent actionEvent) {
        int index = autotable.getSelectionModel().getSelectedIndex();
        Automobile automobile = (Automobile) autotable.getSelectionModel().getSelectedItem();
        if(index >= 0) {
            automobileServiceImpl.delete(automobile);
            dataAutomobile.remove(index);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Automobile Selected");
            alert.setContentText("Please select an automobile in the table");
            alert.showAndWait();
        }

    }

    @FXML
    public void handleEditCar(ActionEvent actionEvent) throws IOException {
        Automobile selectedAutomobile = (Automobile) autotable.getSelectionModel().getSelectedItem();
        if(selectedAutomobile != null) {
              showEditDialog(selectedAutomobile);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Automobile Selected");
            alert.setContentText("Please select an automobile in the table");
            alert.showAndWait();
        }
    }

    public void showEditDialog(Automobile automobile) throws IOException {
       if(autoEditStage == null) {
           autoEditStage = new Stage();
           autoEditStage.setTitle("Edit Auto");
           autoEditStage.setScene(new Scene(editAutoView.getView()));
       }
       EditAutomobilesController editAutoController = (EditAutomobilesController) editAutoView.getController();
       editAutoController.setAutomobile(automobile);
        autoEditStage.showAndWait();

        List<Automobile> automobiles = automobileServiceImpl.findAll();

        if(automobiles != null) {
            dataAutomobile = FXCollections.observableArrayList(automobiles);
        }

        autotable.setItems(dataAutomobile);

    }

    @FXML
    public void addReservation() throws ParseException {

        ReservationValid Valid = new ReservationValid();
       if(Valid.validateByTelephone(telephone.getText()) &&
               Valid.validateByEndDate(back_date.getText())
               && Valid.validateByStartDate(give_date.getText()) &&
        Valid.validateByComparingStartAndEndDates(give_date.getText(),back_date.getText())) {
           Reservation reservation = new Reservation(username.getText(),telephone.getText(),reservationmodel.getText(),
                   give_date.getText(),back_date.getText());
           reservationsServiceImpl.save(reservation);
           dataReservation.add(reservation);
           username.setText("");
           telephone.setText("");
           reservationmodel.setText("");
           give_date.setText("");
           back_date.setText("");
       }else {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Wrong data");
           alert.setHeaderText("Check data for reservation!");
           alert.setContentText("Impossible to add reservation with such parameters");
           alert.showAndWait();
       }
    }

    @FXML
    public void deleteReservation() {
        int index = reservationtable.getSelectionModel().getSelectedIndex();
        Reservation reservation = (Reservation) reservationtable.getSelectionModel().getSelectedItem();
        if(index >= 0) {
            reservationsServiceImpl.delete(reservation);
            dataReservation.remove(index);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Reservation Selected");
            alert.setContentText("Please select a reservation in the table");
            alert.showAndWait();
        }
    }


    @FXML
    public void handleEditReservation(ActionEvent actionEvent) {
        Reservation selectedReservation = (Reservation) reservationtable.getSelectionModel().getSelectedItem();
        if(selectedReservation != null) {
               showReservationEditDialog(selectedReservation);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Reservation Selected");
            alert.setContentText("Please select any reservation in the table");
            alert.showAndWait();
        }
    }

    private void showReservationEditDialog(Reservation selectedReservation) {
        if(reservationEditStage == null) {
            reservationEditStage = new Stage();
            reservationEditStage.setTitle("Edit Auto");
            reservationEditStage.setScene(new Scene(editReservationView.getView()));
        }

        EditReservationController editReservationController = (EditReservationController) editReservationView.getController();
        editReservationController.setReservation(selectedReservation);
        reservationEditStage.showAndWait();


        List<Reservation> reservations = reservationsServiceImpl.findAll();

        if(reservations != null) {
            dataReservation = FXCollections.observableArrayList(reservations);
        }

        reservationtable.setItems(dataReservation);
    }
}
