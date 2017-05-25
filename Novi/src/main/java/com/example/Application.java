package com.example;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;


@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

	@Value("${ui.title:JavaFX приложение}")
	private String windowTitle;

	@Qualifier("loginView")
	@Autowired
	private ConfigurationControllers.View enterView;

	@Qualifier("automobileView")
	@Autowired
	private ConfigurationControllers.View automobileView;

	@Qualifier("registrationView")
	@Autowired
	private ConfigurationControllers.View registrationView;

	@Qualifier("reservationView")
	@Autowired
	private ConfigurationControllers.View reservationView;


	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle(windowTitle);
		primaryStage.setScene(new Scene(enterView.getView()));
		primaryStage.setResizable(true);
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	public static void main(String[] args) {launchApp(Application.class, args);}
}
