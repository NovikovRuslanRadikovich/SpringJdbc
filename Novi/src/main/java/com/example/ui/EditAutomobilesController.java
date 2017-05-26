package com.example.ui;

import com.example.ConfigurationControllers;
import com.example.entities.Automobile;
import com.example.service.AutomobilesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.IOException;



public  class EditAutomobilesController extends EventHandling {

    private Automobile automobile;

    @Autowired
    AutomobilesService automobilesService;

    @Qualifier("editautomobileView")
    @Autowired
    ConfigurationControllers.View editAutoView;


    @FXML public  TextField modelEdit;
    @FXML public  TextField yearEdit;
    @FXML public  TextField probegEdit;
    @FXML public TextField powerEdit;
    @FXML public  TextField rentpayEdit;

    @FXML
    public void initialize() throws IOException {

    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init(){

    }

    @FXML
    public void editAutomobile(ActionEvent actionEvent) {
        if(automobile == null ) {
            automobile = new Automobile("dfvfd","L","1","1","1");
        }
        automobile.setAutomodel(modelEdit.getText());
        automobile.setPowerty(powerEdit.getText());
        automobile.setYear(yearEdit.getText());
        automobile.setProbeg(yearEdit.getText());
        automobile.setRentpay(rentpayEdit.getText());

        automobilesService.update(automobile);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.close();

    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.close();
    }

    public void setAutomobile(Automobile automobile) {
        modelEdit.setText(automobile.getAutomodel());
        yearEdit.setText(String.valueOf(automobile.getYear()));
        probegEdit.setText(String.valueOf(automobile.getProbeg()));
        powerEdit.setText(String.valueOf(automobile.getPowerty()));
        rentpayEdit.setText(String.valueOf(automobile.getRentpay()));
    }
}
