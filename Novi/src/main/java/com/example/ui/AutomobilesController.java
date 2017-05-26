//package com.example.ui;
//
//import com.example.entities.Automobile;
//import com.example.service.AutomobileServiceImpl;
//import com.example.service.AutomobilesService;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.List;
//
//@SuppressWarnings("SpringJavaAutowiringInspection")
//public class AutomobilesController extends EventHandling{
//
//    @Autowired
//    AutomobilesService automobileServiceImpl;
//
//    @FXML public TableView table;
//    @FXML public TextField model;
//    @FXML public TextField year;
//    @FXML public TextField probeg;
//    @FXML public TextField powerty;
//    @FXML public TextField rentpay;
//
//    private ObservableList<Automobile> data;
//
//
//    @FXML
//    public void initialize() {
//    }
//
//    @SuppressWarnings("unchecked")
//    @PostConstruct
//    public void init(){
//        List<Automobile> automobiles = automobileServiceImpl.findAll();
//        if(automobiles != null) {
//            data = FXCollections.observableArrayList(automobiles);
//        }
//
//        TableColumn<Automobile,String> modelColumn = new TableColumn<>("Model");
//        modelColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
//
//        TableColumn<Automobile, String> yearColumn = new TableColumn<>("Год");
//        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
//
//        TableColumn<Automobile, String> probegColumn = new TableColumn<>("Пробег");
//        probegColumn.setCellValueFactory(new PropertyValueFactory<>("probeg"));
//
//        TableColumn<Automobile, String> powertyColumn = new TableColumn<>("Мощность");
//        powertyColumn.setCellValueFactory(new PropertyValueFactory<>("powerty"));
//
//        TableColumn<Automobile, String> rentpayColumn = new TableColumn<>("Аренда");
//        rentpayColumn.setCellValueFactory(new PropertyValueFactory<>("rentpay"));
//
//        table.getColumns().setAll(modelColumn, yearColumn, probegColumn, powertyColumn, rentpayColumn);
//
//        table.setItems(data);
//    }
//
//    @FXML
//    public void addCar() {
//        Automobile automobile = new Automobile(model.getText(),year.getText()
//                ,probeg.getText(),powerty.getText(),rentpay.getText());
//
//        automobileServiceImpl.save(automobile);
//        data.add(automobile);
//
//        model.setText("");
//        year.setText("");
//        probeg.setText("");
//        powerty.setText("");
//        rentpay.setText("");
//    }
//
//
//    @FXML
//    public void deleteCar(ActionEvent actionEvent) {
//        int index = table.getSelectionModel().getSelectedIndex();
//
//        Automobile automobile = (Automobile) table.getSelectionModel().getSelectedItem();
//
//        if(index >= 0) {
//            automobileServiceImpl.delete(automobile);
//            data.remove(index);
//        }
//    }
//
//
//    public boolean showEditCarDialog(Automobile automobile) {
//        try{
//            //Загрузка fxml файла и новая сцена
//            // для всплывающего диалогового окна
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(AutomobilesController.class.getResource("fxml/editautomobile.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
//            //диалоговое окно Stage
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Automobile");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//          //  dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//
//
//        } catch(IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return false;
//    }
//
//    @FXML
//    public void handleEditCar(ActionEvent actionEvent) {
//        Automobile selectedAutomobile = (Automobile) table.getSelectionModel().getSelectedItem();
//        if(selectedAutomobile != null) {
//            boolean okClicked = false;
//            if(okClicked) {
//                showEditCarDialog(selectedAutomobile);
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Automobile Selected");
//            alert.setContentText("Please select an automobile in the table");
//
//            alert.showAndWait();
//
//        }
//    }
//}
