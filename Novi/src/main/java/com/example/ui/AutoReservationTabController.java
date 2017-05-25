package com.example.ui;


import com.example.entities.Automobile;
import com.example.entities.Reservation;
import com.example.service.AutomobilesService;
import com.example.service.ReservationsService;
import com.example.validation.ReservationValid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class AutoReservationTabController extends EventHandling{

    @Autowired
    AutomobilesService automobileServiceImpl;


    @Autowired
    ReservationsService reservationsServiceImpl;

   @FXML public AnchorPane automobileTab;

   @FXML public AnchorPane reservationTab;
    public TableView autotable;
    public TextField year;
    public TextField model;
    public TextField probeg;
    public TextField powerty;
    public TextField rentpay;

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

        TableColumn<Automobile,String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Automobile, String> yearColumn = new TableColumn<>("Год");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<Automobile, String> probegColumn = new TableColumn<>("Пробег");
        probegColumn.setCellValueFactory(new PropertyValueFactory<>("probeg"));

        TableColumn<Automobile, String> powertyColumn = new TableColumn<>("Мощность");
        powertyColumn.setCellValueFactory(new PropertyValueFactory<>("powerty"));

        TableColumn<Automobile, String> rentpayColumn = new TableColumn<>("Аренда");
        rentpayColumn.setCellValueFactory(new PropertyValueFactory<>("rentpay"));

        autotable.getColumns().setAll(modelColumn, yearColumn, probegColumn, powertyColumn, rentpayColumn);

        autotable.setItems(dataAutomobile);



        List<Reservation> reservations = reservationsServiceImpl.findAll();
        if(reservations != null) {
            dataReservation = FXCollections.observableArrayList(reservations);
        }

        TableColumn<Automobile,String> clientColumn = new TableColumn<>("Клинет");
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Automobile, String> telephoneReservationColumn = new TableColumn<>("Телефон");
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        TableColumn<Automobile, String> modelReservationColumn = new TableColumn<>("Модель");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        TableColumn<Automobile, String> startColumn = new TableColumn<>("Выдача");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("give_date"));

        TableColumn<Automobile, String> endColumn = new TableColumn<>("Возврат");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("back_date"));

        reservationtable.getColumns().setAll(clientColumn, telephoneReservationColumn, modelReservationColumn, startColumn, endColumn);

        reservationtable.setItems(dataReservation);
    }


    @FXML
    public void addCar() {
        Automobile automobile = new Automobile(model.getText(),Long.valueOf(year.getText())
                ,Long.valueOf(probeg.getText()),Long.valueOf(powerty.getText()),Long.valueOf(rentpay.getText()));

        automobileServiceImpl.save(automobile);
        dataAutomobile.add(automobile);

        model.setText("");
        year.setText("");
        probeg.setText("");
        powerty.setText("");
        rentpay.setText("");
    }


    @FXML
    public void deleteCar(ActionEvent actionEvent) {
        int index = autotable.getSelectionModel().getSelectedIndex();

        Automobile automobile = (Automobile) autotable.getSelectionModel().getSelectedItem();

        if(index >= 0) {
            automobileServiceImpl.delete(automobile);
            dataAutomobile.remove(index);
        }
    }

    @FXML
    public void handleEditCar(ActionEvent actionEvent) throws IOException {
        Automobile selectedAutomobile = (Automobile) autotable.getSelectionModel().getSelectedItem();
        if(selectedAutomobile != null) {
              goToEdit(selectedAutomobile);


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No Automobile Selected");
            alert.setContentText("Please select an automobile in the table");

            alert.showAndWait();

        }
    }

    public void goToEdit(Automobile automobile) throws IOException {
        EditAutomobilesController Controller = new EditAutomobilesController();
        Controller.show(automobile);
    }


    public TableView reservationtable;
    public TextField username;
    public TextField telephone;
    public TextField reservationmodel;
    public TextField give_date;
    public TextField back_date;

    @FXML
    public void addReservation() {
        Reservation reservation = new Reservation(username.getText(),telephone.getText(),model.getText(),
                give_date.getText(),back_date.getText());

        ReservationValid Valid = new ReservationValid();
       if(Valid.validateByComparingStartAndEndDates(give_date.getText(),back_date.getText())
           && Valid.validateByEndDate(back_date.getText()) && Valid.validateByExistenceOfAutomobile(model.getText())
               && Valid.validateByStartDate(give_date.getText())) {

           reservationsServiceImpl.save(reservation);
           System.out.println(give_date.getText());
           dataReservation.add(reservation);

           username.setText("");
           telephone.setText("");
           model.setText("");
           give_date.setText("");
           back_date.setText("");
       } else {
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
        }
    }


    @FXML
    public void handleEditReservation(ActionEvent actionEvent) {
        Automobile selectedAutomobile = (Automobile) autotable.getSelectionModel().getSelectedItem();
        if(selectedAutomobile != null) {

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("No Selection");
            alert.setHeaderText("No Reservation Selected");
            alert.setContentText("Please select any reservation in the table");

            alert.showAndWait();

        }
    }
}
