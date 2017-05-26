package com.example;



import com.example.ui.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ConfigurationControllers {

    @Bean(name = "loginView")
    public View getLoginView() throws IOException {
        return loadView("fxml/login.fxml");
    }

    @Bean
    public LoginController getLoginController() throws IOException {
        return (LoginController) getLoginView().getController();
    }

    @Bean(name = "registrationView")
    public View getRegistrationView() throws IOException {
        return loadView("fxml/registration.fxml");
    }

    @Bean
    public RegistrationController getRegistrationController() throws IOException{
        return (RegistrationController) getRegistrationView().getController();
    }

     @Bean(name = "autoreservationtabView")
     public View getAutoReservationTabView() throws IOException {
        return loadView("fxml/autoreservationtab.fxml");
     }

     @Bean
     public AutoReservationTabController getAutoReservationTabController() throws IOException {
        return (AutoReservationTabController) getAutoReservationTabView().getController();
     }

     @Bean(name = "userinputautomobileView")
     public View getUserInputAutomobileView() throws IOException {
        return loadView("fxml/userinputautomobile.fxml");
     }

     @Bean
     public UserInputAutomobileController getUserInputAutomobileContoller() throws IOException {
        return (UserInputAutomobileController) getUserInputAutomobileView().getController();
     }

      @Bean(name = "userautomobileView")
      public View getUserAutomobileView() throws IOException {
          return loadView("fxml/userautomobile.fxml");
      }

      @Bean(name = "editautomobileView")
      public View getEditAutomobileView() throws IOException {
          return loadView("fxml/editautomobile.fxml");
      }

      @Bean
      public EditAutomobilesController getEditAutomobilesController() throws IOException {
        return (EditAutomobilesController) getEditAutomobileView().getController();
      }

       @Bean(name = "editreservationView")
       public View getEditReservationView() throws IOException {
          return loadView("fxml/editreservation.fxml");
       }

       @Bean
       public EditReservationController getEditReservationController() throws IOException {
            return (EditReservationController) getEditReservationView().getController();
       }

      @Bean
      public ClientsAutomobilesController getUserAutomobileController() throws IOException {
           return (ClientsAutomobilesController) getUserAutomobileView().getController();
      }

       protected View loadView(String url) throws IOException {
          InputStream fxmlStream = null;
          try {
             fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
             FXMLLoader loader = new FXMLLoader();
             loader.load(fxmlStream);
             return new View(loader.getRoot(), loader.getController());
           } finally {
              if (fxmlStream != null) {
                  fxmlStream.close();
              }
            }
        }

    public static class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }
}
