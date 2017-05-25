package com.example.ui;

import com.example.ConfigurationControllers;
import com.example.entities.Automobile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;


public  class EditAutomobilesController extends EventHandling {

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
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
    }


    public void show(Automobile automobile) throws IOException {
            modelEdit.setText(automobile.getModel());
           yearEdit.setText(String.valueOf(automobile.getYear()));
           probegEdit.setText(String.valueOf(automobile.getYear()));
           powerEdit.setText(String.valueOf(automobile.getYear()));
           rentpayEdit.setText(String.valueOf(automobile.getYear()));

           createNewStage(editAutoView,"EditAuto");

    }
}
