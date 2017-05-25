package com.example.ui;

import com.example.ConfigurationControllers;
import com.example.entities.User;
import com.example.service.UsersService;
import com.example.validation.ClientValid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class RegistrationController extends EventHandling {

    @Qualifier("userautomobileView")
    @Autowired
    private ConfigurationControllers.View userautomobileView;

    @Qualifier("loginView")
    @Autowired
    private ConfigurationControllers.View loginView;

    @Autowired
    UsersService usersServiceImpl;


 @FXML public TextField Username;
 @FXML public PasswordField Password;
 @FXML public TextField Telephone;

    @FXML
    public void initialize() {
    }


    @FXML
    public void registration(ActionEvent actionEvent) {
           String username = Username.getText();
           String password = Password.getText();
           String telephone = Telephone.getText();
           if(ClientValid.validateByTelephone(telephone) &&
                   ClientValid.validateByPassword(password) &&
                   ClientValid.validateByUsername(username)) {
               usersServiceImpl.save(new User(username,password,telephone));

               Node registrationSource = (Node) actionEvent.getSource();
               Stage registrationStage = (Stage) registrationSource.getScene().getWindow();
               registrationStage.setScene(new Scene(userautomobileView.getView()));
               registrationStage.show();

           } else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Wrong data!");
               alert.setHeaderText("Verify your data!");
               alert.setContentText("Telephone is digital type.Username and password aren't empty");
               alert.showAndWait();
           }
    }

    public void login(ActionEvent actionEvent) {
        changeSceneByEvent(actionEvent,loginView,"LoginPage");
    }
}
