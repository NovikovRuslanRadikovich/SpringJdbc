package com.example.ui;


import com.example.ConfigurationControllers;
import com.example.entities.Automobile;
import com.example.service.AutomobilesService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.util.List;


public class ClientsAutomobilesController extends EventHandling{

    @Qualifier("userinputautomobileView")
    @Autowired
    private ConfigurationControllers.View  userinputautomobileView;

    @Autowired
    AutomobilesService automobileServiceImpl;

    @FXML public TableView table;
    private ObservableList<Automobile> data;

    @FXML
    public void initialize() {}

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init(){
        List<Automobile> automobiles = automobileServiceImpl.findAll();
        data = FXCollections.observableArrayList(automobiles);

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

        table.getColumns().setAll(modelColumn, yearColumn, probegColumn, powertyColumn, rentpayColumn);

        table.setItems(data);
    }

    @FXML
    public void reserveCar(ActionEvent actionEvent) {
        Node toChooseCar = (Node) actionEvent.getSource();

        Stage clientsStage = (Stage) toChooseCar.getScene().getWindow();

        clientsStage.setScene(new Scene(userinputautomobileView.getView()));
        clientsStage.show();
    }
}
