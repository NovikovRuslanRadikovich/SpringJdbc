package com.example.ui;


import com.example.ConfigurationControllers;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class EventHandling {

    protected void changeSceneByEvent(ActionEvent event, ConfigurationControllers.View view, String name) {
        Parent page = view.getView();
        Scene scene = page.getScene() == null ? new Scene(page) :
                page.getScene();
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(scene);
        appStage.setTitle(name);
        appStage.show();
    }

    protected Stage createNewStage(ConfigurationControllers.View view, String name) {
        Parent page = view.getView();
        Scene scene = view.getView().getScene();
        if(scene == null) {
            scene = new Scene(page);
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(name);
        stage.initModality(Modality.WINDOW_MODAL);
        return stage;
    }
}
