//package com.example.ui;
//
//import com.example.ConfigurationControllers;
//import com.example.entities.Automobile;
//import com.example.entities.Reservation;
//import com.example.service.ReservationsService;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//
//import javax.annotation.PostConstruct;
//import java.util.Collection;
//import java.util.List;
//
//@SuppressWarnings("SpringJavaAutowiringInspection")
//public class ReservationController extends EventHandling{
//
//    @Autowired
//    ReservationsService reservationsServiceImpl;
//
//    @FXML public TextField username;
//    @FXML public TextField telephone;
//    @FXML public TextField model;
//    @FXML public TextField give_date;
//    @FXML public TextField back_date;
//    @FXML public TableView table;
//
//    private ObservableList<Reservation> data;
//
//    @FXML
//    public void initialize(){
//
//    }
//
//    @SuppressWarnings("unchecked")
//    @PostConstruct
//    public void init(){
//        List<Reservation> reservations = reservationsServiceImpl.findAll();
//        if(reservations != null) {
//            data = FXCollections.observableArrayList(reservations);
//        }
//
//        TableColumn<Automobile,String> clientColumn = new TableColumn<>("Клинет");
//        clientColumn.setCellValueFactory(new PropertyValueFactory<>("reservationmodel"));
//
//        TableColumn<Automobile, String> yearColumn = new TableColumn<>("Телефон");
//        yearColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
//
//        TableColumn<Automobile, String> modelColumn = new TableColumn<>("Модель");
//        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
//
//        TableColumn<Automobile, String> startColumn = new TableColumn<>("Выдача");
//        startColumn.setCellValueFactory(new PropertyValueFactory<>("give_date"));
//
//        TableColumn<Automobile, String> endColumn = new TableColumn<>("Возврат");
//        endColumn.setCellValueFactory(new PropertyValueFactory<>("back_date"));
//
//        table.getColumns().setAll(clientColumn, yearColumn, modelColumn, startColumn, endColumn);
//
//        table.setItems(data);
//
//    }
//
//
//
//    @FXML
//    public void addReservation() {
//          Reservation reservation = new Reservation(username.getText(),telephone.getText(),model.getText(),
//                  give_date.getText(),back_date.getText());
//          System.out.println(give_date.getText());
//          reservationsServiceImpl.save(reservation);
//          data.add(reservation);
//
//          username.setText("");
//          telephone.setText("");
//          model.setText("");
//          give_date.setText("");
//          back_date.setText("");
//    }
//
//    @FXML
//    public void deleteReservation() {
//        int index = table.getSelectionModel().getSelectedIndex();
//        Reservation reservation = (Reservation) table.getSelectionModel().getSelectedItem();
//        if(index >= 0) {
//            reservationsServiceImpl.delete(reservation);
//            data.remove(index);
//        }
//    }
//
//
//}
