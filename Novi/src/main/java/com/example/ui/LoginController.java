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
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.io.InputStream;
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
              Node toRegistrationSource = (Node) actionEvent.getSource();

//              Stage loginStage = (Stage) toRegistrationSource.getScene().getWindow();
//              loginStage.setScene(new Scene(autoreservationtabView.getView()));
//              loginStage.show();
//              FXMLLoader loader = new FXMLLoader();
//              InputStream stream = getClass().getClassLoader().getResourceAsStream("fxml/autoreservationtab.fxml");
//              loader.load(stream);

              changeSceneByEvent(actionEvent,autoreservationtabView,"AutoReservationPage");

          }

          List<User> users = usersServiceImpl.findFromUsernameAndPassword(username,password);
          if(users.size() >=1 ) {
//               Node toAutomobiles = (Node) actionEvent.getSource();
//               Stage loginStage = (Stage) toAutomobiles.getScene().getWindow();
//               loginStage.setScene(new Scene(userautomobileView.getView()));
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

//        Node toRegistrationSource = (Node) actionEvent.getSource();
//        Stage loginStage = (Stage) toRegistrationSource.getScene().getWindow();
//        loginStage.setScene(new Scene(registrationView.getView()));
//        loginStage.show();
        changeSceneByEvent(actionEvent,registrationView,"RegistrationPage");
    }
}
