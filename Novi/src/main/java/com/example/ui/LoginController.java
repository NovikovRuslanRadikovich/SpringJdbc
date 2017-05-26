package com.example.ui;


import com.example.ConfigurationControllers;
import com.example.entities.User;
import com.example.service.UsersService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class LoginController extends EventHandling {

    @Autowired
    private UsersService usersServiceImpl;

    @Qualifier("autoreservationtabView")
    @Autowired
    private ConfigurationControllers.View autoreservationtabView;

    @Qualifier("registrationView")
    @Autowired
    private ConfigurationControllers.View registrationView;

    @Qualifier("userautomobileView")
    @Autowired
    private ConfigurationControllers.View userautomobileView;

    @FXML public PasswordField Password;

    @FXML public TextField Username;

    @FXML
    public void initialize() {
    }

    @FXML
    public void enter(ActionEvent actionEvent) throws IOException {
          String username = Username.getText();
          String password = Password.getText();

          if("admin".equals(password) && "admin".equals(username)) {
              changeSceneByEvent(actionEvent,autoreservationtabView,"AutoReservationPage");

          }

          List<User> users = usersServiceImpl.findFromUsernameAndPassword(username,password);
          if(users.size() >=1 ) {

              changeSceneByEvent(actionEvent,userautomobileView,"UserAutoPage");
          } else {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Wrong credentials!");
              alert.setHeaderText("Check your username or password!");
              alert.setContentText("User with entered name is not found");
              alert.showAndWait();
          }
    }

    @FXML
    public void registration(ActionEvent actionEvent) {

        changeSceneByEvent(actionEvent,registrationView,"RegistrationPage");
    }
}
